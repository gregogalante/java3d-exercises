/*

Main.java VERSION 1.0

This class is a base main object for projects.

*/

import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Background;
import javax.media.j3d.Group;
import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.image.TextureLoader;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

// import ColorCube as default tg child
import com.sun.j3d.utils.geometry.ColorCube;

class Main {

  private BoundingSphere defaultBound = new BoundingSphere(new Point3d(), 10.0d);

  public Main() {
    // initialize a new simple universe
    SimpleUniverse universe = new SimpleUniverse();
    universe.getViewingPlatform().setNominalViewingTransform();

    // create branch group
    BranchGroup branchGroup = createBranchGroup();

    // add key movements to branchGroup
    addKeyMovementsToBranchGroup(universe, branchGroup);

    // add ambient light to branchGroup
    // addAmbientLight(branchGroup);

    // add directional light to branchGroup
    // addDirectionalLight(branchGroup);

    // add a background image to the branchGroup
    addBackground(branchGroup, "../images/stars.jpg");

    // add branchgroup to universe
    branchGroup.compile();
    universe.addBranchGraph(branchGroup);
  }

  // This function creates and return a new branchgroup.
  private BranchGroup createBranchGroup() {
    // initialize bg
    BranchGroup bg = new BranchGroup();
    // create main tg
    TransformGroup tg = new TransformGroup();
    tg.addChild(new MyColorCube(0.3f)); // <-- NOTE: edit here with other components. ***
    // add tg to bg
    bg.addChild(tg);
    // return bg
    return bg;
  }

  // MyColorCube class:
  // *******************************************************************************************

  private class MyColorCube extends Group {

    public MyColorCube(float size) {
      TransformGroup tg = new TransformGroup();
      // create transformation for the sphere rotation
      Transform3D rotation = new Transform3D();
      rotation.rotY(- Math.PI / 4.0f);
      // create alpha
      Alpha alpha = new Alpha(-1, 25000);
      // create rotation interpolator
      RotationInterpolator rotationInterpolator = new RotationInterpolator(
        alpha,
        tg,
        rotation,
        0.0f,
        (float) Math.PI * 2.0f
      );
      // create bounding sphere and set to interpolator
      BoundingSphere rotationBoundingSphere = new BoundingSphere();
      rotationInterpolator.setSchedulingBounds(rotationBoundingSphere);
      // permit rotation after the creation
      tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      // add sphere as tg child
      tg.addChild(rotationInterpolator);

      TransformGroup cubeTg = new TransformGroup();
      // create a transformation to translate the cube
      Transform3D translation = new Transform3D();
	    translation.setTranslation(new Vector3d(3.0, 0.0, 0.0));
		  cubeTg.setTransform(translation);
      cubeTg.addChild(new ColorCube(0.3f));
      tg.addChild(cubeTg);
      addChild(tg);
    }
  }

  // User movements:
  // *******************************************************************************************

  // This function adds the possibility to user to move the viewer inside the world.
  private void addKeyMovementsToBranchGroup(SimpleUniverse universe, BranchGroup branchGroup) {
    // find view platform transformgroup
    TransformGroup viewTg = universe.getViewingPlatform().getViewPlatformTransform();
    // create viewTg transformation
    Transform3D viewTransform = new Transform3D();
    // create behaviour to navigate with keys
    KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewTg);
    // create bound for key navigator
    keyNavBeh.setSchedulingBounds(this.defaultBound);

    // add behaviour to branchgroup
    branchGroup.addChild(keyNavBeh);
  }

  // World lights:
  // *******************************************************************************************

  // This function add an ambient light to the world.
  private void addAmbientLight(BranchGroup branchGroup) {
    // create light
    AmbientLight light = new AmbientLight();
    // add bounds to light
    light.setInfluencingBounds(this.defaultBound);
    // add light to tg
    branchGroup.addChild(light);
  }

  // This function add a directional light to the world with the default direction.
  private void addDirectionalLight(BranchGroup branchGroup) {
    addDirectionalLight(branchGroup, -1.0f, -0.5f, 1.0f);
  }

  // This function add a directional light to the world with a custom direction.
  private void addDirectionalLight(BranchGroup branchGroup, float dirX, float dirY, float dirZ) {
    // create light
    DirectionalLight light = new DirectionalLight();
    light.setDirection(dirX, dirY, dirX);
    // add bounds to light
    light.setInfluencingBounds(this.defaultBound);
    // add light to tg
    branchGroup.addChild(light);
  }

  // World background:
  // *******************************************************************************************

  private void addBackground(BranchGroup branchGroup, String imagepath) {
    TextureLoader loader = new TextureLoader(imagepath, null);
    ImageComponent2D image = loader.getImage();
    Background background = new Background();
    background.setImage(image);
    background.setImageScaleMode(Background.SCALE_FIT_MAX);
    background.setApplicationBounds(this.defaultBound);
    branchGroup.addChild(background);
  }

  // Main:
  // *******************************************************************************************

  public static void main (String[] args) {
    new Main();
  }
}
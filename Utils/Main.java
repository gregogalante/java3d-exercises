import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Light;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Background;
import javax.media.j3d.View;
import javax.media.j3d.SpotLight;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;

class Main implements InterfaceTextures, InterfaceColors {

  private BoundingSphere defaultBound = new BoundingSphere(new Point3d(), 10.0d);

  public Main() {
    // initialize a new simple universe
    SimpleUniverse universe = new SimpleUniverse();
    universe.getViewingPlatform().setNominalViewingTransform();

    // create branch group
    BranchGroup branchGroup = createBranchGroup();
    
    // translate user position
    translateLookAt(universe);

    // add key movements to branchGroup
    addKeyMovementsToBranchGroup(universe, branchGroup);

    // add ambient light to branchGroup
    addAmbientLight(branchGroup);

    // add directional light to branchGroup
    addDirectionalLight(branchGroup);

    // add spot light to branchGroup
    addSpotLight(branchGroup);

    // add a background image to the branchGroup
    // addBackground(branchGroup, TEXTURE_STARS);

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
    tg.addChild(new MyGroup(0.3f));
    // rotate tg with mouse
    // addMouseMovementsToTransformGroup(tg, bg);
    // move with keyboard
    // addKeyMovementsToTransformGroup(tg, bg);
    // add tg to bg
    bg.addChild(tg);
    // return bg
    return bg;
  }

  // User movements:
  // *******************************************************************************************

  // This function translates the user position on the world with default values.
  private void translateLookAt(SimpleUniverse universe) {
    translateLookAt(universe, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f);
  }

  // This function translates the user position on the world.
  private void translateLookAt(SimpleUniverse universe, float x, float y, float z, float tx, float ty, float tz) {
    // find viewing platform
    ViewingPlatform viewingPlatform = universe.getViewingPlatform();
    // find view
    View view = universe.getViewer().getView();
    // find transformgroup for view
    TransformGroup vptg = viewingPlatform.getViewPlatformTransform();
    // create lookat transormation
    Transform3D lookAtT3D = new Transform3D();
    lookAtT3D.lookAt(
      new Point3d (x, y, z),
      new Point3d (tx, ty, tz),
      new Vector3d(0.0, 1.0, 0.0)
    );
    lookAtT3D.invert();
    // set lookat transformation to tg
    vptg.setTransform(lookAtT3D);
  }

  // This function adds the possibility to user to move the viewer with keys inside the world.
  private void addKeyMovementsToBranchGroup(SimpleUniverse universe, BranchGroup branchGroup) {
    // find view platform transformgroup
    TransformGroup viewTg = universe.getViewingPlatform().getViewPlatformTransform();
    // create behaviour to navigate with keys
    KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewTg);
    // create bound for key navigator
    keyNavBeh.setSchedulingBounds(this.defaultBound);

    // add behaviour to branchgroup
    branchGroup.addChild(keyNavBeh);
  }

  // Transformgroup movements:
  // *******************************************************************************************

  // This function adds the possibility to user to move the viewer with keys inside the world.
  private void addKeyMovementsToTransformGroup(TransformGroup transformGroup, BranchGroup branchGroup) {
    // create transform transformation
    Transform3D transform = new Transform3D();
    // create behaviour to navigate with keys
    KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(transformGroup);
    // create bound for key navigator
    keyNavBeh.setSchedulingBounds(this.defaultBound);

    // add behaviour to branchgroup
    branchGroup.addChild(keyNavBeh);
  }

  // This function adds movements of a specific transformgroup of the transformgroup managed with mouse.
  private void addMouseMovementsToTransformGroup(TransformGroup transformGroup, BranchGroup branchGroup) {
		// permit movements
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		// create behaviour
		MouseRotate mouseRotate = new MouseRotate(transformGroup);
		// set boundingsphere
		mouseRotate.setSchedulingBounds(this.defaultBound);
    // add all
		branchGroup.addChild(mouseRotate);
  }

  // World lights:
  // *******************************************************************************************

  // This function add an ambient light to the world.
  private void addAmbientLight(BranchGroup branchGroup) {
    // create light
    AmbientLight light = new AmbientLight();
    // add bounds to light
    light.setInfluencingBounds(this.defaultBound);
    // add shared settings
    addSharedSettingsToLight(light);
    // add light to tg
    branchGroup.addChild(light);
  }

  // This function add a directional light to the world with the default direction.
  private void addDirectionalLight(BranchGroup branchGroup) {
    addDirectionalLight(branchGroup, -3.0f, 0.0f, 1.0f);
  }

  // This function add a directional light to the world with a custom direction.
  private void addDirectionalLight(BranchGroup branchGroup, float dirX, float dirY, float dirZ) {
    // create light
    DirectionalLight light = new DirectionalLight();
    light.setDirection(dirX, dirY, dirX);
    // add bounds to light
    light.setInfluencingBounds(this.defaultBound);
    // add shared settings
    addSharedSettingsToLight(light);
    // add light to tg
    branchGroup.addChild(light);
  }

  // This function add a spot light with default position and angle.
  private void addSpotLight(BranchGroup branchGroup) {
    addSpotLight(branchGroup, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, (float) Math.PI / 2.0f);
  }

  // This function add a spot light to the world with custom position and angle.
  private void addSpotLight(BranchGroup branchGroup, float dirX, float dirY, float dirZ, float posX, float posY, float posZ, float angle) {
    // create light
    SpotLight light = new SpotLight();
    light.setDirection(dirX, dirY, dirX);
    light.setPosition(new Point3f(posX, posY, posZ));
		light.setSpreadAngle(angle);
    // add bounds to light
    light.setInfluencingBounds(this.defaultBound);
    // add shared settings
    addSharedSettingsToLight(light);
    // add light to tg
    branchGroup.addChild(light);
  }

  // This function sets shared settings to light.
  private void addSharedSettingsToLight(Light light) {
    // set color light
    // light.setColor(COLOR_WHITE);
    // enable light
    light.setEnable(true);
  }

  // World background:
  // *******************************************************************************************

  private void addBackground(BranchGroup branchGroup, String imagepath) {
    // load the texture
    TextureLoader loader = new TextureLoader(imagepath, null);
    // create image object
    ImageComponent2D image = loader.getImage();
    // create background
    Background background = new Background();
    // set image to background
    background.setImage(image);
    background.setImageScaleMode(Background.SCALE_FIT_MAX);
    // set bound to background
    background.setApplicationBounds(this.defaultBound);
    // add background to branchgroup
    branchGroup.addChild(background);
  }

  // Main:
  // *******************************************************************************************

  public static void main (String[] args) {
    new Main();
  }
}
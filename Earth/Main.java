import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.View;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.media.j3d.BoundingSphere;

class Main {

  public Main() {
    SimpleUniverse universe = new SimpleUniverse();
    BranchGroup group = new BranchGroup();

    // add object to branchgroup
    group.addChild(new Space());
    group.addChild(createTGDirectional());
    universe.getViewingPlatform().setNominalViewingTransform();
    universe.addBranchGraph(group);

    // change user position
    ViewingPlatform viewingPlatform = universe.getViewingPlatform();
    View view = universe.getViewer().getView();

    TransformGroup vptg = viewingPlatform.getViewPlatformTransform();
    Transform3D lookAtT3D = new Transform3D();
    lookAtT3D.lookAt(
      new Point3d (1, 1, 4), // observer position
      new Point3d (0.0, 0.0, 0.0), // center position
      new Vector3d(0.0, 1.0, 0.0) // up position
    );
    lookAtT3D.invert();
    vptg.setTransform(lookAtT3D);
  }

  private TransformGroup createTG() {
    TransformGroup tg = new TransformGroup();
    tg.addChild(new Earth(0.5f));
    return tg;
  }

  private TransformGroup createTGDirectional() {
    TransformGroup tg = createTG();
    // create bound for light
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.d,0.d,0.d), 10.d);
    // create light
    DirectionalLight light = new DirectionalLight();
    light.setDirection(0.1f, 0.1f, 0.1f);
    // add color to light
    Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
    light.setColor(green);
    // add bounds to light
    light.setInfluencingBounds(bounds);
    // add light to tg
    tg.addChild(light);
    // return tg
    return tg;
  }

  public static void main (String[] args) {
    new Main();
  }
}
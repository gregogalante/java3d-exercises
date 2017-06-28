import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.media.j3d.BoundingSphere;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;

class Main {

  public Main() {
    SimpleUniverse universe = new SimpleUniverse();
    universe.getViewingPlatform().setNominalViewingTransform();

    // create branchgroup
    BranchGroup group = new BranchGroup();
    TransformGroup tg = new TransformGroup();
    tg.addChild(new ColorCube(0.3));
    group.addChild(tg);

    // find view platform transformgroup
    TransformGroup viewTg = universe.getViewingPlatform().getViewPlatformTransform();
    // create viewTg transformation
    Transform3D viewTransform = new Transform3D();
    // create behaviour to navigate with keys
    KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewTg);
    // create bound for key navigator
    keyNavBeh.setSchedulingBounds(new BoundingSphere(new Point3d(), 10000.0));

    // add behaviour to branchgroup
    group.addChild(keyNavBeh);
    group.compile();

    // add branchgroup to universe
    universe.addBranchGraph(group);
  }

  public static void main (String[] args) {
    new Main();
  }
}
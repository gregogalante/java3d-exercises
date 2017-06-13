import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;

class Main {

  public Main() {
    SimpleUniverse universe = new SimpleUniverse();
    BranchGroup group = new BranchGroup();
    TransformGroup tg = new TransformGroup();
    Transform3D t1 = new Transform3D();
    Transform3D t2 = new Transform3D();
    Transform3D t3 = new Transform3D();

    t1.setTranslation(new Vector3d(0.3, 0.0, 0.0));
    t2.setScale(1.1);

    t3.mul(t1, t2);

    tg.setTransform(t3);
    tg.addChild(new ColorCube(0.3));
    group.addChild(tg);
    universe.getViewingPlatform().setNominalViewingTransform();
    universe.addBranchGraph(group);
  }

  public static void main (String[] args) {
    new Main();
  }
}
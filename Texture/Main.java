import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.View;

class Main {

  public Main() {
    SimpleUniverse universe = new SimpleUniverse();
    BranchGroup group = new BranchGroup();

    // add object to branchgroup
    group.addChild(new Earth(0.5f));
    group.addChild(new BackSpace());
    universe.getViewingPlatform().setNominalViewingTransform();
    universe.addBranchGraph(group);

    // change user position
    ViewingPlatform viewingPlatform = universe.getViewingPlatform();
    View view = universe.getViewer().getView();

    TransformGroup vptg = viewingPlatform.getViewPlatformTransform();
    Transform3D lookAtT3D = new Transform3D();
    lookAtT3D.lookAt(
      new Point3d (0, 0, 4), // observer position
      new Point3d (0.0, 0.0, 0.0), // center position
      new Vector3d(0.0, 1.0, 0.0) // up position
    );
    lookAtT3D.invert();
    vptg.setTransform(lookAtT3D);
  }

  public static void main (String[] args) {
    new Main();
  }
}
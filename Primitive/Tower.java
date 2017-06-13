import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;

public class Tower extends Group {
  static final protected Appearance appearance = new Appearance();

  protected TransformGroup tgBox = new TransformGroup();
  protected TransformGroup tgCylinder = new TransformGroup();
  protected Box box = new Box(1, 0.3f, 1, appearance);
  protected Cylinder cylinder = new Cylinder(0.8f, 2.5f, appearance);

  public Tower() {
    // add primitives
    tgBox.addChild(box);
    tgCylinder.addChild(cylinder);
    // apply transformations
    Transform3D trBox = new Transform3D();
    Transform3D trCylinder = new Transform3D();
    trBox.setTranslation(new Vector3d(0.0, -1.4, 0.0));
    tgBox.setTransform(trBox);
    trCylinder.setTranslation(new Vector3d(0.0, 0.0, 0.0));
    tgCylinder.setTransform(trCylinder);
    // add children to groups
    addChild(tgBox);
    addChild(tgCylinder);
  }

}
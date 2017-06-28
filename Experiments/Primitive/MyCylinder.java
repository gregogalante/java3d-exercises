import javax.vecmath.Point3d;
import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.geometry.Cylinder;

public class MyCylinder extends Group {
  static final protected Appearance appearance = new Appearance();

  protected TransformGroup tg = new TransformGroup();
  protected Cylinder cylinder = new Cylinder(1, 3, appearance);

  public MyCylinder() {
    tg.addChild(cylinder);
    addChild(tg);
  }

}
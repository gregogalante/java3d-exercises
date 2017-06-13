import javax.vecmath.Point3d;
import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.geometry.Box;

public class MyBox extends Group {
  static final protected Appearance appearance = new Appearance();

  protected TransformGroup tg = new TransformGroup();
  protected Box box = new Box(1, 1, 1, appearance);

  public MyBox() {
    tg.addChild(box);
    addChild(tg);
  }

}
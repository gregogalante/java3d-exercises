import javax.vecmath.Point3d;
import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.geometry.Box;

public class PyramidBase extends Group {
  static final protected Appearance appearance = new Appearance();

  protected TransformGroup tg = new TransformGroup();
  protected Box box;

  public PyramidBase(float width, float height) {
    box = new Box(width, height, width, appearance);
    tg.addChild(box);
    addChild(tg);
  }

}
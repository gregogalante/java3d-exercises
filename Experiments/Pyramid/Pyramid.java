import javax.vecmath.Point3d;
import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;

public class Pyramid extends Group {

  public Pyramid() {
    // loop bases levels
    for (int i = 0; i < 10; i++) {
      // define base
      float width = (1.0f - ((float) i / 10.0f));
      float height = ((i > 8) ? 0.2f : 0.1f);
      System.out.println(height);
      PyramidBase p = new PyramidBase(width, height);
      // define base transformation
      double translation = 0.1 * i;
      Transform3D trp = new Transform3D();
      trp.setTranslation(new Vector3d(0.0, translation, 0.0));
      // define transformgroup for base
      TransformGroup tg = new TransformGroup();
      tg.setTransform(trp);
      tg.addChild(p);
      addChild(tg);
    }
  }

}
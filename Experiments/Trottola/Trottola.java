import javax.vecmath.Point3d;
import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import com.sun.j3d.utils.geometry.Cone;
import javax.vecmath.Vector3d;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.TransparencyAttributes;

class Trottola extends Group {

  protected TransformGroup tg1 = new TransformGroup();
  protected TransformGroup tg2 = new TransformGroup();
  protected Transform3D t3d1;
  protected Transform3D t3d2;
  protected Appearance appearance1;
  protected Appearance appearance2;
  protected Cone cone1;
  protected Cone cone2;

  public Trottola() {
    // create cones with appearance
    appearance1 = createAppearance1();
    appearance2 = createAppearance2();
    cone1 = new Cone(0.5f, 0.5f, appearance1);
    cone2 = new Cone(0.5f, 0.3f, appearance2);
    // create transform3ds and add to tgs
    t3d1 = createTransform1();
    t3d2 = createTransform2();
    tg1.setTransform(t3d1);
    tg2.setTransform(t3d2);
    // add cones to tgs and add tgs to group
    tg1.addChild(cone1);
    tg2.addChild(cone2);
    addChild(tg1);
    addChild(tg2);
  }

  protected Appearance createAppearance1() {
    Appearance app = new Appearance();
    ColoringAttributes coloringAttributes = new ColoringAttributes(0.100f, 0.100f, 0.100f, ColoringAttributes.FASTEST);
    app.setColoringAttributes(coloringAttributes);
    return app; 
  }

  protected Appearance createAppearance2() {
    Appearance app = new Appearance();
    ColoringAttributes coloringAttributes = new ColoringAttributes(0.200f, 0.200f, 0.200f, ColoringAttributes.FASTEST);
    app.setColoringAttributes(coloringAttributes);
    TransparencyAttributes transparencyAttributes = new TransparencyAttributes();
    transparencyAttributes.setTransparencyMode(TransparencyAttributes.BLENDED);
    transparencyAttributes.setTransparency(0.5f);
    app.setTransparencyAttributes(transparencyAttributes);
    return app; 
  }

  protected Transform3D createTransform1() {
    Transform3D t = new Transform3D();
    Transform3D rotation = new Transform3D();
    Transform3D translate = new Transform3D();
    // rotate the cone
    rotation.rotX(2 * Math.PI/2);
    translate.setTranslation(new Vector3d(0.0, -0.40, 0.0));
    translate.mul(rotation);
    t.mul(translate);
    return t;
  }

  protected Transform3D createTransform2() {
    Transform3D t = new Transform3D();
    return t;
  }

}
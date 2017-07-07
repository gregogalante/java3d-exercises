import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

class TavoloRadio extends Group implements InterfaceColors {

  protected BoundingSphere bound;
  protected float size;

  // Constructors:
  // *******************************************************************************************

  public TavoloRadio(float size) {
    this(size, null);
  }

  public TavoloRadio(float size, BoundingSphere bound) {
    if (bound == null) {
      this.bound = createBoundingSphere();
    }
    this.size = size;
    // add base tavolo
    addChild(new BaseTavolo(this.size));
    // create piano with radio
    TransformGroup pianoTavoloRadio = new TransformGroup();
    pianoTavoloRadio.addChild(createPianoTavolo());
    pianoTavoloRadio.addChild(createRadio());
    addRotationToTG(pianoTavoloRadio, 1.0f);
    addChild(pianoTavoloRadio);
  }

  protected TransformGroup createPianoTavolo() {
    TransformGroup tg = new TransformGroup();
    PianoTavolo pianoTavolo = new PianoTavolo(this.size);
    tg.addChild(pianoTavolo);
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, this.size * 3, 0.0f));
    tg.setTransform(translate);
    return tg;
  }

  protected TransformGroup createRadio() {
    TransformGroup tg = new TransformGroup();
    Radio radio = new Radio(this.size);
    tg.addChild(radio);
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, this.size * 3.5, 0.0f));
    tg.setTransform(translate);
    return tg;
  }
  
  // Default datas:
  // *******************************************************************************************

  // This function creates the bounding sphere for the object.
  protected BoundingSphere createBoundingSphere() {
    return new BoundingSphere(new Point3d(), 10.0d);
  }

  // Helpers:
  // *******************************************************************************************

  // This function add a rotation to a transformgroup object.
  protected void addRotationToTG(TransformGroup tg, float rotation) {
    // create transformation for the sphere rotation
    Transform3D sphereRotation = new Transform3D();
		sphereRotation.rotY(- Math.PI / 4.0f);
    // create alpha
		Alpha alpha = new Alpha(-1, 50000);
    // create rotation interpolator
		RotationInterpolator sphereRotationInterpolator = new RotationInterpolator(
      alpha,
      tg,
      sphereRotation,
      0.0f,
      (float) Math.PI * rotation
    );
    // create bounding sphere and set to interpolator
		sphereRotationInterpolator.setSchedulingBounds(this.bound);
    // permit rotation after the creation
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    // add sphere as tg child
    tg.addChild(sphereRotationInterpolator);
  }

}
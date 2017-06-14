// Import base dependencies.
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Material;

import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;

// Colonna class.
public class Colonna extends Group {

  // debug
  protected boolean debugMode;
  // dimensions
  protected float unit;
  // elements
  protected Appearance colonnaAppearance;
  protected TransformGroup tgFusto;
  protected TransformGroup tgEchino;
  protected TransformGroup tgAbaco;

  public Colonna(float height, boolean debugMode) {
    this.debugMode = debugMode;
    // define object values
    this.unit = height / 10;
    this.colonnaAppearance = createAppearance();
    this.tgFusto = createFusto();
    this.tgEchino = createEchino();
    this.tgAbaco = createAbaco();
    // add children to group
    addChild(this.tgFusto);
    addChild(this.tgEchino);
    addChild(this.tgAbaco);
  }

  // This function creates and returns an appearance object for the object.
  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    material.setAmbientColor(239/255f,224/255f,203/255f);
    material.setDiffuseColor(239/255f,224/255f,203/255f);
    appearance.setMaterial(material);
    // add debug style
    if (this.debugMode) {
      appearance.setPolygonAttributes(new PolygonAttributes(
        PolygonAttributes.POLYGON_LINE,
        PolygonAttributes.CULL_NONE,
        0
      ));
    }
    // return the appearance
    return appearance;
  }

  // This function generates and returns a Fusto object based on the given height.
  protected TransformGroup createFusto() {
    TransformGroup tg = new TransformGroup();
    MyCylinder fusto = new MyCylinder(
      this.unit,
      (this.unit * 8.5f),
      this.colonnaAppearance
    );
    tg.addChild(fusto);
    return tg;
  }

  // This function generates and returns a Echino object based on the given height.
  protected TransformGroup createEchino() {
    TransformGroup tg = new TransformGroup();
    MyCylinder echino = new MyCylinder(
      (this.unit * 1.5f),
      this.unit,
      this.unit,
      this.colonnaAppearance
    );
    // translate position
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, (this.unit * 4.75f), 0.0f));
    tg.setTransform(translate);
    // add echino to tg and return it
    tg.addChild(echino);
    return tg;
  }

  protected TransformGroup createAbaco() {
    TransformGroup tg = new TransformGroup();
    Box abaco = new Box(
      (this.unit * 1.55f),
      (this.unit * 0.5f),
      (this.unit * 1.55f),
      this.colonnaAppearance
    );
    // translate position
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, (this.unit * 5.25f), 0.0f));
    tg.setTransform(translate);
    // add abaco to tg and return it
    tg.addChild(abaco);
    return tg;
  }

}
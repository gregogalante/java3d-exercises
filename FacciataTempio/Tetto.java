// Import base dependencies.
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TexCoordGeneration;

import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.geometry.Box;

// Scalinata class.
public class Tetto extends Group {
  
  // debug
  protected boolean debugMode;
  // dimensions
  protected float size;
  protected float baseWidth;
  protected float baseLength;
  // elements
  protected Appearance tettoAppearance;
  protected TransformGroup base;
  protected TransformGroup sopraBase;
  protected TransformGroup copertura;

  public Tetto(float x, float z) {
    this(x, z, null, false);
  }

  public Tetto(float x, float z, Appearance appearance) {
    this(x, z, appearance, false);
  }

  public Tetto(float x, float z, Appearance appearance, boolean debugMode) {
    this.debugMode = debugMode;
    // set initial values
    this.baseWidth = x;
    this.baseLength = z;
    this.size = calculateSize();
    this.tettoAppearance = (appearance == null) ? createAppearance() : appearance;
    // create componets
    this.base = createBase();
    addChild(this.base);
    this.sopraBase = createSopraBase();
    addChild(this.sopraBase);
    this.copertura = createCopertura();
    addChild(this.copertura);
  }

  public float getSize() {
    return this.size;
  }

  public float getHeight() {
    return (this.size * 1.5f);
  }

  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    material.setAmbientColor(239/255f,224/255f,203/255f);
    material.setDiffuseColor(239/255f,224/255f,203/255f);
    appearance.setMaterial(material);
    // load texture file
    TextureLoader textureLoader = new TextureLoader("pietra.jpg", null);
    // initialize texture object
    Texture texture = textureLoader.getTexture();
    // add texture to the appearance
    appearance.setTexture(texture);
    // initialize texture attributes
    TextureAttributes textureAttributes = new TextureAttributes();
	  textureAttributes.setTextureMode(TextureAttributes.COMBINE);
    appearance.setTextureAttributes(textureAttributes);
    // initialize and add text coordinates generator
    TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_3);
	  appearance.setTexCoordGeneration(tcg);
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // return the appearance
    return appearance;
  }

  // This function calculate the correct size for a single scalino.
  protected float calculateSize() {
    float d = (float) Math.sqrt((this.baseLength * this.baseLength) + (this.baseWidth * this.baseWidth));
    return (d / 20);
  }

  protected TransformGroup createBase() {
    TransformGroup tg = new TransformGroup();
    Box base = new Box(
      (this.baseWidth),
      this.size,
      (this.baseLength),
      this.tettoAppearance
    );
    tg.addChild(base);
    return tg;
  }

  protected TransformGroup createSopraBase() {
    TransformGroup tg = new TransformGroup();
    Box base = new Box(
      (this.baseWidth - (this.size / 2)),
      (this.size / 2),
      (this.baseLength - (this.size / 2)),
      this.tettoAppearance
    );
    tg.addChild(base);
    // add correct transformation to tg
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      this.size + (this.size / 2),
      0.0f
    ));
    tg.setTransform(translate);
    return tg;
  }

  protected TransformGroup createCopertura() {
    TransformGroup tg = new TransformGroup();
    // TODO: Continue here
    return tg;
  }

}
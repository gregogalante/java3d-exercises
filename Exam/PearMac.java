import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.SwitchValueInterpolator;
import javax.media.j3d.Switch;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.image.TextureLoader;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Cylinder;

class PearMac extends Group implements InterfaceTextures, InterfaceColors {

  protected Appearance tavoloAppearance;
  protected Appearance baseMacAppearance;
  protected Appearance schermoAppearance;
  protected BoundingSphere bound;
  protected float size;

  // Constructors:
  // *******************************************************************************************

  /*
  Il componente size indica l'altezza del tavolo
  (dalla quale vengono poi valutate le altre dimensioni)
  */
  
  public PearMac(float size) {
    this(size, null);
  }

  public PearMac(float size, BoundingSphere bound) {
    // initial settings
    this.bound = (bound == null) ? createBoundingSphere() : bound;
    this.tavoloAppearance = createTavoloAppearance();
    this.baseMacAppearance = createBaseMacAppearance();
    this.schermoAppearance = createSchermoAppearance();
    this.size = size;
    // add children
    addChild(createAll());
  }
  
  // Default datas:
  // *******************************************************************************************

  // This function creates the appearance for the object.
  protected Appearance createTavoloAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    material.setEmissiveColor(COLOR_BLACK);
    material.setAmbientColor(COLOR_BLACK);
    material.setDiffuseColor(COLOR_BLACK);
    material.setSpecularColor(COLOR_LIGHT_BLACK);
    material.setLightingEnable(true);
    appearance.setMaterial(material);
    // set coloring attributes
    ColoringAttributes coloringAttributes = new ColoringAttributes();
    coloringAttributes.setColor(COLOR_BLACK);
    appearance.setColoringAttributes(coloringAttributes);
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // return the appearance
    return appearance;
  }

  // This function creates the appearance for the object.
  protected Appearance createBaseMacAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    material.setLightingEnable(true);
    appearance.setMaterial(material);
    // add the texture
    addTextureToAppearance(appearance, TEXTURE_IPEC);
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // return the appearance
    return appearance;
  }

  // This function creates the appearance for the object.
  protected Appearance createSchermoAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    material.setLightingEnable(true);
    appearance.setMaterial(material);
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // return the appearance
    return appearance;
  }

  // This function creates the bounding sphere for the object.
  protected BoundingSphere createBoundingSphere() {
    return new BoundingSphere(new Point3d(), 10.0d);
  }

  // Elements:
  // *******************************************************************************************

  protected TransformGroup createAll() {
    TransformGroup tg = new TransformGroup();

    // add object to tg
    tg.addChild(createTavolo());
    tg.addChild(createBaseMac());
    tg.addChild(createBraccioCilindro1());
    tg.addChild(createBraccioSfera1());
    tg.addChild(createBraccioCilindro2());
    tg.addChild(createBraccioSfera2());
    tg.addChild(createSwitchingSchermo());

    // add a rotation
    addRotationToTG(tg, 4.0f);
    
    // return tg
    return tg;
  }

  // Tavolo.
  protected TransformGroup createTavolo() {
    TransformGroup tg = new TransformGroup();

    Box box = new Box(
      this.size / 2f,
      this.size,
      this.size / 2f,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.tavoloAppearance
    );
    tg.addChild(box);

    return tg;
  }

  // Base Mac.
  protected TransformGroup createBaseMac() {
    TransformGroup tg = new TransformGroup();

    Sphere sphere = new Sphere(
      this.size / 4f,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.baseMacAppearance
    );
    tg.addChild(sphere);

    // add transformations
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      this.size,
      0.0f
    ));
    tg.setTransform(translate);

    return tg;
  }

  // Braccio cilindro 1.
  protected TransformGroup createBraccioCilindro1() {
    TransformGroup tg = new TransformGroup();

    Cylinder cylinder = new Cylinder(
      this.size / 40f,
      this.size / 10f,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.tavoloAppearance // riciclo appearance tavolo
    );
    tg.addChild(cylinder);

    // add transformations
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      (this.size + this.size / 4f + this.size / 30f),
      0.0f
    ));
    tg.setTransform(translate);

    return tg;
  }

  // Braccio sfera 1.
  protected TransformGroup createBraccioSfera1() {
    TransformGroup tg = new TransformGroup();

    Sphere sphere = new Sphere(
      this.size / 16f,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.tavoloAppearance // riciclo appearance tavolo
    );
    tg.addChild(sphere);

    // add transformations
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      (this.size + this.size / 4f + this.size / 30f + this.size / 16f),
      0.0f
    ));
    tg.setTransform(translate);

    return tg;
  }

  // Braccio cilindro 2.
  protected TransformGroup createBraccioCilindro2() {
    TransformGroup tg = new TransformGroup();

    Cylinder cylinder = new Cylinder(
      this.size / 25f,
      this.size / 5f,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.tavoloAppearance // riciclo appearance tavolo
    );
    tg.addChild(cylinder);

    // add transformations
    Transform3D translate = new Transform3D();
    Transform3D rotation = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      (this.size + this.size / 4f + this.size / 30f + this.size / 16f + this.size / 10f),
      2 * this.size / 25f
    ));
    rotation.rotX(Math.PI / 4);
    translate.mul(rotation);
    tg.setTransform(translate);

    return tg;
  }

  // Braccio sfera 2.
  protected TransformGroup createBraccioSfera2() {
    TransformGroup tg = new TransformGroup();

    Sphere sphere = new Sphere(
      this.size / 16f,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.tavoloAppearance // riciclo appearance tavolo
    );
    tg.addChild(sphere);

    // add transformations
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      (this.size + this.size / 4f + this.size / 30f + this.size / 16f + this.size / 6f),
      2 * this.size / 25f + this.size / 16f
    ));
    tg.setTransform(translate);

    return tg;
  }

  // Schermo switch.
  protected TransformGroup createSwitchingSchermo() {
    TransformGroup tg = new TransformGroup();

    // create switch object
    Switch objSwitch= new Switch();
    objSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);

    // create switch interpolator
    Alpha alpha=new Alpha(-1,Alpha.INCREASING_ENABLE+Alpha.DECREASING_ENABLE,0,0,2000,0,1000,2000,0,1000);
    SwitchValueInterpolator switching = new SwitchValueInterpolator(alpha, objSwitch);
    switching.setLastChildIndex(2);

    // set bound
    switching.setSchedulingBounds(this.bound);

    // select children
    objSwitch.addChild(createSchermo(0));
    objSwitch.addChild(createSchermo(1));
    objSwitch.addChild(createSchermo(2));
    tg.addChild(objSwitch);
    tg.addChild(switching);

    return tg;
  }

  // Schermo.
  protected TransformGroup createSchermo(int number) {
    TransformGroup tg = new TransformGroup();

    // add the texture
    String texture = number == 1 ? TEXTURE_SCHERMO_1 : (number == 2 ? TEXTURE_SCHERMO_2 : TEXTURE_SCHERMO_3);
    Appearance customAppearance = new Appearance();
    customAppearance.duplicateNodeComponent(this.schermoAppearance, true);
    addTextureToAppearance(customAppearance, texture);

    Box box = new Box(
      this.size / 2f,
      this.size / 4f,
      this.size / 50f,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      customAppearance
    );
    tg.addChild(box);

    // add transformations
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      (this.size + this.size / 4f + this.size / 30f + this.size / 16f + this.size / 6f),
      2 * this.size / 25f + this.size / 16f + 2 * this.size / 50f
    ));
    tg.setTransform(translate);

    return tg;
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

  // This function add a texture to an appearance object.
  protected void addTextureToAppearance(Appearance appearance, String texturePath) {
    // load texture file
    TextureLoader textureLoader = new TextureLoader(texturePath, null);
    // set texture
    Texture texture = textureLoader.getTexture();
    texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
    // add texture to the appearance
    appearance.setTexture(texture);
    // initialize texture attributes
    TextureAttributes textureAttributes = new TextureAttributes();
	  textureAttributes.setTextureMode(TextureAttributes.COMBINE);
    textureAttributes.setPerspectiveCorrectionMode(TextureAttributes.NICEST);
    appearance.setTextureAttributes(textureAttributes);
  }

}
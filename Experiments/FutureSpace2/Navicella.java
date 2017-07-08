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

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Box;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.image.TextureLoader;

// import ColorCube as default child
import com.sun.j3d.utils.geometry.ColorCube;

class Navicella extends Group implements InterfaceTextures, InterfaceColors {

  protected Appearance appearance;
  protected BoundingSphere bound;
  protected float size;

  // Constructors:
  // *******************************************************************************************

  public Navicella(float length) {
    this(length, null, null);
  }

  public Navicella(float length, BoundingSphere bound) {
    this(length, bound, null);
  }

  public Navicella(float length, BoundingSphere bound, Appearance appearance) {
    // initial settings
    if (appearance == null) {
      this.appearance = createAppearance();
    }
    if (bound == null) {
      this.bound = createBoundingSphere();
    }
    this.size = length / 8;
    // add children
    addChild(createCorpoCentrale());
    addChild(createMotoreCentraleCilindro());
    addChild(createMotoreCentraleEchino());
    addChild(createCollegamentoMotori());
    addChild(createMotore("left"));
    addChild(createMotore("right"));
    addChild(createChiusura());
  }
  
  // Default datas:
  // *******************************************************************************************

  // This function creates the appearance for the object.
  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    // material.setEmissiveColor(COLOR_GREY); // light: not illuminated zone | no_light: object color
    // material.setAmbientColor(COLOR_GREY); // light: not illuminated zone | no_light: partial object color
    // material.setDiffuseColor(COLOR_GREY); // light: illuminated zone | no_light: none
    // material.setSpecularColor(COLOR_GREY); // light: reflection | no_light: none
    // material.setShininess(80.0f);
    // material.setLightingEnable(true);
    appearance.setMaterial(material);
    // add trasparency
    // TransparencyAttributes transparencyAttributes = new TransparencyAttributes();
    // transparencyAttributes.setTransparencyMode(TransparencyAttributes.BLENDED);
    // transparencyAttributes.setTransparency(0.5f);
    // appearance.setTransparencyAttributes(transparencyAttributes);
    // add texture
    addTextureToAppearance(appearance, TEXTURE_HOPE);
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

  protected TransformGroup createCorpoCentrale() {
    TransformGroup tg = new TransformGroup();

    Sphere sphere = new Sphere(
      this.size * 2, // radius size * 2
      -Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,
      100,
      this.appearance
    );
    tg.addChild(sphere);

    // return tg
    return tg;
  }

  protected TransformGroup createMotoreCentraleCilindro() {
    TransformGroup tg = new TransformGroup();

    Cylinder cylinder = new Cylinder(
      this.size / 2,
      this.size / 10,
      -Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    tg.addChild(cylinder);

    // add transformations
    Transform3D rotation = new Transform3D();
    rotation.rotX(Math.PI / 2);
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, 0.0f, this.size * 2));
    translate.mul(rotation);
    tg.setTransform(translate);
    
    // return tg
    return tg;
  }

  protected TransformGroup createMotoreCentraleEchino() {
    TransformGroup tg = new TransformGroup();

    MyCylinder cylinder = new MyCylinder(
      100,
      this.size,
      this.size / 2,
      this.size * 4 - this.size / 10,
      this.appearance
    );
    tg.addChild(cylinder);

    // add transformations
    Transform3D rotation = new Transform3D();
    rotation.rotX(Math.PI / 2);
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, 0.0f, this.size * 4));
    translate.mul(rotation);
    tg.setTransform(translate);
    
    // return tg
    return tg;
  }

  protected TransformGroup createCollegamentoMotori() {
    TransformGroup tg = new TransformGroup();

    Cylinder cylinder = new Cylinder(
      this.size / 2,
      this.size * 5,
      -Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    tg.addChild(cylinder);

    // add transformations
    Transform3D rotation = new Transform3D();
    rotation.rotZ(Math.PI / 2);
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, 0.0f, this.size * 4));
    translate.mul(rotation);
    tg.setTransform(translate);
    
    // return tg
    return tg;
  }

  protected TransformGroup createMotore(String type) {
    TransformGroup tg = new TransformGroup();

    Box box = new Box(
      this.size / 2,
      this.size / 2,
      this.size,
      -Box.GENERATE_NORMALS|Box.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    tg.addChild(box);

    // set x translation sign
    float sign;
    if (type == "left") {
      sign = -1.0f;
    } else {
      sign = +1.0f;
    }

    // add transformations
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(sign * this.size * 2.5f, 0.0f, this.size * 4));
    tg.setTransform(translate);
    
    // return tg
    return tg;
  }

  protected TransformGroup createChiusura() {
    TransformGroup tg = new TransformGroup();

    Cylinder cylinder = new Cylinder(
      this.size,
      this.size / 10,
      -Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    tg.addChild(cylinder);

    // add transformations
    Transform3D rotation = new Transform3D();
    rotation.rotX(Math.PI / 2);
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, 0.0f, this.size * 6));
    translate.mul(rotation);
    tg.setTransform(translate);
    
    // return tg
    return tg;
  }

  // Helpers:
  // *******************************************************************************************

  // // This function add a rotation to a transformgroup object.
  // protected void addRotationToTG(TransformGroup tg, float rotation) {
  //   // create transformation for the sphere rotation
  //   Transform3D sphereRotation = new Transform3D();
	// 	sphereRotation.rotY(- Math.PI / 4.0f);
  //   // create alpha
	// 	Alpha alpha = new Alpha(-1, 50000);
  //   // create rotation interpolator
	// 	RotationInterpolator sphereRotationInterpolator = new RotationInterpolator(
  //     alpha,
  //     tg,
  //     sphereRotation,
  //     0.0f,
  //     (float) Math.PI * rotation
  //   );
  //   // create bounding sphere and set to interpolator
	// 	sphereRotationInterpolator.setSchedulingBounds(this.bound);
  //   // permit rotation after the creation
	// 	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  //   // add sphere as tg child
  //   tg.addChild(sphereRotationInterpolator);
  // }

  // This function add a texture to an appeance object.
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
    // initialize and add text coordinates generator
    TexCoordGeneration tcg = new TexCoordGeneration(
      TexCoordGeneration.OBJECT_LINEAR,
      TexCoordGeneration.TEXTURE_COORDINATE_3
    );
	  appearance.setTexCoordGeneration(tcg);
  }

}
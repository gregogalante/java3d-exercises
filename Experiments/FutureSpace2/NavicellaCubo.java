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

import javax.vecmath.Point3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;

import com.sun.j3d.utils.image.TextureLoader;

// import ColorCube as default child
import com.sun.j3d.utils.geometry.ColorCube;

class NavicellaCubo extends Group implements InterfaceTextures, InterfaceColors {

  protected Appearance appearance;
  protected BoundingSphere bound;
  protected float lato;

  // Constructors:
  // *******************************************************************************************

  public NavicellaCubo(float lato) {
    this(lato, null, null);
  }

  public NavicellaCubo(float lato, BoundingSphere bound) {
    this(lato, bound, null);
  }

  public NavicellaCubo(float lato, BoundingSphere bound, Appearance appearance) {
    // initial settings
    if (appearance == null) {
      this.appearance = createAppearance();
    } else {
      this.appearance = appearance;
    }
    if (bound == null) {
      this.bound = createBoundingSphere();
    } else {
      this.bound = bound;
    }
    this.lato = lato / 4;
    // add children
    addChild(createCubo());
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
    addTextureToAppearance(appearance, TEXTURE_BORG);
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

  protected TransformGroup createCubo() {
    TransformGroup tg = new TransformGroup();

    Box box = new Box(
      this.lato,
      this.lato,
      this.lato,
      -Box.GENERATE_NORMALS|Box.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    tg.addChild(box);

    // add rotation
    addRotationToTG(tg, 2.0f);
    // return tg
    return tg;
  }

  // Helpers:
  // *******************************************************************************************

  // This function add a rotation to a transformgroup object.
  protected void addRotationToTG(TransformGroup tg, float rotation) {
    // create transformation for the sphere rotation
    Transform3D sphereRotation = new Transform3D();
		sphereRotation.rotZ(- Math.PI / 4.0f);
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
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

import javax.vecmath.Point3d;

import com.sun.j3d.utils.image.TextureLoader;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Box;

class MyGroup extends Group implements InterfaceTextures, InterfaceColors {

  protected Appearance appearance;
  protected BoundingSphere bound;
  protected float size;

  // Constructors:
  // *******************************************************************************************

  public MyGroup(float size) {
    this(size, null, null);
  }

  public MyGroup(float size, BoundingSphere bound) {
    this(size, bound, null);
  }

  public MyGroup(float size, BoundingSphere bound, Appearance appearance) {
    // initial settings
    this.appearance = (appearance == null) ? createAppearance() : appearance;
    this.bound = (bound == null) ? createBoundingSphere() : bound;
    this.size = size;
    // add children
    addChild(createSomething());
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
    // add the texture
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

  protected TransformGroup createSomething() {
    TransformGroup tg = new TransformGroup();

    // Use a box as example
    Box box = new Box(
      this.size,
      this.size,
      this.size,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    // add object to tg
    tg.addChild(box);

    // add transformations
    Transform3D t = new Transform3D();
    tg.setTransform(t);

    // add a rotation
    // addRotationToTG(tg, 1.0f);
    
    // return tg
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
    // initialize and add text coordinates generator
    // TexCoordGeneration tcg = new TexCoordGeneration(
    //   TexCoordGeneration.OBJECT_LINEAR,
    //   TexCoordGeneration.TEXTURE_COORDINATE_3
    // );
	  // appearance.setTexCoordGeneration(tcg);
  }

}
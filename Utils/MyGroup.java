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

import javax.vecmath.Point3d;

import com.sun.j3d.utils.image.TextureLoader;

// import ColorCube as default child
import com.sun.j3d.utils.geometry.ColorCube;

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
    if (appearance == null) {
      this.appearance = createAppearance();
    }
    if (bound == null) {
      this.bound = createBoundingSphere();
    }
    this.size = size;
    // add children
    addChild(new ColorCube(this.size));
  }
  
  // Default datas:
  // *******************************************************************************************

  // This function creates the appearance for the object.
  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    material.setShininess(80.0f);
	  material.setSpecularColor(COLOR_WHITE);
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
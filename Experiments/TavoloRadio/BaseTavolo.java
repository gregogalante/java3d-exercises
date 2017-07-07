import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TexCoordGeneration;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.geometry.Cylinder;

class BaseTavolo extends Group implements InterfaceTextures, InterfaceColors {

  protected Appearance appearance;
  protected float size;

  // Constructors:
  // *******************************************************************************************

  public BaseTavolo(float size) {
    this(size, null);
  }

  public BaseTavolo(float size, Appearance appearance) {
    // initial settings
    if (appearance == null) {
      this.appearance = createAppearance();
    }
    this.size = size;
    // add children
    addChild(createCilindro());
    addChild(createEchino());
  }
  
  // Default datas:
  // *******************************************************************************************

  // This function creates the appearance for the object.
  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    appearance.setMaterial(material);
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // add texture
    addTextureToAppearance(appearance, TEXTURE_MARMO_PANNA);
    // return the appearance
    return appearance;
  }

  // Elements:
  // *******************************************************************************************

  protected TransformGroup createCilindro() {
    TransformGroup tg = new TransformGroup();
    Cylinder cylinder = new Cylinder(
      this.size,
      this.size * 4,
      -Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    tg.addChild(cylinder);
    return tg;
  }

  protected TransformGroup createEchino() {
    TransformGroup tg = new TransformGroup();

    MyCylinder cylinder = new MyCylinder(
      this.size,
      this.size * 1.25f,
      this.size,
      this.appearance
    );
    tg.addChild(cylinder);

    // add transformations
    Transform3D t = new Transform3D();
    t.setTranslate(new Vector3d(0.0f, this.size * 4, 0.0f));
    tg.setTransform(t);
    // return tg
    return tg;
  }

  // Helpers:
  // *******************************************************************************************

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
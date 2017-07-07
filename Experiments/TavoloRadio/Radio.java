import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransparencyAttributes;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.geometry.Box;

// import ColorCube as default child
import com.sun.j3d.utils.geometry.ColorCube;

class Radio extends Group implements InterfaceTextures, InterfaceColors {

  protected Appearance latoAppearance;
  protected float size;

  // Constructors:
  // *******************************************************************************************

  public Radio(float size) {
    // initial settings
    this.latoAppearance = createLatoAppearance();
    this.size = size;
  }
  
  // Default datas:
  // *******************************************************************************************

  // This function creates the appearance for the object.
  protected Appearance createLatoAppearance() {
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
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // add appearance
    addTextureToAppearance(appearance, TEXTURE_LEGNO);
    // return the appearance
    return appearance;
  }

  // Elements:
  // *******************************************************************************************

  // TODO

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
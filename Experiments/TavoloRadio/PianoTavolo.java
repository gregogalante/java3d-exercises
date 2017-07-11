import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.TransparencyAttributes;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.geometry.Cylinder;

class PianoTavolo extends Group implements InterfaceTextures, InterfaceColors {

  protected Appearance appearance;
  protected float size;

  // Constructors:
  // *******************************************************************************************

  public PianoTavolo(float size) {
    this(size, null);
  }

  public PianoTavolo(float size, Appearance appearance) {
    // initial settings
    if (appearance == null) {
      this.appearance = createAppearance();
    }
    this.size = size;
    // add children
    addChild(createPiano());
  }
  
  // Default datas:
  // *******************************************************************************************

  // This function creates the appearance for the object.
  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    material.setEmissiveColor(COLOR_VIOLET); // light: not illuminated zone | no_light: object color
    material.setAmbientColor(COLOR_VIOLET); // light: not illuminated zone | no_light: partial object color
    material.setDiffuseColor(COLOR_VIOLET); // light: illuminated zone | no_light: none
    material.setSpecularColor(COLOR_VIOLET); // light: reflection | no_light: none
    // material.setShininess(80.0f);
    // material.setLightingEnable(true);
    appearance.setMaterial(material);
    // add trasparency
    TransparencyAttributes transparencyAttributes = new TransparencyAttributes();
    transparencyAttributes.setTransparencyMode(TransparencyAttributes.BLENDED);
    transparencyAttributes.setTransparency(0.4f);
    appearance.setTransparencyAttributes(transparencyAttributes);
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // return the appearance
    return appearance;
  }

  // Elements:
  // *******************************************************************************************

  protected TransformGroup createPiano() {
    TransformGroup tg = new TransformGroup();
    Cylinder cylinder = new Cylinder(
      this.size * 4,
      this.size / 2,
      -Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS,
      100,
      100,
      this.appearance
    );
    tg.addChild(cylinder);
    return tg;
  }
}
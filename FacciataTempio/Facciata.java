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

// Facciata class.
public class Facciata extends Group {

  protected TransformGroup scalinata;
  protected float scalinataSize;

  protected TransformGroup[] colonne;
  
  protected Appearance facciataAppearance;

  public Facciata() {
    this.facciataAppearance = createAppearance();
    this.scalinata = createScalinata();
    this.colonne = new TransformGroup[6];
    for (int i = 0; i < 6; i++) {
      this.colonne[i] = createColonna(i);
      addChild(this.colonne[i]);
    }
    addChild(this.scalinata);
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

  protected TransformGroup createScalinata() {
    TransformGroup tg = new TransformGroup();
    Scalinata scalinata = new Scalinata(2.75f, 1.0f, 3, this.facciataAppearance);
    this.scalinataSize = scalinata.getSize();
    tg.addChild(scalinata);
    return tg;
  }

  protected TransformGroup createColonna(int numColonna) {
    TransformGroup tg = new TransformGroup();
    Colonna colonna = new Colonna(2.0f, this.facciataAppearance);
    tg.addChild(colonna);
    // add correct transform to tg
    // add correct transformation to tg
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      ((numColonna > 2) ? -(2.5 - numColonna) : (numColonna - 2.5)),
      (this.scalinataSize * 3) + 1,
      0.0f
    ));
    tg.setTransform(translate);
    return tg;
  }

}
  
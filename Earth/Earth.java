import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.Material;
import javax.media.j3d.TextureAttributes;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import javax.vecmath.Color3f;

class Earth extends Group {
  
  protected float radius;
  protected Appearance appearance;
  protected TransformGroup sphere;

  public Earth(float radius) {
    this.radius = radius;
    this.appearance = createAppearance();
    this.sphere = createSphere();
    addChild(sphere);
  }

  // This function create a new sphere used as earth.
  protected TransformGroup createSphere() {
    TransformGroup tg = new TransformGroup();
    Sphere sphere = new Sphere(
      this.radius,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      100,
      this.appearance
    );
    tg.addChild(sphere);
    return tg;
  }

  // This function create a new appearance for the earth.
  protected Appearance createAppearance() {
    // initialize the appearance
    Appearance app = new Appearance();
    // create material
    Material material = new Material();
    material.setShininess(80.0f);
	  material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
    app.setMaterial(material);
    // load texture file
    TextureLoader textureLoader = new TextureLoader("../images/earth.jpg", null);
    // initialize texture object
    Texture texture = textureLoader.getTexture();
    // add texture to the appearance
    app.setTexture(texture);
    // initialize texture attributes
    TextureAttributes textureAttributes = new TextureAttributes();
	  textureAttributes.setTextureMode(TextureAttributes.COMBINE);
    app.setTextureAttributes(textureAttributes);
    // return the appearance
    return app;
  }

}
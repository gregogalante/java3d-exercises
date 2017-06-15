import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.Material;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

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
    Sphere sphere = new Sphere(this.radius, Primitive.GENERATE_TEXTURE_COORDS, this.appearance);
    tg.addChild(sphere);
    return tg;
  }

  // This function create a new appearance for the earth.
  protected Appearance createAppearance() {
    // initialize the appearance
    Appearance app = new Appearance();
    // load texture file
    TextureLoader textureLoader = new TextureLoader("earth.jpg", null);
    // initialize texture object
    Texture texture = textureLoader.getTexture();
    // add texture to the appearance
    app.setTexture(texture);
    // create material
    Material material = new Material();
    app.setMaterial(material);
    // return the appearance
    return app;
  }

}
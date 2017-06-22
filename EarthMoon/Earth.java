import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.Material;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Transform3D;

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
    addChild(this.sphere);
  }

  // This function creates a new sphere used as earth.
  protected TransformGroup createSphere() {
    TransformGroup tg = new TransformGroup();
    Sphere sphere = new Sphere(
      this.radius,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      100,
      this.appearance
    );
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
      (float) Math.PI * 2.0f
    );
    // create bounding sphere and set to interpolator
		BoundingSphere sphereBoundingSphere = new BoundingSphere();
		sphereRotationInterpolator.setSchedulingBounds(sphereBoundingSphere);
    // permit rotation after the creation
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    // add sphere as tg child
    tg.addChild(sphereRotationInterpolator);
		tg.addChild(sphere);
    // return tg
    return tg;
  }

  // This function creates a new appearance for the earth.
  protected Appearance createAppearance() {
    // initialize the appearance
    Appearance app = new Appearance();
    // create material
    Material material = new Material();
    material.setShininess(80.0f);
	  material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
    app.setMaterial(material);
    // load texture file
    TextureLoader textureLoader = new TextureLoader("earth.jpg", null);
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
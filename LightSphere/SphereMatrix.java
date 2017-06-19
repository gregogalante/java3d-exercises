import javax.vecmath.Point3d;
import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Material;
import javax.vecmath.Vector3d;

public class SphereMatrix extends Group {

  protected TransformGroup spheres = new TransformGroup();
  protected Appearance appearance;
  protected float radius;
  protected int number;

  public SphereMatrix(int sphereNumber) {
    if (sphereNumber < 1) {
      throw new RuntimeException("sphereNumber should be one or more");
    }
    this.number = sphereNumber;
    // initialize appearance
    this.appearance = createAppearance();
    // initialize spheres radius
    this.radius = createRadius();
    // create spheres
    for (int y = 0; y < number; y++) {
      for (int x = 0; x < number; x++) {
        spheres.addChild(createSphereTG(x, y));
      }
    }
    // add spheres as group child
    Transform3D translate = new Transform3D();
    float t = -((3 * ((float) number / 2.0f) * radius) - (float) radius * 1.5f); // TODO: Rivedere
    translate.setTranslation(new Vector3d(t, t, 0));

    spheres.setTransform(translate);
    addChild(spheres);
  }

  protected float createRadius() {
    return (1.0f / ((float) number * 2.0f));
  }

  protected Appearance createAppearance() {
    Appearance app = new Appearance();
    Material material = new Material();
    app.setMaterial(material);
    return app;
  }

  protected TransformGroup createSphereTG(int x, int y) {
    TransformGroup tg = new TransformGroup();
    Sphere s = new Sphere(radius, Primitive.GEOMETRY_NOT_SHARED|Primitive.GENERATE_NORMALS, appearance);
    Transform3D t3d = new Transform3D();
    t3d.setTranslation(calculateSphereTranslation(x, y));
    tg.setTransform(t3d);
    tg.addChild(s);
    return tg;
  }

  protected Vector3d calculateSphereTranslation(int x, int y) {
    Float vx = 3 * x * radius;
    Float vy = 3 * y * radius;
    // return vector
    return new Vector3d(vx, vy, 0);
  }

}
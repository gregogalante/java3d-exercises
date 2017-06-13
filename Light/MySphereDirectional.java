import javax.vecmath.Point3d;
import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Material;

public class MySphereDirectional extends Group {
  static final protected Appearance appearance = new Appearance();

  protected TransformGroup tg = new TransformGroup();
  protected Sphere sphere = new Sphere(0.5f, Primitive.GEOMETRY_NOT_SHARED|Primitive.GENERATE_NORMALS, appearance);

  public MySphereDirectional() {
    // create bound for light
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.d,0.d,0.d), 10.d);
    // create light
    DirectionalLight light = new DirectionalLight();
    light.setDirection(0.1f, 0.1f, 0.1f);
    // add color to light
    Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
    light.setColor(green);
    // add bounds to light
    light.setInfluencingBounds(bounds);
    // create material for appearance
    Material material = new Material();
    // add material to appearance
		appearance.setMaterial(material);
    // add light to tg
    tg.addChild(light);
    // add sphere to tg
    tg.addChild(sphere);
    // add tg to group
    addChild(tg);
  }

}
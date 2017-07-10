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
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.ColoringAttributes;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.image.TextureLoader;

import com.sun.j3d.utils.geometry.Box;

class BoxPyramid extends Group implements InterfaceTextures, InterfaceColors {

  protected Appearance appearance;
  protected float width;
  protected float length;
  protected float height;
  protected float percentageChange;
  protected int floorsNumber;

  // Constructors:
  // *******************************************************************************************

  public BoxPyramid(float width, float length, float height, int floorsNumber) {
    this(width, length, height, 0.0f, floorsNumber, null);
  }

  public BoxPyramid(float width, float length, float height, float percentageChange, int floorsNumber) {
    this(width, length, height, percentageChange, floorsNumber, null);
  }

  public BoxPyramid(float width, float length, float height, float percentageChange, int floorsNumber, Appearance appearance) {
    // initial settings
    this.appearance = (appearance == null) ? createAppearance() : appearance;
    this.width = width;
    this.length = length;
    this.height = height;
    this.percentageChange = percentageChange;
    this.floorsNumber = floorsNumber;
    // add children
    createFloors();
  }
  
  // Default datas:
  // *******************************************************************************************

  // This function creates the appearance for the object.
  protected Appearance createAppearance() {
    Appearance app = new Appearance();
    // set polygon attributes
    PolygonAttributes polyAttrbutes = new PolygonAttributes();
    polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
    app.setPolygonAttributes(polyAttrbutes);
    // set coloring attributes
    ColoringAttributes color = new ColoringAttributes();
		color.setColor(COLOR_WHITE);
		app.setColoringAttributes(color);
    return app;
  }

  // Elements:
  // *******************************************************************************************

  protected void createFloors() {
    for (int floor = 0; floor < this.floorsNumber; floor++) {
      addChild(createFloor(floor));
    }
  }

  protected TransformGroup createFloor(int floorNumber) {
    TransformGroup tg = new TransformGroup();
    
    float height = this.height / (float) this.floorsNumber;
    float bottomWidth = this.width * (this.floorsNumber - floorNumber) / (float) this.floorsNumber;
    float bottomLength = this.length * (this.floorsNumber - floorNumber) / (float) this.floorsNumber;
    float topWidth = bottomWidth - (this.width / (float) this.floorsNumber) * this.percentageChange;
    float toplength = bottomLength - (this.height / (float) this.floorsNumber) * this.percentageChange;

    // float topWidth, float topLength, float bottomWidth, float bottomLength, float height, Appearance appearance
    MyBox box = new MyBox(
      topWidth,
      toplength,
      bottomWidth,
      bottomLength,
      height,
      this.appearance
    );
    tg.addChild(box);
    
    Transform3D t = new Transform3D();
    t.setTranslation(new Vector3d(0.0f, height * floorNumber, 0.0f));
    tg.setTransform(t);

    return tg;
  }

}
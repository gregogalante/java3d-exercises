import javax.vecmath.Point3d;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TriangleStripArray;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.ColoringAttributes;

import javax.vecmath.Point3d;

public class MyBox extends Shape3D {

  protected Point3d [] p = new Point3d[17];
  protected TriangleStripArray triangleStrip = null;

  public MyBox(float topWidth, float topLength, float bottomWidth, float bottomLength, float height, Appearance appearance) {
    if (appearance == null) {
      appearance = createAppearance();
    }
    // define points
    p[0] = new Point3d(-(bottomWidth / 2), -(height / 2), +(bottomLength / 2));
    p[1] = new Point3d(+(bottomWidth / 2), -(height / 2), +(bottomLength / 2));
    p[2] = new Point3d(-(bottomWidth / 2), -(height / 2), -(bottomLength / 2));
    p[3] = new Point3d(+(bottomWidth / 2), -(height / 2), -(bottomLength / 2));
    p[4] = new Point3d(-(topWidth / 2), +(height / 2), -(topLength / 2));
    p[5] = new Point3d(+(topWidth / 2), +(height / 2), -(topLength / 2));
    p[6] = new Point3d(-(topWidth / 2), +(height / 2), +(topLength / 2));
    p[7] = new Point3d(+(topWidth / 2), +(height / 2), +(topLength / 2));
    p[8] = p[0];
    p[9] = p[1];
    p[10] = p[3];
    p[11] = p[7];
    p[12] = p[5];
    p[13] = p[6];
    p[14] = p[4];
    p[15] = p[0];
    p[16] = p[2];
    // set geometry
    int [] stripCounts = {(p.length)};
		triangleStrip = new TriangleStripArray(p.length, GeometryArray.COORDINATES, stripCounts);
		triangleStrip.setCoordinates(0, p);
		setGeometry(triangleStrip);
    // manage appearance
    setAppearance(appearance);
  }

  // This function create a default appearance for the box.
  protected Appearance createAppearance() {
    Appearance app = new Appearance();
    // set polygon attributes
    PolygonAttributes polyAttrbutes = new PolygonAttributes();
    polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
    app.setPolygonAttributes(polyAttrbutes);
    // set coloring attributes
    ColoringAttributes color = new ColoringAttributes();
		color.setColor(0.6f, 0.4f, 0.3f);
		app.setColoringAttributes(color);
    return app;
  }

}
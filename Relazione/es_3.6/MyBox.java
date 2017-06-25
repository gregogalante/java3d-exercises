import javax.vecmath.Point3d;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TriangleStripArray;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.ColoringAttributes;

import javax.vecmath.Point3d;

public class MyBox extends Shape3D {

  protected Point3d [] p = new Point3d[8];
  protected Point3d [] v = new Point3d[12];
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
    p[4] = new Point3d(-(topWidth / 2), -(height / 2), +(topLength / 2));
    p[5] = new Point3d(+(topWidth / 2), -(height / 2), +(topLength / 2));
    p[6] = new Point3d(-(topWidth / 2), -(height / 2), -(topLength / 2));
    p[7] = new Point3d(+(topWidth / 2), -(height / 2), -(topLength / 2));
    // define vectors
    v[0] = p[0]; v[1] = p[1]; v[2] = p[2];
    v[3] = p[1]; v[4] = p[2]; v[5] = p[3];
    v[6] = p[4]; v[7] = p[5]; v[8] = p[6];
    v[9] = p[5]; v[10] = p[6]; v[11] = p[7];
    // v[12] = p3; v[13] = p6; v[14] = p4;
    // v[15] = p3; v[16] = p5; v[17] = p6;
    // v[18] = p1; v[19] = p5; v[20] = p3;
    // v[21] = p2; v[22] = p4; v[23] = p6;
    // v[24] = p2; v[25] = p4; v[26] = p6;
    // v[27] = p2; v[28] = p4; v[29] = p6;
    // v[30] = p2; v[31] = p4; v[32] = p6;
    // v[33] = p2; v[34] = p4; v[35] = p6;
    // set geometry

    // manage appearance
    setAppearance(appearance);
  }

  // This function create a default appearance for the box.
  protected Appearance createAppearance() {
    Appearance app = new Appearance();
    // set polygon attributes
    PolygonAttributes polyAttrbutes = new PolygonAttributes();
    polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
    app.setPolygonAttributes(polyAttrbutes);
    // set coloring attributes
    ColoringAttributes color = new ColoringAttributes();
		color.setColor(0.6f, 0.4f, 0.3f);
		app.setColoringAttributes(color);
    return app;
  }

}
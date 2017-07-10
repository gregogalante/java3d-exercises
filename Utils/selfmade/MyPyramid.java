import javax.vecmath.Point3d;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TriangleStripArray;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.ColoringAttributes;

import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.GeometryInfo;

import javax.vecmath.Point3d;

public class MyPyramid extends Shape3D implements InterfaceColors {
  
  protected Point3d [] points = new Point3d[5];
  protected Point3d [] vectors = new Point3d[10];
  protected TriangleStripArray triangleStrip = null;

  public MyPyramid(float width, float length, float height) {
    this(width, length, height, null);
  }

  public MyPyramid(float width, float length, float height, Appearance appearance) {
    // set appearance
    if (appearance == null) {
      appearance = createAppearance();
    }
    // define points
    points[0] = new Point3d(-(width / 2), -(height / 2), +(length / 2));
    points[1] = new Point3d(+(width / 2), -(height / 2), +(length / 2));
    points[2] = new Point3d(-(width / 2), -(height / 2), -(length / 2));
    points[3] = new Point3d(+(width / 2), -(height / 2), -(length / 2));
    points[4] = new Point3d(0, +(height / 2), 0);
    // define vectors
    vectors[0] = points[0]; vectors[1] = points[1]; vectors[2] = points[2];
    vectors[3] = points[3]; vectors[4] = points[4]; vectors[5] = points[1];
    vectors[6] = points[0]; vectors[7] = points[4]; vectors[8] = points[2];
    vectors[9] = points[3];

    // set geometry
    int [] stripCounts = {(vectors.length)};
		triangleStrip = new TriangleStripArray(vectors.length, GeometryArray.COORDINATES, stripCounts);
		triangleStrip.setCoordinates(0, vectors);
		setGeometry(triangleStrip);
    // set geometry info
    GeometryInfo gInfo = new GeometryInfo(triangleStrip);
    NormalGenerator normgen = new NormalGenerator();
    normgen.generateNormals(gInfo);
    setGeometry(gInfo.getGeometryArray());
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
		color.setColor(COLOR_WHITE);
		app.setColoringAttributes(color);
    return app;
  }

}
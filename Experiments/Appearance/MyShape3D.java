import javax.vecmath.Point3d;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Geometry;
import javax.media.j3d.Appearance;
import javax.media.j3d.TriangleArray;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.ColoringAttributes;

public class MyShape3D extends Shape3D {
  
  protected Geometry geometry;
  protected Appearance appearance;

  // Points for the figure
  private static final Point3d P1 = new Point3d(0.5, 0.5, 0.5);
  private static final Point3d P2 = new Point3d(-0.5, -0.5, 0.5);
  private static final Point3d P3 = new Point3d(-0.5, 0.5, -0.5);
  private static final Point3d P4 = new Point3d (0.5, -0.5, -0.5);

  // Define array of points 
  private static final Point3d [] faces = {
    P1,P3,P2, // face 1
    P1,P2,P4, // face 2
    P2,P4,P3, // face 3
    P1,P3,P4 // fase 4
  };

  public MyShape3D() {
    geometry = createGeometry();
    appearance = createAppearance();
    setGeometry(geometry);
    setAppearance(appearance);
  }

  // Manage geometry of object.
  protected Geometry createGeometry() {
    TriangleArray triangles ;
    triangles = new TriangleArray(faces.length, TriangleArray.COORDINATES);
    triangles.setCoordinates(0, faces);
    return triangles;
  }

  // Manage appearance of object.
  private Appearance createAppearance() {
    Appearance app = new Appearance();
    // set point attributes
    // PointAttributes pointAttributes = new PointAttributes(10.0f, true);
    // app.setPointAttributes(pointAttributes);

    // set line attributes
    // LineAttributes lineAttributes = new LineAttributes(10.0f, LineAttributes.PATTERN_SOLID, false);
    // app.setLineAttributes(lineAttributes);

    // set coloring attributes
    // ColoringAttributes coloringAttributes = new ColoringAttributes(0.100f, 0.100f, 0.100f, ColoringAttributes.FASTEST);
    // app.setColoringAttributes(coloringAttributes);

    // set polygon attributes
    PolygonAttributes polygonAttributes = new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0);
    app.setPolygonAttributes(polygonAttributes);
    return app;
  }

}
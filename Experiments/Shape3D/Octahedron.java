import javax.vecmath.Point3d;
import javax.media.j3d.TriangleArray;
import javax.media.j3d.Geometry;

public class Octahedron extends VisualObject {

  // Points for the figure
  private static final Point3d P1 = new Point3d( 0.0, 1.0, 0.0);
  private static final Point3d P2 = new Point3d(0.0, -1.0, 0.0);
  private static final Point3d P3 = new Point3d(-0.5, 0.0, -0.5);
  private static final Point3d P4 = new Point3d (0.5, 0.0, -0.5);
  private static final Point3d P5 = new Point3d (-0.5, 0.0, 0.5);
  private static final Point3d P6 = new Point3d (0.5, 0.0, 0.5);

  // Define array of points 
  private static final Point3d [] faces = {
    P1,P3,P4,
    P1, P3, P5,
    P1, P4, P6,
    P1, P5, P6,
    P2, P4, P3,
    P2, P3, P5,
    P2, P6, P5,
    P2, P6, P4
  };

  protected Geometry createGeometry() {
    TriangleArray triangles ;
    triangles = new TriangleArray(faces.length, TriangleArray.COORDINATES);
    triangles.setCoordinates(0, faces);
    return triangles;
  }

}
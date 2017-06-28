import javax.vecmath.Point3d;
import javax.media.j3d.TriangleArray;
import javax.media.j3d.Geometry;

public class Tetrahedron extends VisualObject {

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

  protected Geometry createGeometry() {
    TriangleArray triangles ;
    triangles = new TriangleArray(faces.length, TriangleArray.COORDINATES);
    triangles.setCoordinates(0, faces);
    return triangles;
  }

}
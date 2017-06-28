import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class MyCopertura extends Shape3D {

  protected Point3f v[] = new Point3f[24];
  protected TriangleStripArray triangleStrip = null;
  protected PolygonAttributes polyAttrbutes = new PolygonAttributes();

  protected Point3f p1, p2, p3, p4, p5, p6;

  // MyCopertura.
  public MyCopertura(float width, float height, float length, Appearance appearance) {

    p1 = new Point3f(
      -(width),
      0.0f,
      (length)
    );
    p2 = new Point3f(
      -(width),
      0.0f,
      -(length)
    );
    p3 = new Point3f(
      (width),
      0.0f,
      (length)
    );
    p4 = new Point3f(
      (width),
      0.0f,
      -(length)
    );
    p5 = new Point3f(
      0.0f,
      (height * 2),
      (length)
    );
    p6 = new Point3f(
      0.0f,
      (height * 2),
      -(length)
    );

    v[0] = p1; v[1] = p2; v[2] = p4;
    v[3] = p1; v[4] = p3; v[5] = p4;
    v[6] = p1; v[7] = p2; v[8] = p6;
    v[9] = p1; v[10] = p5; v[11] = p6;
    v[12] = p3; v[13] = p6; v[14] = p4;
    v[15] = p3; v[16] = p5; v[17] = p6;
    v[18] = p1; v[19] = p5; v[20] = p3;
    v[21] = p2; v[22] = p4; v[23] = p6;

    int [] stripCounts = {50};
    triangleStrip = new TriangleStripArray(
      50,
      GeometryArray.COORDINATES,
      stripCounts
    );
    triangleStrip.setCoordinates(0, v);
    
    GeometryInfo gInfo = new GeometryInfo(triangleStrip);
    NormalGenerator normgen = new NormalGenerator();
    normgen.generateNormals(gInfo);
    
    setGeometry(triangleStrip);
    setGeometry(gInfo.getGeometryArray());

    setAppearance(appearance);
  }
}
import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class MyCopertura extends Shape3D {

  protected Point3f v[] = new Point3f[8];
  protected TriangleStripArray triangleStrip = null;
  protected PolygonAttributes polyAttrbutes = new PolygonAttributes();

  // MyCopertura.
  public MyCopertura(float width, float height, float length, Appearance appearance) {
    float x, y, z;

    // define point 1
    x = -(width / 2.0f);
    y = 0.0f;
    z = (length / 2.0f);
    v[0] = new Point3f(x, y, z);

    // define point 2
    x = -(width / 2.0f);
    y = 0.0f;
    z = -(length / 2.0f);
    v[1] = new Point3f(x, y, z);

    // define point 3
    x = (width / 2.0f);
    y = 0.0f;
    z = (length / 2.0f);
    v[2] = new Point3f(x, y, z);

    // define point 4
    x = (width / 2.0f);
    y = 0.0f;
    z = -(length / 2.0f);
    v[3] = new Point3f(x, y, z);

    // define point 5
    x = (width / 2.0f);
    y = height;
    z = (length / 2.0f);
    v[4] = new Point3f(x, y, z);

    // define point 6
    x = (width / 2.0f);
    y = height;
    z = -(length / 2.0f);
    v[5] = new Point3f(x, y, z);

    // define point 7
    v[6] = v[0];

    // define point 8
    v[7] = v[1];

    int [] stripCounts = {18};
    triangleStrip = new TriangleStripArray(
      18,
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
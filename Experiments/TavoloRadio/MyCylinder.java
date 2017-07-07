import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class MyCylinder extends Shape3D implements InterfaceColors {

  protected Point3f v[] = null;
  protected TriangleStripArray triangleStrip = null;
  protected PolygonAttributes polyAttrbutes = new PolygonAttributes();

  public MyCylinder(float radius, float height) {
    this(20, radius, radius, height, null);
  }

  public MyCylinder(float radius, float height, Appearance appearance) {
    this(20, radius, radius, height, appearance);
  }

  public MyCylinder(float topRadius, float bottomRadius, float height, Appearance appearance) {
    this(20, topRadius, bottomRadius, height, appearance);
  }

  public MyCylinder(int steps, float topRadius, float bottomRadius, float height, Appearance appearance) {
    // set appearance
    if (appearance == null) {
      appearance = createAppearance();
    }
    // set top-bottom
    float top = height / 2;
    float bottom = -height / 2;
    // create vectors
    v = new Point3f[(steps + 1) * 2];
    for(int i = 0; i < steps; i++) {
      double angle = 2.0 * Math.PI * (double) i / (double) steps;
      float xInf = (float) Math.sin(angle) * bottomRadius;
      float yInf = (float) Math.cos(angle) * bottomRadius;
      float xSup = (float) Math.sin(angle) * topRadius;
      float ySup = (float) Math.cos(angle) * topRadius;
      v[i*2+0] = new Point3f(xInf, bottom, yInf);
      v[i*2+1] = new Point3f(xSup, top, ySup);
    }
    v[steps*2+0] = new Point3f(0.0f, bottom, bottomRadius);
    v[steps*2+1] = new Point3f(0.0f, top, topRadius);
    // create triangle strip
    int [] stripCounts ={(steps + 1) * 2};
    triangleStrip = new TriangleStripArray(
      (steps + 1) * 2,
      GeometryArray.COORDINATES,
      stripCounts
    );
    triangleStrip.setCoordinates(0, v);
    // create geometry
    GeometryInfo gInfo = new GeometryInfo(triangleStrip);
    NormalGenerator normgen = new NormalGenerator();
    normgen.generateNormals(gInfo);
    // set geometry
    setGeometry(triangleStrip);
    setGeometry(gInfo.getGeometryArray());
    // set appearance
    setAppearance(appearance);
  }

  // This function create a default appearance for the cylinder.
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
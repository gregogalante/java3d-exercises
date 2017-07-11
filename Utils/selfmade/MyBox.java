import javax.vecmath.Point3d;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TriangleStripArray;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.TexCoordGeneration;

import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.GeometryInfo;

import javax.vecmath.Point3d;

public class MyBox extends Shape3D implements InterfaceColors {
  
  protected Point3d [] points = new Point3d[8];
  protected Point3d [] vectors = new Point3d[14];
  protected TriangleStripArray triangleStrip = null;

  public MyBox(float width, float length, float height) {
    this(width, length, height, null);
  }

  public MyBox(float width, float length, float height, Appearance appearance) {
    this(width, length, width, length, height, appearance);
  }

  public MyBox(float topWidth, float topLength, float bottomWidth, float bottomLength, float height, Appearance appearance) {
    // set appearance
    if (appearance == null) {
      appearance = createAppearance();
    }
    // define points
    points[0] = new Point3d(-(topWidth / 2), +(height / 2), +(topLength / 2));
    points[1] = new Point3d(+(topWidth / 2), +(height / 2), +(topLength / 2));
    points[2] = new Point3d(-(bottomWidth / 2), -(height / 2), +(bottomLength / 2));
    points[3] = new Point3d(+(bottomWidth / 2), -(height / 2), +(bottomLength / 2));
    points[4] = new Point3d(+(topWidth / 2), +(height / 2), -(topLength / 2));
    points[5] = new Point3d(-(topWidth / 2), +(height / 2), -(topLength / 2));
    points[6] = new Point3d(+(bottomWidth / 2), -(height / 2), -(bottomLength / 2));
    points[7] = new Point3d(-(bottomWidth / 2), -(height / 2), -(bottomLength / 2));
    // define vectors
    vectors[0] = points[1]; vectors[1] = points[0]; vectors[2] = points[2];
    vectors[3] = points[5]; vectors[4] = points[7]; vectors[5] = points[6];
    vectors[6] = points[2]; vectors[7] = points[3]; vectors[8] = points[1];
    vectors[9] = points[6]; vectors[10] = points[4]; vectors[11] = points[5];
    vectors[12] = points[1]; vectors[13] = points[0];
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
    // initialize and add text coordinates generator
    TexCoordGeneration tcg = new TexCoordGeneration(
      TexCoordGeneration.OBJECT_LINEAR,
      TexCoordGeneration.TEXTURE_COORDINATE_3
    );
	  appearance.setTexCoordGeneration(tcg);
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
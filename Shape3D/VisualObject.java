import javax.media.j3d.Shape3D;
import javax.media.j3d.Geometry;
import javax.media.j3d.Appearance;
import javax.media.j3d.PolygonAttributes;

abstract class VisualObject extends Shape3D {

  protected Geometry geometry;
  protected Appearance appearance;

  // Set NodeComponents on costructor.
  public VisualObject() {
    geometry = createGeometry();
    appearance = createAppearance();
    setGeometry(geometry);
    setAppearance(appearance);
  }

  // Method to create a new geometry.
  protected abstract Geometry createGeometry();

  // Method to create an appereance.
  private Appearance createAppearance() {
    Appearance app = new Appearance() ;
    // POLYGON_LINE - the polygon is rendered as lines drawn between consecutive vertices.
    // CULL_BACK - culls all front-facing polygons. The default.
    app.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE,
                                                   PolygonAttributes.CULL_BACK,0));
    return app;
  }

}
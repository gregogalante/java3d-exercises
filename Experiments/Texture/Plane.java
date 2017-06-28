import javax.media.j3d.Group;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Appearance;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Texture2D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Texture;

import com.sun.j3d.utils.image.TextureLoader;

import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;

class Plane extends Group {

  TexCoord2f[] tc = new TexCoord2f[] {
    new TexCoord2f(-1.0f,1.5f),
    new TexCoord2f(-1.0f,-1.0f),
    new TexCoord2f(1.5f,-1.0f),
    new TexCoord2f(1.5f,1.5f)
  };

  public Plane() {
    // create plane geometry
    QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES|GeometryArray.TEXTURE_COORDINATE_2);
    Point3f p = new Point3f(-1.0f, 1.0f, 0.0f);
    plane.setCoordinate(0, p);
    p.set(-1.0f, -1.0f, 0.0f);
    plane.setCoordinate(1, p);
    p.set(1.0f, -1.0f, 0.0f);
    plane.setCoordinate(2, p);
    p.set(1.0f, 1.0f, 0.0f);
    plane.setCoordinate(3, p);
    // set texture coordinates
    for (int i = 0; i < this.tc.length; i++) plane.setTextureCoordinate(0, i, this.tc[i]);
    // create plane appearance
    Appearance app = new Appearance();
    // load texture
    TextureLoader loader = new TextureLoader("stripe.gif", null);
    ImageComponent2D image = loader.getImage();
    // create texture
    Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, image.getWidth(), image.getHeight());
    texture.setImage(0, image);
    texture.setBoundaryModeS(Texture.WRAP);
    texture.setBoundaryModeT(Texture.CLAMP);
    // set texture to appearance
    app.setTexture(texture);
    // create object and add to group
    Shape3D planeObj = new Shape3D(plane, app);
    TransformGroup tg= new TransformGroup();
    tg.addChild(planeObj);
    addChild(tg);
  }

}
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;

import com.sun.j3d.utils.image.TextureLoader;

import javax.vecmath.Point3d;

class BackSpace extends Group {

  public BackSpace() {
    TextureLoader myLoader = new TextureLoader("stars.jpg", null);
    ImageComponent2D myImage = myLoader.getImage();
    Background myBack = new Background();
    myBack.setImage(myImage);
    myBack.setImageScaleMode(Background.SCALE_FIT_MAX);
    BoundingSphere myBounds = new BoundingSphere(new Point3d(), 1000.0);
    myBack.setApplicationBounds(myBounds);
    addChild(myBack);
  }

}
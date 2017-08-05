import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Group;

import javax.vecmath.Vector3d;
import com.sun.j3d.utils.geometry.ColorCube;

public class ColorCubeCircle extends Group {

	protected int number;
	protected double theta;
	protected double size;
	
	public ColorCubeCircle(int number) {
    // check number is valid
    if (number < 1) {
      throw new RuntimeException("number should be one or more");
    }
    // set variables
		this.number = number;
    this.theta = calculateTheta();
    this.size = calculateSize();
    // create cubes
    for (int i = 0; i < this.number; i++) {
      createCube(i);
    }
	}

  protected double calculateTheta() {
    return (2 * Math.PI) / this.number;
  }

  protected double calculateSize() {
    return 1.0d / this.number;
  }

  protected void createCube(int position) {
    TransformGroup tg = new TransformGroup();
		Transform3D translate = new Transform3D();
		// define angle for the current counter
		double thetaCounter = position * this.theta;
		// define translation for transform3D
		translate.setTranslation(new Vector3d(
			Math.cos(thetaCounter + Math.PI / 2) / 2,
			Math.sin(thetaCounter + Math.PI / 2) / 2,
			0
		));
		// add transform3D to the transform
		tg.setTransform(translate);
		// add new Colorcube to transform
		tg.addChild(new ColorCube(this.size));
		addChild(tg);
  }

}
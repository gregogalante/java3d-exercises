// Import base dependencies.
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Appearance;

import com.sun.j3d.utils.geometry.Box;

// Scalinata class.
public class Scalinata extends Group {
  
  // dimensions
  protected float size;
  protected float baseWidth;
  protected float baseLength;
  // elements
  protected Appearance scalaAppearance;
  protected TransformGroup[] tgScale;

  public Scalinata(float x, float z, int numScale) {
    if (numScale < 1) {
      throw new RuntimeException("numScale should be one or more");
    }
    this.baseWidth = x;
    this.baseLength = z;
    this.size = calculateSize();
    this.scalaAppearance = createAppearance();
    this.tgScale = new TransformGroup[numScale];
    // create scale
    for (int i = 0; i < this.tgScale.length; i++) {
      this.tgScale[i] = createScala(i);
    }
    // add scale to group
    for (int i = 0; i < this.tgScale.length; i++) {
      addChild(this.tgScale[i]);
    }
  }

  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    return appearance;
  }

  protected TransformGroup createScala(int numScala) {
    TransformGroup tg = new TransformGroup();
    Box scala = new Box(
      calculateWidth(numScala),
      this.size,
      calculateLength(numScala),
      this.scalaAppearance
    );
    tg.addChild(scala);
    return tg;
  }

  protected float calculateWidth(int numScala) {
    float width = (this.baseWidth + (this.size * (this.tgScale.length - numScala - 1)));
    System.out.println("Width " + numScala + ": " + width);
    return width;
  }

  protected float calculateLength(int numScala) {
    float length = (this.baseLength + (this.size * (this.tgScale.length - numScala - 1)));
    System.out.println("Length " + numScala + ": " + length);
    return length;
  }

  // This function calculate the correct size for a single scalino.
  protected float calculateSize() {
    float d = (float) Math.sqrt((this.baseLength * this.baseLength) + (this.baseWidth * this.baseWidth));
    System.out.println("Size: " + (d / 10));
    return (d / 10);
  }

}
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;

class Main {

  public Main() {
    SimpleUniverse universe = new SimpleUniverse();
    BranchGroup group = new BranchGroup();
    group.addChild(new ColorCube(0.3));
    universe.getViewingPlatform().setNominalViewingTransform();
    universe.addBranchGraph(group);
  }

  public static void main (String[] args) {
    new Main();
  }
}
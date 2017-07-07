import javax.vecmath.Color3f;

public interface InterfaceColors {
  
  public static final Color3f COLOR_WHITE = hex2Color3f("#ffffff");
  public static final Color3f COLOR_BLACK = hex2Color3f("#000000");
  public static final Color3f COLOR_VIOLET = hex2Color3f("#8e44ad");

  public static Color3f hex2Color3f(String colorStr) {
    return new Color3f(
      Integer.valueOf( colorStr.substring( 1, 3 ), 16 ) / (float) 255,
      Integer.valueOf( colorStr.substring( 3, 5 ), 16 ) / (float) 255,
      Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) / (float) 255
    );
  }
  
}

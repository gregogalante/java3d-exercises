import javax.vecmath.Color3f;

public interface InterfaceColors {
  
  public static final Color3f COLOR_WHITE = hex2Color3f("#ffffff");
  public static final Color3f COLOR_BLACK = hex2Color3f("#000000");
  public static final Color3f COLOR_LIGHT_BLACK = hex2Color3f("#333333");
  public static final Color3f COLOR_VIOLET = hex2Color3f("#8e44ad");
  public static final Color3f COLOR_GREEN = hex2Color3f("#27ae60");
  public static final Color3f COLOR_BLUE = hex2Color3f("#2980b9");
  public static final Color3f COLOR_GREY = hex2Color3f("#bdc3c7");

  public static Color3f hex2Color3f(String colorStr) {
    return new Color3f(
      Integer.valueOf( colorStr.substring( 1, 3 ), 16 ) / (float) 255,
      Integer.valueOf( colorStr.substring( 3, 5 ), 16 ) / (float) 255,
      Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) / (float) 255
    );
  }
  
}

import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TexCoordGeneration;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.geometry.Box;

import javax.vecmath.Vector3d;

class Navicella extends Group {

  protected Appearance appearance;
  protected float size;

  public Navicella(float diameter) {
    this(diameter, null);
  }

  public Navicella(float diameter, Appearance appearance) {
    this.size = diameter / 10;

    if (appearance == null) {
      this.appearance = createAppearance();
    }

    addChild(createCorpoCentrale());
    addChild(createMotoreCentraleCilindro());
    addChild(createMotoreCentraleEchino());
    addChild(createCollegamanentoMotori());
    addChild(createMotore1());
    addChild(createMotore2());
    addChild(createChiusuraPosteriore());
  }

  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    appearance.setMaterial(material);
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // add texture
    addTextureToAppearance(appearance);
    // return the appearance
    return appearance;
  }

  protected void addTextureToAppearance(Appearance appearance) {
    // load texture file
    TextureLoader textureLoader = new TextureLoader("../../images/hope.jpg", null);
    // set texture
    Texture texture = textureLoader.getTexture();
    texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
    // add texture to the appearance
    appearance.setTexture(texture);
    // initialize texture attributes
    TextureAttributes textureAttributes = new TextureAttributes();
	  textureAttributes.setTextureMode(TextureAttributes.COMBINE);
    textureAttributes.setPerspectiveCorrectionMode(TextureAttributes.NICEST);
    appearance.setTextureAttributes(textureAttributes);
    // initialize and add text coordinates generator
    TexCoordGeneration tcg = new TexCoordGeneration(
      TexCoordGeneration.OBJECT_LINEAR,
      TexCoordGeneration.TEXTURE_COORDINATE_3
    );
	  appearance.setTexCoordGeneration(tcg);
  }

  // Corpo centrale.
  protected TransformGroup createCorpoCentrale() {
    TransformGroup corpoCentrale = new TransformGroup();

    Sphere sphere = new Sphere(
      this.size * 10,
      -Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,
      100,
      this.appearance
    );
    corpoCentrale.addChild(sphere);

    return corpoCentrale;
  }

  // Motore centrale cilindro.
  protected TransformGroup createMotoreCentraleCilindro() {
    TransformGroup motoreCentraleCilindro = new TransformGroup();

    Cylinder cylinder = new Cylinder(
      this.size * 2,
      this.size,
      -Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    motoreCentraleCilindro.addChild(cylinder);

    // translate from center
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, this.size * 10, 0.0f));
    // rotate for 90 degrees
    Transform3D rotation = new Transform3D();
    rotation.rotX(Math.PI / 2);

    rotation.mul(translate);
    motoreCentraleCilindro.setTransform(rotation);

    return motoreCentraleCilindro;
  }

  // Motore centrale echino.
  protected TransformGroup createMotoreCentraleEchino() {
    TransformGroup motoreCentraleEchino = new TransformGroup();

    MyCylinder cylinder = new MyCylinder(
      this.size * 5,
      this.size * 2,
      this.size * 10,
      this.appearance
    );
    motoreCentraleEchino.addChild(cylinder);

    // translate from center
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, this.size * 15.5, 0.0f));
    // rotate for 90 degrees
    Transform3D rotation = new Transform3D();
    rotation.rotX(Math.PI / 2);

    rotation.mul(translate);
    motoreCentraleEchino.setTransform(rotation);

    return motoreCentraleEchino;
  }

  // Collegamento motori.
  protected TransformGroup createCollegamanentoMotori() {
    TransformGroup collegamentoMotori = new TransformGroup();

    Cylinder cylinder = new Cylinder(
      this.size * 1.5f,
      this.size * 20,
      -Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    collegamentoMotori.addChild(cylinder);

    // translate from center
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, 0.0f, this.size * 17.5f));
    // rotate for 90 degrees
    Transform3D rotation = new Transform3D();
    rotation.rotZ(Math.PI / 2);

    rotation.mul(translate);
    collegamentoMotori.setTransform(rotation);

    return collegamentoMotori;
  }

  // Motore 1.
  protected TransformGroup createMotore1() {
    TransformGroup motore1 = new TransformGroup();

    Box box = new Box(
      this.size * 2,
      this.size * 2,
      this.size * 6,
      -Box.GENERATE_NORMALS|Box.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    motore1.addChild(box);

    // translate from center
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(-this.size * 10, 0.0f, this.size * 17.5f));

    motore1.setTransform(translate);

    return motore1;
  }

  // Motore 2.
  protected TransformGroup createMotore2() {
    TransformGroup motore2 = new TransformGroup();

    Box box = new Box(
      this.size * 2,
      this.size * 2,
      this.size * 6,
      -Box.GENERATE_NORMALS|Box.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    motore2.addChild(box);

    // translate from center
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(+this.size * 10, 0.0f, this.size * 17.5f));

    motore2.setTransform(translate);

    return motore2;
  }

  // Chiusura posteriore.
  protected TransformGroup createChiusuraPosteriore() {
    TransformGroup chiusuraPosteriore = new TransformGroup();

    Cylinder cylinder = new Cylinder(
      this.size * 5,
      this.size * 0.1f,
      -Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS,
      this.appearance
    );
    chiusuraPosteriore.addChild(cylinder);

    // translate from center
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, this.size * 20.5f, 0.0f));
    // rotate for 90 degrees
    Transform3D rotation = new Transform3D();
    rotation.rotX(Math.PI / 2);

    rotation.mul(translate);
    chiusuraPosteriore.setTransform(rotation);

    return chiusuraPosteriore;
  }
 
}
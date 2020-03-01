package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;

public class ProjectileMatrixTest{

  @Test
  public void testProjectileMatrixConstruction(){
    PImage img = new PImage(5, 5);
    PImage img2 = new PImage(5, 5);
    ProjectileMatrix projMatrix = new ProjectileMatrix(img, img2);
    assertNotNull(projMatrix);
  }

}

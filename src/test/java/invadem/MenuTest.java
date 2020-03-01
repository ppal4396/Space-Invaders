package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;

public class MenuTest{

    @Test
    public void testMenuConstruction(){
      //constructor start --
      PImage img = new PImage(5, 5);
      PImage img2 = new PImage(5, 5);
      Menu m = new Menu(0, 0);
      m.prepareDisplays(img, img, img2);
      // -- end
      assertNotNull(m);

    }

    @Test
    public void testMenuAddPoints(){
      //constructor start --
      PImage img = new PImage(5, 5);
      PImage img2 = new PImage(5, 5);
      Menu m = new Menu(0, 0);
      m.prepareDisplays(img, img, img2);
      // -- end
      //.addPoints() accepts two parameters; two booleans for whether an
      //armoured or powered invader has been shot respectively.
      m.addPoints(true, false);
      assertEquals(250, m.getCurrentScore().getScore());
      m.addPoints(false, true);
      assertEquals(500, m.getCurrentScore().getScore());
      m.addPoints(true, true);
      assertEquals(750, m.getCurrentScore().getScore());
      m.addPoints(false, false);
      assertEquals(850, m.getCurrentScore().getScore());

    }


}

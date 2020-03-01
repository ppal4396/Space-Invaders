package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;
import java.util.Arrays;
import java.util.List;

public class InvaderTest {


  @Test
  public void testInvaderConstruction(){
    //Constructors start --
    Invader s = new SimpleInvader(5, 5, 16, 16);
    Invader p = new PowerInvader(5, 5, 16, 16);
    Invader a = new ArmouredInvader(5, 5, 16, 16);
    List<Invader> invs = Arrays.asList(s, p, a);
    // -- end

    assertTrue(p.isPowered());
    assertTrue(a.isArmoured());

    for(Invader inv:invs){
      assertNotNull(inv);
      if(inv.isPowered()){
        assertFalse(inv.isArmoured());
      }
      else if(inv.isArmoured()){
        assertFalse(inv.isPowered());
      }
      else{
        assertFalse(inv.isArmoured());
        assertFalse(inv.isPowered());
      }
    }
  }

    @Test
    public void testInvaderIsDead(){
      //Constructors start --
      Invader s = new SimpleInvader(5, 5, 16, 16);
      Invader p = new PowerInvader(5, 5, 16, 16);
      Invader a = new ArmouredInvader(5, 5, 16, 16);
      List<Invader> invs = Arrays.asList(s, p, a);
      // -- end
      assertTrue(s.sustainDamage());
      assertTrue(p.sustainDamage());
      assertFalse(a.sustainDamage());
      assertFalse(a.sustainDamage());
      assertTrue(a.sustainDamage());
    }

    @Test
    public void testInvaderIntersectWithPlayerProjectile() {
      //Constructors start --
      Invader s = new SimpleInvader(5, 5, 16, 16);
      Invader p = new PowerInvader(5, 5, 16, 16);
      Invader a = new ArmouredInvader(5, 5, 16, 16);
      List<Invader> invs = Arrays.asList(s, p, a);
      // -- end
      PImage img = new PImage(5, 5);
      Projectile proj = new Projectile(img, 5, 5, 1, 3);
      for(Invader inv:invs){
        assertTrue(proj.checkCollision(inv, proj));
      }
    }

     @Test
     public void testInvaderNotIntersectWithPlayerProjectile() {
       //Constructors start --
       Invader s = new SimpleInvader(5, 5, 16, 16);
       Invader p = new PowerInvader(5, 5, 16, 16);
       Invader a = new ArmouredInvader(5, 5, 16, 16);
       List<Invader> invs = Arrays.asList(s, p, a);
       // -- end
       PImage img = new PImage(5, 5);
       Projectile proj = new Projectile(img, 100, 100, 1, 3);
       for(Invader inv:invs){
         assertFalse(proj.checkCollision(inv, proj));
       }
     }

     @Test
     public void testInvaderChangeSprite(){
       //Constructors start --
       Invader s = new SimpleInvader(5, 5, 16, 16);
       Invader p = new PowerInvader(5, 5, 16, 16);
       Invader a = new ArmouredInvader(5, 5, 16, 16);
       List<Invader> invs = Arrays.asList(s, p, a);
       PImage img = new PImage(5, 5);
       PImage img2 = new PImage(5, 5);
       for(Invader inv: invs){
         inv.setSprites(img, img2);
       }
       // -- end
       for(Invader inv: invs){
         inv.changeSprite();
         assertEquals(img2, inv.getDisplay());
       }
       for(Invader inv: invs){
         inv.changeSprite();
         assertEquals(img, inv.getDisplay());
       }
     }

     @Test
     public void testInvaderMove(){
       //Constructors start --
       Invader s = new SimpleInvader(5, 5, 16, 16);
       Invader p = new PowerInvader(5, 5, 16, 16);
       Invader a = new ArmouredInvader(5, 5, 16, 16);
       List<Invader> invs = Arrays.asList(s, p, a);
       // -- end
       for(Invader inv:invs){
         inv.moveDown();
         assertEquals(5, inv.getX());
         assertEquals(13, inv.getY());
         inv.moveLeft();
         assertEquals(4, inv.getX());
         assertEquals(13, inv.getY());
         inv.moveRight();
         assertEquals(5, inv.getX());
         assertEquals(13, inv.getY());
       }
     }
  }

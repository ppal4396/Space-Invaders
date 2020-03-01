package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;

public class ProjectileTest {


   @Test
   public void testProjectileConstruction() {
     PImage image = new PImage(5, 5);
     Projectile proj = new Projectile(image, 0, 0, 1, 3);
     assertNotNull(proj);
   }

   @Test
   public void testProjectileIsFriendly() {
     PImage image = new PImage(5, 5);
     Projectile proj = new Projectile(image, 0, 0, 1, 3);
     assertFalse(proj.isEnemyProjectile());
   }

   @Test
   public void testProjectileIsNotFriendly() {
     PImage image = new PImage(5, 5);
     Projectile proj = new Projectile(image, 0, 0, 1, 3);
     proj.setEnemyProjectile();
     assertTrue(proj.isEnemyProjectile());
   }

   @Test
   public void testEnemyProjectileMovesDown(){
     PImage image = new PImage(5, 5);
     Projectile proj = new Projectile(image, 0, 0, 1, 3);
     proj.setEnemyProjectile();
     int originalYPosition = proj.getY() + 0;
     int originalXPosition = proj.getX() + 0;
     proj.tick();
     assertEquals(originalYPosition + 1, proj.getY());
     assertEquals(originalXPosition, proj.getX());
   }

   @Test
   public void testTankProjectileMovesUp(){
     PImage image = new PImage(5, 5);
     Projectile proj = new Projectile(image, 0, 5, 1, 3);
     int originalYPosition = proj.getY() + 0;
     int originalXPosition = proj.getX() + 0;
     proj.tick();
     assertEquals(originalYPosition - 1, proj.getY());
     assertEquals(originalXPosition, proj.getX());
   }


}

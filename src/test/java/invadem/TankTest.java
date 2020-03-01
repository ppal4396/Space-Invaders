package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;

public class TankTest {

   @Test
   public void testTankConstruction() {
      //constructor start --
      PImage image = new PImage(5, 5);
      Tank tank = new PlayerOne(image, 5, 5, 22, 16, image);
      // -- end
      assertNotNull(tank);
   }

   @Test
   public void testTankIsDead(){
     //constructor start --
     PImage image = new PImage(5, 5);
     Tank tank = new PlayerOne(image, 5, 5, 22, 16, image);
     // -- end
     tank.sustainDamage();
     tank.sustainDamage();
     tank.sustainDamage();
     assertTrue(tank.isDead());
   }

   @Test
   public void testTankSpawn() {
     //constructor start --
     PImage image = new PImage(5, 5);
     Tank tank = new PlayerOne(image, 5, 5, 22, 16, image);
     // -- end
     tank.instaKill();
     assertTrue(tank.isDead());
     tank.spawn();
     assertEquals(309, tank.getX());
     assertFalse(tank.isDead());
   }

   @Test
   public void testTankIsNotDead() {
     //constructor start --
     PImage image = new PImage(5, 5);
     Tank tank = new PlayerOne(image, 5, 5, 22, 16, image);
     // -- end
     assertFalse(tank.isDead());
     tank.sustainDamage();
     assertFalse(tank.isDead());
     tank.sustainDamage();
     assertFalse(tank.isDead());
   }

   @Test
   public void testTankIntersectWithEnemyProjectile(){
     //constructor start --
     PImage image = new PImage(5, 5);
     Tank tank = new PlayerOne(image, 309, 454, 16, 16, image);
     // -- end
     Projectile p = new Projectile(image, 309, 454, 1, 3);
     p.setEnemyProjectile();
     assertEquals(true, p.checkCollision(p, tank));
   }




}

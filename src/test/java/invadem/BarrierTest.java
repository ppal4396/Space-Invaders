package invadem;
import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;
public class BarrierTest{


  @Test
   public void barrierConstruction() {
      //constructor start --
     PImage image = new PImage(5, 5);
     PImage[] imageArray = new PImage[3];
     for(PImage img:imageArray){
       img = image;
     }
     Barrier b = new Barrier(0, 0);
     b.setComponentSprites(imageArray, imageArray, imageArray, image);
     // -- end
     for(BarrierComponent component:b){
       component.sustainDamage();
     }
     for(BarrierComponent component:b){
       assertNotNull(component);
     }
     assertNotNull(b);
   }

   @Test
   public void testBarrierNotDestroyed() {
     //constructor start --
     PImage image = new PImage(5, 5);
     PImage[] imageArray = new PImage[3];
     for(PImage img:imageArray){
       img = image;
     }
     Barrier b = new Barrier(0, 0);
     b.setComponentSprites(imageArray, imageArray, imageArray, image);
     // -- end
     for(BarrierComponent component:b){
       assertEquals(false, component.isDestroyed());
     }
   }

   @Test
   public void testBarrierHitPoints() {
      //constructor start --
     PImage image = new PImage(5, 5);
     PImage[] imageArray = new PImage[3];
     for(PImage img:imageArray){
       img = image;
     }
     Barrier b = new Barrier(0, 0);
     b.setComponentSprites(imageArray, imageArray, imageArray, image);
     // -- end
     //Hit Once
     for(BarrierComponent component:b){
       component.sustainDamage();
     }
     for(BarrierComponent component:b){
       assertEquals(false, component.isDestroyed());
     }
     //Hit Twice
     for(BarrierComponent component:b){
       component.sustainDamage();
     }
     for(BarrierComponent component:b){
       assertEquals(false, component.isDestroyed());
     }
     //Hit Thrice
     for(BarrierComponent component:b){
       component.sustainDamage();
     }
     for(BarrierComponent component:b){
       assertEquals(true, component.isDestroyed());
     }
   }

     @Test
     public void testBarrierRespawn(){
        //constructor start --
       PImage image = new PImage(5, 5);
       PImage[] imageArray = new PImage[3];
       for(PImage img:imageArray){
         img = image;
       }
       Barrier b = new Barrier(0, 0);
       b.setComponentSprites(imageArray, imageArray, imageArray, image);
       // -- end
       for(BarrierComponent component:b){
         component.sustainDamage();
         component.sustainDamage();
         component.sustainDamage();
       }
       b.spawn();
       for(BarrierComponent component:b){
         assertEquals(false, component.isDestroyed());
       }
     }

     @Test
     public void testBarrierClearComponents(){
        //constructor start --
       PImage image = new PImage(5, 5);
       PImage[] imageArray = new PImage[3];
       for(PImage img:imageArray){
         img = image;
       }
       Barrier b = new Barrier(0, 0);
       b.setComponentSprites(imageArray, imageArray, imageArray, image);
       // -- end
       b.clearComponents();
       for(BarrierComponent component:b){
         assertEquals(true, component.isDestroyed());
       }


     }

   }

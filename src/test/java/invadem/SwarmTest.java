package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import processing.core.PImage;
import java.util.ArrayList;


public class SwarmTest {

   @Test
   public void testSwarmConstruction() {
       //constructor start --
       PImage img = new PImage(5, 5);
       Swarm s = new Swarm(0, 0, img, img, img, img, img, img);
       // -- end
       assertNotNull(s);
       for(Invader inv:s.getInvaderList()){
         assertNotNull(inv);
       }
   }

   @Test
   public void testSwarmSpawn(){
     //constructor start --
     PImage img = new PImage(5, 5);
     Swarm s = new Swarm(0, 0, img, img, img, img, img, img);
     // -- end
     s.removeAllInvaders();
     assertEquals(0, s.getInvaderList().size());

     s.spawn();
     //check size of swarm returns to 40.
     assertEquals(40, s.getInvaderList().size());

     //Check armoured, powered and normal invaders are in correct positions.
     for(int i = 0; i < 10; i ++){
       assertTrue(s.getInvaderList().get(i).isArmoured());
       assertEquals(0, s.getInvaderList().get(i).getY());
     }
     for(int i = 10; i < 20; i ++){
       assertTrue(s.getInvaderList().get(i).isPowered());
       assertEquals(32, s.getInvaderList().get(i).getY());
     }
     for(int i = 20; i < 30; i ++){
       assertFalse(s.getInvaderList().get(i).isArmoured());
       assertFalse(s.getInvaderList().get(i).isPowered());
       if(i < 30){
         assertEquals(64, s.getInvaderList().get(i).getY());
       }
       else{
         assertEquals(96, s.getInvaderList().get(i).getY());
       }
     }
   }

     @Test
     public void testSwarmSteps(){
       //constructor start --
       PImage img = new PImage(5, 5);
       PImage img2 = new PImage(5, 5);
       Swarm s = new Swarm(30, 0, img, img2, img, img2, img, img2);
       // -- end
       ArrayList<Integer> originalXPositions = new ArrayList<Integer>();
       ArrayList<Integer> originalYPositions = new ArrayList<Integer>();
       for(Invader inv:s.getInvaderList()){
         originalXPositions.add(inv.getX());
         originalYPositions.add(inv.getY());
       }
       //take 30 steps - swarm should move left 30 pixels.
       for(int i = 0; i < 30; i++){
         s.stepAll();
       }
       for(int i = 0; i < s.getInvaderList().size(); i++){
         assertEquals(originalXPositions.get(i) - 30, s.getInvaderList().get(i).getX());
       }
       //take 31st step - swarm should move down 8 pixels.
       s.stepAll();
       for(int i = 0; i < s.getInvaderList().size(); i++){
         assertEquals(originalYPositions.get(i) + 8, s.getInvaderList().get(i).getY());
       }
       //take 30 more steps - swarm should move right 30 pixels.
       for(int i = 0; i < 30; i++){
         s.stepAll();
       }
       for(int i = 0; i < s.getInvaderList().size(); i++){
         assertEquals(s.getInvaderList().get(i).getX(), originalXPositions.get(i) + 0);
       }
     }

     @Test
     public void testSwarmLoses(){
       //constructor start --
       PImage img = new PImage(5, 5);
       PImage img2 = new PImage(5, 5);
       Swarm s = new Swarm(30, 0, img, img2, img, img2, img, img2);
       // -- end
       s.removeAllInvaders();
       if (s.getInvaderList().size() == 0){
         s.setLose();
       }
       assertTrue(s.hasLost());

     }

     @Test
     public void testSwarmGetRandomInvader(){
       //constructor start --
       PImage img = new PImage(5, 5);
       PImage img2 = new PImage(5, 5);
       Swarm s = new Swarm(30, 0, img, img2, img, img2, img, img2);
       // -- end
       assertThat(s.getRandomInvader(), instanceOf(Invader.class));
     }




   }

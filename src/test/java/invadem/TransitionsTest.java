package invadem;

import org.junit.*;
import static org.junit.Assert.*;
import processing.core.PApplet;
import processing.core.PImage;


public class TransitionsTest extends App{

  private App app;

  @Before
  public void testSetup(){
    app = new App();
    String[] args = {"AppTest"};
    PApplet.runSketch(args, app);
    delay(200);
  }

  @Test
  public void testGameOverEndTransition(){
    app.getPlayerOne().setPosition(200, 454);
    app.getMenu().gameOverTransition(app);
    delay(2000);
    delay(500);
    assertFalse(app.getPlayerOne().isDead());
    assertEquals(309, app.getPlayerOne().getX() + 0);
    for(Barrier barrier:app.getBarriers()){
      for(BarrierComponent component:barrier){
        assertFalse(component.isDestroyed());
      }
    }
    assertEquals(40, app.getSwarm().getInvaderList().size());
  }

  @Test
  public void testNextLevelEndTransition(){
    app.getPlayerOne().setPosition(200, 454);
    app.getMenu().nextLevelTransition(app);
    delay(2000);
    delay(500);
    assertFalse(app.getPlayerOne().isDead());
    assertEquals(309, app.getPlayerOne().getX() + 0);
    for(Barrier barrier:app.getBarriers()){
      for(BarrierComponent component:barrier){
        assertFalse(component.isDestroyed());
      }
    }
    assertEquals(40, app.getSwarm().getInvaderList().size());
  }

  @Test
  public void testLevelSpeedIncreaseAndReset(){
    //Tick rate of ProjectileMatrix should decrease by 60 until at 60 every time a new level is reached.
    //Game over should reset tick rate to 300.
    app.getMenu().nextLevelTransition(app);
    delay(2000);
    delay(100);
    assertEquals(240, app.getProjMatrix().getTickRate());

    app.getMenu().nextLevelTransition(app);
    delay(2000);
    delay(100);
    assertEquals(180, app.getProjMatrix().getTickRate());

    app.getMenu().nextLevelTransition(app);
    delay(2000);
    delay(100);
    assertEquals(120, app.getProjMatrix().getTickRate());

    app.getMenu().nextLevelTransition(app);
    delay(2000);
    delay(100);
    assertEquals(60, app.getProjMatrix().getTickRate());

    app.getMenu().nextLevelTransition(app);
    delay(2000);
    delay(100);
    assertEquals(60, app.getProjMatrix().getTickRate());

    app.getMenu().gameOverTransition(app);
    delay(2000);
    delay(100);
    assertEquals(300, app.getProjMatrix().getTickRate());

  }

  @Test
  public void testGameOverIncreasesHighScore(){
    app.getMenu().getCurrentScore().setScore(20000);
    app.getMenu().gameOverTransition(app);
    delay(2000);
    app.getMenu().endTransition(app);
    delay(100);
    assertEquals(20000, app.getMenu().getHighScore().getScore());
  }


  @After
  public void stopApp(){
    app.noLoop();
  }



}

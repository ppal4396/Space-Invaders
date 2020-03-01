package invadem;

import org.junit.*;
import static org.junit.Assert.*;
import processing.core.PApplet;
import processing.core.PImage;


public class AppTest extends App{
  App app;
  int prevPlayerPosition;

  @Before
  public void testSetup(){
    app = new App();
    String[] args = {"AppTest"};
    PApplet.runSketch(args, app);
    delay(200);
    prevPlayerPosition = app.getPlayerOne().getX() + 0;
  }

  @Test
  public void testAppConstruction(){
    assertNotNull(app);
    assertNotNull(app.getPlayerOne());
    assertNotNull(app.getBarriers());
    assertNotNull(app.getProjMatrix());
    assertNotNull(app.getAudioPlayer());
    assertNotNull(app.getMenu());
    assertNotNull(app.getSwarm());
  }

  @Test
  public void testPlayerMoveLeft(){
    boolean[] keys = new boolean[256];
    keys[128] = true; //used for keyCode LEFT
    app.getPlayerOne().handleKeyEvent(keys);
    delay(100);
    assertTrue(app.getPlayerOne().getX() < prevPlayerPosition);
  }

  @Test
  public void testPlayerMoveRight(){
    boolean[] keys = new boolean[256];
    keys[129] = true; //used for keyCode RIGHT
    app.getPlayerOne().handleKeyEvent(keys);
    delay(100);
    assertTrue(app.getPlayerOne().getX() > prevPlayerPosition);
  }

  @Test
  public void testPlayerShoots(){
    boolean[] keys = new boolean[256];
    keys[' '] = true;
    app.getPlayerOne().handleKeyEvent(keys);
    delay(100);
    //assert only 1 projectile generated
    assertEquals(1, app.getProjMatrix().getTankProjectiles().size());
    //assert friendly projectile generated
    assertFalse(app.getProjMatrix().getTankProjectiles().get(0).isEnemyProjectile());
  }

  @Test
  public void testSwarmShotGeneration(){
    app.getProjMatrix().generateSwarmShot(app);
    //assert only 1 projectile generated
    assertEquals(1, app.getProjMatrix().getEnemyProjectiles().size());
    //assert enemy projectile generated
    assertTrue(app.getProjMatrix().getEnemyProjectiles().get(0).isEnemyProjectile());
  }

  @Test
  public void testInvaderTankProjCollision(){
    int originalSwarmSize = app.getSwarm().getInvaderList().size() + 0;
    //add tank projectile and set it to collide with non-armoured invader
    app.getProjMatrix().addTankProjectile(app.getPlayerOne());

    int xPos = app.getSwarm().getInvaderList().get(11).getX();
    int yPos = app.getSwarm().getInvaderList().get(11).getY();
    app.getProjMatrix().getTankProjectiles().get(0).setPosition(xPos, yPos);

    delay(100);
    assertEquals(originalSwarmSize - 1, app.getSwarm().getInvaderList().size());

    //add 3 tank projectiles and set them to collide with armoured invader
    app.getProjMatrix().addTankProjectile(app.getPlayerOne());
    app.getProjMatrix().addTankProjectile(app.getPlayerOne());
    app.getProjMatrix().addTankProjectile(app.getPlayerOne());

    xPos = app.getSwarm().getInvaderList().get(0).getX();
    yPos = app.getSwarm().getInvaderList().get(0).getY();
    for(Projectile proj:app.getProjMatrix().getTankProjectiles()){
      proj.setPosition(xPos, yPos);
    }

    delay(200);
    assertEquals(originalSwarmSize - 2, app.getSwarm().getInvaderList().size());
  }

  @Test
  public void testBarrierEnemyProjCollision(){
    //generate 3x enemy shots and set them to collide with left barrier's top left component.
    //assert component dies after 3 shots, unless powered shot is generated in which case
    //assert component dies instantly.
    BarrierComponent component = app.getBarriers().get(0).getComponentList().get(0);
    app.getProjMatrix().generateSwarmShot(app);
    Projectile proj = app.getProjMatrix().getEnemyProjectiles().get(0);
    proj.setPosition(component.getX(), component.getY());
    if(proj.isPowered()){
      delay(100);
      assertTrue(component.isDestroyed());
      return;
    }
    else{
      delay(100);
      assertFalse(component.isDestroyed());
    }

    app.getProjMatrix().generateSwarmShot(app);
    proj = app.getProjMatrix().getEnemyProjectiles().get(0);
    proj.setPosition(component.getX(), component.getY());
    if(proj.isPowered()){
      delay(100);
      assertTrue(component.isDestroyed());
      return;
    }
    else{
      delay(100);
      assertFalse(component.isDestroyed());
    }

    app.getProjMatrix().generateSwarmShot(app);
    proj = app.getProjMatrix().getEnemyProjectiles().get(0);
    proj.setPosition(component.getX(), component.getY());
    delay(100);
    assertTrue(component.isDestroyed());
  }

  @Test
  public void testBarrierTankProjCollision(){
    //generate 3x tank shots and set them to collide with left barrier's top middle component.
    BarrierComponent component = app.getBarriers().get(0).getComponentList().get(1);

    app.getProjMatrix().addTankProjectile(app.getPlayerOne());
    app.getProjMatrix().getTankProjectiles().get(0).setPosition(component.getX(), component.getY());
    delay(100);
    assertFalse(component.isDestroyed());

    app.getProjMatrix().addTankProjectile(app.getPlayerOne());
    app.getProjMatrix().getTankProjectiles().get(0).setPosition(component.getX(), component.getY());
    delay(100);
    assertFalse(component.isDestroyed());

    app.getProjMatrix().addTankProjectile(app.getPlayerOne());
    app.getProjMatrix().getTankProjectiles().get(0).setPosition(component.getX(), component.getY());
    delay(100);
    assertTrue(component.isDestroyed());
  }

  @Test
  public void testTankEnemyProjCollision(){
    //generate 3 shots, set them to collide with tank, check tank dies only after 3 collisions, unless powered shot generated.
    app.getProjMatrix().generateSwarmShot(app);
    Projectile proj = app.getProjMatrix().getEnemyProjectiles().get(0);
    proj.setPosition(app.getPlayerOne().getX(), 454);
    if (proj.isPowered()){
      delay(100);
      assertTrue(app.getPlayerOne().isDead());
      return;
    }
    else{
      delay(100);
      assertFalse(app.getPlayerOne().isDead());
    }

    app.getProjMatrix().generateSwarmShot(app);
    proj = app.getProjMatrix().getEnemyProjectiles().get(0);
    proj.setPosition(app.getPlayerOne().getX(), 454);
    if (proj.isPowered()){
      delay(100);
      assertTrue(app.getPlayerOne().isDead());
      return;
    }
    else{
      delay(100);
      assertFalse(app.getPlayerOne().isDead());
    }

    app.getProjMatrix().generateSwarmShot(app);
    proj = app.getProjMatrix().getEnemyProjectiles().get(0);
    proj.setPosition(app.getPlayerOne().getX(), 454);
    delay(100);
    assertTrue(app.getPlayerOne().isDead());
  }

  @Test
  public void testGameOverConditionDeadTank(){
    app.getPlayerOne().instaKill();
    delay(100);

    assertTrue(app.getMenu().isInTransition());
    assertTrue(app.getPlayerOne().isDead());

    for(Barrier barrier:app.getBarriers()){
      for(BarrierComponent component:barrier){
        assertTrue(component.isDestroyed());
      }
    }
    assertEquals(0, app.getSwarm().getInvaderList().size());
    assertEquals(0, app.getProjMatrix().getTankProjectiles().size());
    assertEquals(0, app.getProjMatrix().getEnemyProjectiles().size());

    assertEquals(0, app.getMenu().getCurrentScore().getScore());
    assertEquals(1, app.getMenu().getLevel());
  }

  @Test
  public void testGameOverConditionSwarmReachesEnd(){
    app.getSwarm().getInvaderList().get(39).setPosition(418, 420);
    delay(100);

    assertTrue(app.getMenu().isInTransition());

    for(Barrier barrier:app.getBarriers()){
      for(BarrierComponent component:barrier){
        assertTrue(component.isDestroyed());
      }
    }
    assertEquals(0, app.getSwarm().getInvaderList().size());
    assertEquals(0, app.getProjMatrix().getTankProjectiles().size());
    assertEquals(0, app.getProjMatrix().getEnemyProjectiles().size());

    assertEquals(0, app.getMenu().getCurrentScore().getScore());
    assertEquals(1, app.getMenu().getLevel());
  }

  @Test
  public void testNextLevelCondition(){
    //Test tank shot colliding with last invader triggers Next Level.
    app.getSwarm().removeAllInvaders();
    Invader inv = new SimpleInvader(5, 5, 16, 16);
    PImage img = new PImage(5, 5);
    inv.setSprites(img, img);
    app.getSwarm().getInvaderList().add(inv);
    app.getProjMatrix().addTankProjectile(app.getPlayerOne());
    app.getProjMatrix().getTankProjectiles().get(0).setPosition(5, 5);
    delay(100);

    assertTrue(app.getMenu().isInTransition());
    for(Barrier barrier:app.getBarriers()){
      for(BarrierComponent component:barrier){
        assertTrue(component.isDestroyed());
      }
    }
    assertEquals(0, app.getSwarm().getInvaderList().size());
    assertEquals(0, app.getProjMatrix().getTankProjectiles().size());
    assertEquals(0, app.getProjMatrix().getEnemyProjectiles().size());
    assertEquals(2, app.getMenu().getLevel());
  }

  @Test
  //NOTE: While this test case works in isolation, it breaks the other test cases if uncommented.
  public void testUpdateOffScreen(){
    //off screen projectiles by projMatrix.updateOffScreen()
    app.getProjMatrix().generateSwarmShot(app);
    app.getProjMatrix().generateSwarmShot(app);
    app.getProjMatrix().getEnemyProjectiles().get(0).setPosition(100, 510);
    app.getProjMatrix().updateOffScreen(app);
    delay(100);
    assertEquals(1, app.getProjMatrix().getEnemyProjectiles().size());

    app.getProjMatrix().addTankProjectile(app.getPlayerOne());
    app.getProjMatrix().addTankProjectile(app.getPlayerOne());
    app.getProjMatrix().getTankProjectiles().get(0).setPosition(100, -30);
    app.getProjMatrix().updateOffScreen(app);
    delay(100);
    assertEquals(1, app.getProjMatrix().getEnemyProjectiles().size());

  }

  @After
  public void stopApp(){
    app.noLoop();
  }





}

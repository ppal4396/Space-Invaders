package invadem;

import org.junit.*;
import static org.junit.Assert.*;

public class AudioTest extends App{


  @Test
  public void testTankShotSound(){
    AudioPlayer a = new AudioPlayer();
    a.playTankShot();
    boolean tankShotPlaying = false;
    for(int i = 0; i < 10; i++){
      if(a.getTankShotSounds()[i].isOn()){
        tankShotPlaying = true;
      }
    }
    assertTrue(tankShotPlaying);
  }

  @Test
  public void testEnemyShotSound(){
    AudioPlayer a = new AudioPlayer();
    boolean powered = true;
    a.playEnemyShot(powered);
    assertTrue(a.getSoundEffects()[21].isOn());
    a.playEnemyShot(!powered);
    assertTrue(a.getSoundEffects()[20].isOn());
  }

  @Test
  public void testComponentExplosion(){
    AudioPlayer a = new AudioPlayer();
    a.playExplosion();
    assertTrue(a.getSoundEffects()[22].isOn());
  }

  @Test
  public void testInvaderExplosion(){
    AudioPlayer a = new AudioPlayer();
    a.playInvaderExplosion();
    assertTrue(a.getSoundEffects()[23].isOn());
  }

  @Test
  public void testTankDamageSound(){
    AudioPlayer a = new AudioPlayer();
    a.playTankDamage();
    assertTrue(a.getSoundEffects()[25].isOn());
  }

  @Test
  public void testNextLevelSounds(){
    AudioPlayer b = new AudioPlayer();
    b.playTankShot();
    b.playExplosion();
    b.playInvaderExplosion();
    Score level = new Score(5, 5, 5, 5);
    level.setScore(1);
    b.playNextLevel(level);
    delay(500);
    //assert Invader explosion, Component explosion or Tank shot sounds are not playing.
    assertFalse(b.getSoundEffects()[23].isOn());
    assertFalse(b.getSoundEffects()[22].isOn());
    boolean tankShotPlaying = false;
    for(int i = 0; i < 10; i++){
      if(b.getTankShotSounds()[i].isOn()){
        tankShotPlaying = true;
      }
    }
    assertFalse(tankShotPlaying);
    //assert Next Level sound is playing.
    assertTrue(b.getNextLevelSounds()[0].isOn());

    level.setScore(2);
    b.playNextLevel(level);
    assertTrue(b.getNextLevelSounds()[1].isOn());

    level.setScore(3);
    b.playNextLevel(level);
    assertTrue(b.getNextLevelSounds()[2].isOn());

    level.setScore(4);
    b.playNextLevel(level);
    assertTrue(b.getNextLevelSounds()[3].isOn());

    level.setScore(5);
    b.playNextLevel(level);
    assertTrue(b.getNextLevelSounds()[4].isOn());

    level.setScore(6);
    b.playNextLevel(level);
    assertTrue(b.getNextLevelSounds()[4].isOn());
  }

  @Test
  public void testGameOverSound(){
    AudioPlayer a = new AudioPlayer();
    a.playGameOver();
    assertTrue(a.getSoundEffects()[24].isOn());
  }

  @AfterClass
  public static void testEncouragement(){
    AudioPlayer a = new AudioPlayer();
    Score score = new Score(5, 5, 5, 5);
    score.setScore(5100);
    a.playEncouragement(score);
    boolean encouragementPlaying = false;
    for(int i = 0; i < 5; i++){
      if(a.getEncouragementSounds()[i].isOn()){
        encouragementPlaying = true;
      }
    }
    assertTrue(encouragementPlaying);

    for(int i = 0; i < 5; i++){
      a.getEncouragementSounds()[i].stop();
    }

    //once encouragement has been played, should not be played again until score has gained another 5000 points.
    score.add(100);
    a.playEncouragement(score);
    encouragementPlaying = false;
    for(int i = 0; i < 5; i++){
      if(a.getEncouragementSounds()[i].isOn()){
        encouragementPlaying = true;
      }
    }
    assertFalse(encouragementPlaying);

    score.add(5000);
    a.playEncouragement(score);
    encouragementPlaying = false;
    for(int i = 0; i < 5; i++){
      if(a.getEncouragementSounds()[i].isOn()){
        encouragementPlaying = true;
      }
    }
    assertTrue(encouragementPlaying);
  }
}

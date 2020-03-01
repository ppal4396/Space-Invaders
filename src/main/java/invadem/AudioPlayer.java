package invadem;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;
import java.util.Random;
import java.util.Arrays;

public class AudioPlayer{

  private Random random;
  private SoundEffect[] tankShotSounds;
  private SoundEffect[] nextLevelSounds;
  private SoundEffect[] encouragementSounds;
  private int nextEncouragement;

  public enum SoundEffect{
    SHOT1("shot1.wav"),
    SHOT2("shot2.wav"),
    SHOT3("shot3.wav"),
    SHOT4("shot4.wav"),
    SHOT5("shot5.wav"),
    SHOT6("shot6.wav"),
    SHOT7("shot7.wav"),
    SHOT8("shot8.wav"),
    SHOT9("shot9.wav"),
    SHOT10("shot10.wav"),
    NEXTLEVEL1("nextlevel1.wav"),
    NEXTLEVEL2("nextlevel2.wav"),
    NEXTLEVEL3("nextlevel3.wav"),
    NEXTLEVEL4("nextlevel4.wav"),
    NEXTLEVEL5("nextlevel5.wav"),
    ENCOURAGEMENT1("encouragement1.wav"),
    ENCOURAGEMENT2("encouragement2.wav"),
    ENCOURAGEMENT3("encouragement3.wav"),
    ENCOURAGEMENT4("encouragement4.wav"),
    ENCOURAGEMENT5("encouragement5.wav"),
    ENEMYSHOT1("enemyshot1.wav"),
    ENEMYSHOT2("enemyshot2.wav"),
    EXPLOSION("explosion.wav"),
    EXPLOSION2("explosion2.wav"),
    GAMEOVER("gameover.wav"),
    TANKDAMAGE("ow.wav");

    private Clip clip;
    private boolean on; //checked in testing framework

    SoundEffect(String fileName){
      URL url = this.getClass().getClassLoader().getResource(fileName);
      try{
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioIn);
      }
      catch (UnsupportedAudioFileException e){
        e.printStackTrace();
      }
      catch (LineUnavailableException e){
        e.printStackTrace();
      }
      catch (IOException e){
        e.printStackTrace();
      }
    }

    public void play(){
      if(clip.isRunning()){
        clip.stop();
      }
      clip.setFramePosition(0);
      clip.start();
      on = true;
    }

    public boolean isRunning(){
      if(clip.isRunning()){
        return true;
      }
      return false;
    }

    public void stop(){
      clip.stop();
      on = false;
    }

    public boolean isOn(){
      return this.on;
    }

  }
  public AudioPlayer(){
    this.random = new Random();
    this.tankShotSounds = Arrays.copyOfRange(SoundEffect.values(), 0, 10);
    this.nextLevelSounds = Arrays.copyOfRange(SoundEffect.values(), 10, 15);
    this.encouragementSounds = Arrays.copyOfRange(SoundEffect.values(), 15, 20);
    this.nextEncouragement = 5000;
  }

  public SoundEffect[] getSoundEffects(){
    return SoundEffect.values();
  }

  public void playTankShot(){
    int randomShotIndex = this.random.nextInt(10);
    tankShotSounds[randomShotIndex].play();

  }

  public void playEnemyShot(boolean poweredShot){
    if(poweredShot){
      SoundEffect.ENEMYSHOT2.play();
    }
    else{
      SoundEffect.ENEMYSHOT1.play();
    }
  }

  public void playExplosion(){
    SoundEffect.EXPLOSION.play();
  }

  public void playInvaderExplosion(){
    SoundEffect.EXPLOSION2.play();
  }

  public void playTankDamage(){
    SoundEffect.TANKDAMAGE.play();
  }

  public void playNextLevel(Score level){
    muteShotsAndExplosions();
    int nextLevelIndex = 0;
    if (level.getScore() >= 5){
      nextLevelIndex += 4;
    }
    else{
      nextLevelIndex = level.getScore() - 1;
    }
    nextLevelSounds[nextLevelIndex].play();
  }

  public void playGameOver(){
    SoundEffect.GAMEOVER.play();
    muteShotsAndExplosions();
  }

  public void resetEncouragement(){
    this.nextEncouragement = 5000;
  }

  public void playEncouragement(Score currentScore){
    if (currentScore.getScore() > nextEncouragement){
      int randomEncouragementIndex = this.random.nextInt(5);
      encouragementSounds[randomEncouragementIndex].play();
      nextEncouragement += 5000;
    }

  }

  public void muteShotsAndExplosions(){
    for(SoundEffect sound:tankShotSounds){
      if (sound.isRunning()){
        sound.stop();
      }
    }
    if (SoundEffect.ENEMYSHOT1.isRunning()){
      SoundEffect.ENEMYSHOT1.stop();
    }
    if (SoundEffect.ENEMYSHOT2.isRunning()){
      SoundEffect.ENEMYSHOT2.stop();
    }
    if (SoundEffect.EXPLOSION.isRunning()){
      SoundEffect.EXPLOSION.stop();
    }
    if (SoundEffect.EXPLOSION2.isRunning()){
      SoundEffect.EXPLOSION2.stop();
    }
  }

  public boolean isEncouraging(){
    boolean answer = false;
    for(SoundEffect sound:encouragementSounds){
      if (sound.isRunning()){
        answer = true;
      }
    }
    return answer;
  }

  public SoundEffect[] getTankShotSounds(){
    return this.tankShotSounds;
  }
  public SoundEffect[] getNextLevelSounds(){
    return this.nextLevelSounds;
  }
  public SoundEffect[] getEncouragementSounds(){
    return this.encouragementSounds;
  }

}

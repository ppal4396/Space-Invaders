package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class Menu extends DrawableObject implements Iterable<MenuObject>{
  private TransitionImage gameOver;
  private TransitionImage nextLevel;
  private Score currentScore;
  private Score highScore;
  private Score level;
  private List<MenuObject> menuObjects;
  private boolean inTransition;

  public Menu(int x, int y){
    super(x, y);
    this.gameOver = new TransitionImage(208, 208, 224, 32);
    this.nextLevel = new TransitionImage(208, 208, 224, 32);
    this.currentScore = new Score(10, 10, 170, 100);
    this.highScore = new Score(490, 10, 170, 100);
    this.level = new Score(10, 462, 170, 100);
    this.menuObjects = Arrays.asList(gameOver, nextLevel, currentScore, highScore, level);
    this.inTransition = false;
    this.tickRate = 120;
  }

  public void prepareDisplays(PImage gameOverImg, PImage nextLevelImg, PImage emptySprite){
    this.gameOver.setDisplay(gameOverImg, emptySprite);
    this.nextLevel.setDisplay(nextLevelImg, emptySprite);
    this.currentScore.setDisplay("current score: ", 0);
    this.highScore.setDisplay("high Score: ", 10000);
    this.level.setDisplay("Level ", 1);
  }

  public Iterator<MenuObject> iterator(){
    return this.menuObjects.iterator();
  }

  public void addPoints(boolean armouredInvader, boolean poweredInvader){
    if (armouredInvader || poweredInvader){
      this.currentScore.add(250);
    }
    else{
      this.currentScore.add(100);
    }
    this.currentScore.display();
  }

  public void checkEncouragement(App app){
    //encourages the player based on current score
    app.getAudioPlayer().playEncouragement(currentScore);
    if (app.getAudioPlayer().isEncouraging()){
      app.getAudioPlayer().muteShotsAndExplosions();
    }
  }

  public int getLevel(){
    return this.level.getScore();
  }

  public Score getCurrentScore(){
    return this.currentScore;
  }

  public Score getHighScore(){
    return this.highScore;
  }

  public void clearScreen(App app){
    for(Barrier barrier:app.getBarriers()){
      barrier.clearComponents();
    }
    app.getSwarm().removeAllInvaders();
    if(!app.getPlayerOne().isDead()){
      app.getPlayerOne().clearSprite();
    }
    app.getProjMatrix().clearAllShots();
  }

  public void gameOverTransition(App app){
    this.inTransition = true;
    clearScreen(app);
    this.gameOver.display();
    app.getAudioPlayer().playGameOver();
    if(currentScore.getScore() > highScore.getScore()){
      highScore.setScore(currentScore.getScore());
    }
    this.currentScore.reset();
    this.level.setScore(1);
    app.getAudioPlayer().resetEncouragement();

  }

  public void nextLevelTransition(App app){
    this.inTransition = true;
    clearScreen(app);
    this.nextLevel.display();
    app.getAudioPlayer().playNextLevel(level);
    this.level.add(1);


  }

  public boolean isInTransition(){
    return this.inTransition;
  }

  public void endTransition(App app){
      if(tickFrame()){
        this.inTransition = false;
        this.gameOver.hide();
        this.nextLevel.hide();
        this.currentScore.display();
        this.highScore.display();
        this.level.display();
        app.getProjMatrix().setLevelSpeed(level);
        app.getPlayerOne().spawn();
        app.getSwarm().spawn();
        for(Barrier barrier:app.getBarriers()){
          barrier.spawn();
        }
      }
  }


  public void tick(App app){
    if(inTransition){
      endTransition(app);
    }
    else{
      if (app.getPlayerOne().isDead() || app.getSwarm().hasWon()){
        gameOverTransition(app);
      }
      else if (app.getSwarm().hasLost()){
        nextLevelTransition(app);
      }
      checkEncouragement(app);
    }
  }


  public void draw(App app){
    for(MenuObject menuObj:this){
      menuObj.draw(app);
    }
    tick(app);
  }

}

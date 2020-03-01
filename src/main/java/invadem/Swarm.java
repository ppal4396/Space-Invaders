package invadem;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Swarm extends DrawableObject{

  private List<Invader> invaderSwarm;
  private int stepsMade;
  private boolean movingLeft;
  private Random random;
  private PImage normalSprite1;
  private PImage normalSprite2;
  private PImage powerSprite1;
  private PImage powerSprite2;
  private PImage armouredSprite1;
  private PImage armouredSprite2;
  private boolean swarmSuccess;
  private boolean swarmFailure;

  public Swarm(int x, int y, PImage normalSprite1, PImage normalSprite2,
  PImage powerSprite1, PImage powerSprite2, PImage armouredSprite1,
  PImage armouredSprite2){
    super(x, y);
    this.normalSprite1 = normalSprite1;
    this.normalSprite2 = normalSprite2;
    this.powerSprite1 = powerSprite1;
    this.powerSprite2 = powerSprite2;
    this.armouredSprite1 = armouredSprite1;
    this.armouredSprite2 = armouredSprite2;
    this.invaderSwarm = new ArrayList<Invader>();
    spawn();
    this.random = new Random();
    this.tickRate = 2;
  }

  public void spawn(){
    if (this.invaderSwarm.size() > 0){
      this.invaderSwarm = new ArrayList<Invader>();
    }
    for (int i = 0; i < 4; i ++){
        for(int j = 0; j < 10; j++){
          if (i == 0){
            Invader invader = new ArmouredInvader(x + (32*j), y + (i*32), 16, 16);
            this.invaderSwarm.add(invader);
          }
          else{
            if (i == 1){
              Invader invader = new PowerInvader(x + (32*j), y + (i*32), 16, 16);
              this.invaderSwarm.add(invader);
            }
            else{
              Invader invader = new SimpleInvader(x + (32*j), y + (i*32), 16, 16);
              this.invaderSwarm.add(invader);
            }
          }
        }
    }
    setAllSprites();
    this.stepsMade = 0;
    this.movingLeft = true;
    this.swarmSuccess = false;
    this.swarmFailure = false;
  }


  public void removeAllInvaders(){
    this.invaderSwarm = new ArrayList<Invader>();
  }

  public void setAllSprites(){
    for(Invader invader: this.invaderSwarm){
        if (invader.isPowered()){
          invader.setSprites(powerSprite1, powerSprite2);
        }
        else if (invader.isArmoured()){
          invader.setSprites(armouredSprite1, armouredSprite2);
        }
        else{
          invader.setSprites(normalSprite1, normalSprite2);
        }
    }
  }

  public Invader getRandomInvader(){
    int randomInt = this.random.nextInt(invaderSwarm.size());
    return this.invaderSwarm.get(randomInt);
  }

  public List<Invader> getInvaderList(){
    return this.invaderSwarm;
  }

  public int getSize(){
    return this.invaderSwarm.size();
  }

  public void stepAll(){
    if (this.stepsMade != 30){
      for(Invader invader:this.invaderSwarm){
        if(movingLeft){
          invader.moveLeft();
        }
        else{
          invader.moveRight();
        }
      }
      this.stepsMade += 1;
      if (stepsMade == 30){
        this.tickRate = 1;
      }
    }
    else {
      for(Invader invader:this.invaderSwarm){
        invader.moveDown();
        invader.changeSprite();
      }
      if(movingLeft){
        this.movingLeft = false;
      }
      else{
        this.movingLeft = true;
      }
      this.stepsMade = 0;
      this.tickRate = 2;
    }
  }

  public void checkSwarmProgress(App app){
    for(Invader invader:this.invaderSwarm){
      if (invader.getY() + 16 >= app.getBarriers().get(0).getY()){
        this.swarmSuccess = true;
      }
    }
  }

  public boolean hasWon(){
    return this.swarmSuccess;
  }

  public boolean hasLost(){
    return this.swarmFailure;
  }

  public void setLose(){
    this.swarmFailure = true;
  }

  public void fastTick(App app){
    checkSwarmProgress(app);
  }
  public void slowTick(App app){
    stepAll();
  }

  public void draw(App app){
      for(int i = 0; i < invaderSwarm.size(); i++){
          invaderSwarm.get(i).draw(app);
      }
      fastTick(app);
      if(tickFrame()){
        slowTick(app);
      }
  }

}

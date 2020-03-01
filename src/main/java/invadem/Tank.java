package invadem;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Tank extends GameObject{
    private int hitpoints;
    private PImage tankSprite;
    private PImage emptySprite;
    private PImage display;
    protected boolean shooting;
    protected boolean reloaded;


  public Tank(PImage img, int x, int y, int width, int height, PImage emptySprite){
    super(x, y, width, height);
    this.tankSprite = img;
    spawn();
    this.velocity = new int[] {0, 0};
    this.shooting = false;
    this.reloaded = true;
    this.emptySprite = emptySprite;
  }

  public void spawn(){
    this.display = this.tankSprite;
    this.hitpoints = 3;
    if(this.x != 309){
      this.x = 309;
    }
  }

  public boolean isShooting(){
      return this.shooting;
  }

  public boolean isReloaded(){
    return this.reloaded;
  }

  public void finishShooting(){
    this.shooting = false;
    this.reloaded = false;
  }

  public void sustainDamage(){
      this.hitpoints -= 1;
      if (this.hitpoints==0){
        this.display = emptySprite;
      }
  }

  public void instaKill(){
    this.hitpoints = 0;
    this.display = emptySprite;
  }

  public void clearSprite(){
    this.display = emptySprite;
  }

  public boolean isDead(){
    if (this.hitpoints == 0){
      return true;
    }
    return false;
  }

  public abstract void handleKeyEvent(boolean[] keys);

  public void tick(){
      if ((this.velocity[0] == 1 || x > 180) && (this.velocity[0] == -1 || x < 438)){
          this.x += this.velocity[0];
          this.y += this.velocity[1];
    }
  }

  public void draw(App app){
      app.image(display, x, y, width, height);
      tick();
  }
}

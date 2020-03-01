package invadem;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Invader extends GameObject{
    private PImage display;
    private PImage sprite1;
    private PImage sprite2;
    protected boolean armoured;
    protected boolean powered;

    public Invader(int x, int y, int width, int height){
      super(x, y, width, height);
      this.armoured = false;
      this.powered = false;
    }

    public void setSprites(PImage sprite1, PImage sprite2){
      this.sprite1 = sprite1;
      this.sprite2 = sprite2;
      this.display = sprite1;
    }

    public void changeSprite(){
      if (this.display == sprite1){
        this.display = sprite2;
      }
      else{
        this.display = sprite1;
      }
    }

    public void moveDown(){
      this.y += 8;
    }
    public void moveLeft(){
      this.x -= 1;
    }

    public void moveRight(){
      this.x += 1;
    }

    public abstract boolean sustainDamage();

    public boolean isPowered(){
      return this.powered;
    }

    public boolean isArmoured(){
      return this.armoured;
    }

    public PImage getDisplay(){
      return this.display;
    }

    public void draw(App app){
        app.image(display, x, y, width, height);

    }

}

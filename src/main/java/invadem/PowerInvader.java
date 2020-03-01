package invadem;
import processing.core.PApplet;
import processing.core.PImage;

public class PowerInvader extends Invader{
  public PowerInvader(int x, int y, int width, int height){
    super(x, y, width, height);
    this.powered = true;
  }

  public boolean sustainDamage(){
    return true;
  }




}

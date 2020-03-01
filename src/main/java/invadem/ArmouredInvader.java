package invadem;
import processing.core.PApplet;
import processing.core.PImage;

public class ArmouredInvader extends Invader{

  private int hitpoints;

  public ArmouredInvader(int x, int y, int width, int height){
    super(x, y, width, height);
    this.armoured = true;
    this.hitpoints = 3;
  }

  public boolean sustainDamage(){
    this.hitpoints -= 1;
    if (this.hitpoints == 0){
      return true;
    }
    return false;
  }


}

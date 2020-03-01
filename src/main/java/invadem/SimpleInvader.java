package invadem;
import processing.core.PApplet;
import processing.core.PImage;

public class SimpleInvader extends Invader{

  public SimpleInvader(int x, int y, int width, int height){
    super(x, y, width, height);
  }

  public boolean sustainDamage(){
    return true;
  }
}

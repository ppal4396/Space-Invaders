package invadem;
import processing.core.PImage;

public class PlayerOne extends Tank{

  public PlayerOne(PImage img, int x, int y, int width, int height, PImage emptySprite){
    super(img, x, y, width, height, emptySprite);

  }

  public void handleKeyEvent(boolean[] keys){
      if (keys[' ']){
          this.shooting = true;
      }
      else {
          this.reloaded = true;
          this.shooting = false;
      }

      if (keys[128] && keys[129]){
          this.velocity[0] = 0;
      }
      else if (keys[128]){
          this.velocity[0] = -1;
      }
      else if (keys[129]){
          this.velocity[0] = 1;
      }
      else {
          this.velocity[0] = 0;
      }
  }
}

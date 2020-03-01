package invadem;
import processing.core.PApplet;
import processing.core.PImage;

public class BarrierComponent extends GameObject{

    private int hitpoints;
    private PImage img;
    private PImage[] sprites;
    private boolean destroyed;



    public BarrierComponent(int x, int y, int width, int height){
      super(x, y, width, height);
      this.hitpoints = 3;
      this.sprites = new PImage[4];
      this.destroyed = false;

    }

    public boolean isDestroyed(){
      return this.destroyed;
    }

    public void spawn(){
      this.hitpoints = 3;
      this.img = sprites[0];
      this.destroyed = false;
    }

    public void instaKill(){
      this.destroyed = true;
      this.img = this.sprites[3];
    }
    public void sustainDamage(){
        if (!this.destroyed){
          this.hitpoints -= 1;
          if (this.hitpoints == 0){
            this.destroyed = true;
          }
          this.img = this.sprites[3 - this.hitpoints];
        }
    }

    public void setSprites(PImage[] componentSprites, PImage emptySprite){
      for(int i = 0; i < componentSprites.length; i++){
        this.sprites[i] = componentSprites[i];
      }
      this.sprites[3] = emptySprite;
      this.img = this.sprites[0];
    }


    public void draw(App app){
        app.image(img, x, y, width, height);
    }
}

package invadem;
import processing.core.PApplet;
import processing.core.PImage;

public class Projectile extends GameObject{
    private PImage img;
    private boolean enemyProjectile;
    private boolean powered;

    public Projectile(PImage img, int x, int y, int width, int height){
        super(x, y, width, height);
        this.img = img;
        this.velocity = new int[] {0, -1};
        this.enemyProjectile = false;
        this.powered = false;
    }


    public void setEnemyProjectile(){
        this.enemyProjectile = true;
        this.velocity[1] = 1;
    }

    public void setPowered(PImage large){
      this.powered = true;
      this.width = 2;
      this.height = 5;
      this.img = large;
    }

    public boolean isEnemyProjectile(){
        return this.enemyProjectile;
    }

    public boolean isPowered(){
      return this.powered;
    }

    public void tick(){
      this.x += this.velocity[0];
      this.y += this.velocity[1];
    }

    public void draw(App app){
        app.image(img, x, y, width, height);
        tick();
    }

}

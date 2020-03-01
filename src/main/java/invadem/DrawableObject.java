package invadem;
import processing.core.PApplet;


public abstract class DrawableObject{
    protected int x;
    protected int y;
    protected int tickRate; //Used to update every n frames where n = tickRate.
    protected int frameCounter;

    public DrawableObject(int x, int y){
      this.x = x;
      this.y = y;
      this.frameCounter = 0;
    }

    public int getX(){
      return this.x;
    }

    public int getY(){
      return this.y;
    }

    public void setPosition(int x, int y){
      this.x = x;
      this.y = y;
    }

    public boolean checkCollision(GameObject one, GameObject two){
      if (    (one.getX() < (two.getX() + two.getWidth())) &&
              ((one.getX() + one.getWidth()) > two.getX()) &&
              (one.getY() < (two.getY() + two.getHeight()))&&
              ((one.getHeight() + one.getY()) > two.getY())    ){
                return true;
      }
      return false;
    }
    public boolean tickFrame(){ //returns true if current frame is a nth frame
      if(this.frameCounter > this.tickRate){
        this.frameCounter = 0;
      }
      if (this.frameCounter == this.tickRate){
        this.frameCounter = 0;
        return true;
      }
      this.frameCounter += 1;
      return false;
    }

    public abstract void draw(App app);


}

package invadem;
import processing.core.PApplet;


public abstract class GameObject extends DrawableObject{
    protected int width;
    protected int height;
    protected int[] velocity;

    public GameObject(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
      return this.width;
    }

    public int getHeight(){
      return this.height;
    }

    public abstract void draw(App app);
}

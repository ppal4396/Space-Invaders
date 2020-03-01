package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class MenuObject extends GameObject{

  public MenuObject(int x, int y, int width, int height){
    super(x, y, width, height);
  }

  public abstract void display();

  public abstract void draw(App app);

  }

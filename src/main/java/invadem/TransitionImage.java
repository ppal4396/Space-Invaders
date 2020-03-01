package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class TransitionImage extends MenuObject{
    private PImage display;
    private PImage transitionImg;
    private PImage emptySprite;

    public TransitionImage(int x, int y, int width, int height){
      super(x, y, width, height);
    }

    public void setDisplay(PImage transitionImg, PImage emptySprite){
      this.transitionImg = transitionImg;
      this.emptySprite = emptySprite;
      this.display = emptySprite;
    }

    public void display(){
      this.display = transitionImg;
    }

    public void hide(){
      this.display = emptySprite;
    }

    public void draw(App app){
      app.image(display, x, y, width, height);
    }

    }

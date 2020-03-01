package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Score extends MenuObject{
  private String title;
  private String display;
  private int scoreCount;

  public Score(int x, int y, int width, int height){
    super(x, y, width, height);
  }
  public void setDisplay(String title, int num){
    this.title = title;
    this.scoreCount = num;
    display();
  }
  public void add(int num){
    this.scoreCount += num;
  }
  public void reset(){
    this.scoreCount = 0;
  }
  public void display(){
    this.display = title + Integer.toString(scoreCount);
  }
  public int getScore(){
    return this.scoreCount;
  }
  public void setScore(int num){
    this.scoreCount = num;
  }

  public void draw(App app){
      app.text(display, x, y, width, height);
  }
}

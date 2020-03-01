package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import processing.core.PFont;


public class App extends PApplet {
    PlayerOne player1;
    Barrier leftBarrier;
    Barrier middleBarrier;
    Barrier rightBarrier;
    Swarm invaderSwarm;
    ProjectileMatrix projMatrix;
    Menu menu;
    List<DrawableObject> drawObjects; //[1x tank, 3 x barriers, 1x swarm of invaders, 1x projectile matrix, 1x menu]

    AudioPlayer audioPlayer;
    boolean[] keys;



    public App() {
      leftBarrier = new Barrier(200, 420);
      middleBarrier = new Barrier(309, 420);
      rightBarrier = new Barrier(418, 420);

      menu = new Menu(0, 0);
      drawObjects = new ArrayList<>();

      audioPlayer = new AudioPlayer();
      keys = new boolean[256];



    }

    public void setup() {
        frameRate(60);
        PImage emptySprite = loadImage("empty.png");
        player1 = new PlayerOne(loadImage("tank1.png"), 309, 454, 22, 16, emptySprite);
        drawObjects.add(player1);

        PImage[] leftComponentSprites = new PImage[3];
        PImage[] rightComponentSprites = new PImage[3];
        PImage[] solidComponentSprites = new PImage[3];
        leftComponentSprites[0] = loadImage("barrier_left1.png");
        leftComponentSprites[1] = loadImage("barrier_Left2.png");
        leftComponentSprites[2] = loadImage("barrier_Left3.png");
        rightComponentSprites[0] = loadImage("barrier_right1.png");
        rightComponentSprites[1] = loadImage("barrier_right2.png");
        rightComponentSprites[2] = loadImage("barrier_right3.png");
        solidComponentSprites[0] = loadImage("barrier_solid1.png");
        solidComponentSprites[1] = loadImage("barrier_solid2.png");
        solidComponentSprites[2] = loadImage("barrier_solid3.png");


        leftBarrier.setComponentSprites(leftComponentSprites, rightComponentSprites, solidComponentSprites, emptySprite);
        middleBarrier.setComponentSprites(leftComponentSprites, rightComponentSprites, solidComponentSprites, emptySprite);
        rightBarrier.setComponentSprites(leftComponentSprites, rightComponentSprites, solidComponentSprites, emptySprite);
        drawObjects.add(leftBarrier);
        drawObjects.add(middleBarrier);
        drawObjects.add(rightBarrier);

        invaderSwarm = new Swarm(184, 0, loadImage("invader1.png"), loadImage("invader2.png"),
        loadImage("invader1_power.png"), loadImage("invader2_power.png"),
        loadImage("invader1_armoured.png"), loadImage("invader2_armoured.png"));

        drawObjects.add(invaderSwarm);

        projMatrix = new ProjectileMatrix(loadImage("projectile.png"), loadImage("projectile_lg.png"));
        drawObjects.add(projMatrix);

        PFont gameFont = createFont("PressStart2P-Regular.ttf", 8);
        textFont(gameFont);
        menu.prepareDisplays(loadImage("gameover.png"), loadImage("nextlevel.png"), emptySprite);
        drawObjects.add(menu);





    }

    public void settings() {
        size(640, 480);
    }

    public void draw() {
        background(0);
        for(DrawableObject obj: drawObjects){
            obj.draw(this);
        }
    }

    public void keyPressed(){
      //index in keys[] with ASCII value of last key pressed will save true until released (see keyReleased()).
      //Indices 128 and 129 added for special keys LEFT and RIGHT.
        if (key == CODED){
          if (keyCode == LEFT){
            keys[128] = true;
          }
          if (keyCode == RIGHT){
            keys[129] = true;
          }
        }
        else{
          keys[key] = true;
        }
        player1.handleKeyEvent(keys);
    }

    public void keyReleased(){
      //index in keys[] with ASCII value of last key released will save false until pressed (see keyPressed()).
      // Indices 128 and 129 added for special keys LEFT and RIGHT.
        if (key == CODED){
          if (keyCode == LEFT){
            keys[128] = false;
          }
          if (keyCode == RIGHT){
            keys[129] = false;
          }
        }
        else{
          keys[key] = false;
        }
        player1.handleKeyEvent(keys);
    }

    public Swarm getSwarm(){
      return invaderSwarm;
    }

    public PlayerOne getPlayerOne(){
      return player1;
    }

    public List<Barrier> getBarriers(){
      List<Barrier> barriers = Arrays.asList(leftBarrier, middleBarrier, rightBarrier);
      return barriers;
    }

    public Menu getMenu(){
      return menu;
    }

    public ProjectileMatrix getProjMatrix(){
      return projMatrix;
    }

    public AudioPlayer getAudioPlayer(){
      return audioPlayer;
    }


    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}

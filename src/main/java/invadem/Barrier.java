package invadem;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Barrier extends DrawableObject implements Iterable<BarrierComponent>{
    private BarrierComponent left;
    private BarrierComponent top;
    private BarrierComponent right;
    private BarrierComponent lSolidTop;
    private BarrierComponent lSolidBot;
    private BarrierComponent rSolidTop;
    private BarrierComponent rSolidBot;
    private List<BarrierComponent> components;

    public Barrier(int x, int y){
      super(x, y);
      this.left = new BarrierComponent(x, y, 8, 8);
      this.top = new BarrierComponent(x + 8, y, 8, 8);
      this.right = new BarrierComponent(x + 16, y, 8, 8);
      this.lSolidTop = new BarrierComponent(x, y + 8, 8, 8);
      this.lSolidBot = new BarrierComponent(x, y + 16, 8, 8);
      this.rSolidTop = new BarrierComponent(x + 16, y + 8, 8, 8);
      this.rSolidBot = new BarrierComponent(x + 16, y + 16, 8, 8);
      components = Arrays.asList(this.left, this.top, this.right, this.lSolidTop, this.lSolidBot, this.rSolidTop, this.rSolidBot);
    }

    public void setComponentSprites(
    PImage[] leftComponentSprites, PImage[] rightComponentSprites,
    PImage[] solidComponentSprites, PImage emptySprite
    ){
      this.left.setSprites(leftComponentSprites, emptySprite);
      this.top.setSprites(solidComponentSprites, emptySprite);
      this.right.setSprites(rightComponentSprites, emptySprite);
      this.lSolidTop.setSprites(solidComponentSprites, emptySprite);
      this.rSolidTop.setSprites(solidComponentSprites, emptySprite);
      this.lSolidBot.setSprites(solidComponentSprites, emptySprite);
      this.rSolidBot.setSprites(solidComponentSprites, emptySprite);
    }

    public Iterator<BarrierComponent> iterator(){
      return this.components.iterator();
    }

    public void spawn(){
      for(BarrierComponent component:this){
        component.spawn();
      }
    }

    public void clearComponents(){
      for(BarrierComponent component:this){
        component.instaKill();
      }
    }

    public List<BarrierComponent> getComponentList(){
      return this.components;
    }


    public void draw(App app){
        for(BarrierComponent component:this){
            component.draw(app);
        }
    }

}

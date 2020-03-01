package invadem;
import java.util.List;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class ProjectileMatrix extends DrawableObject{

  private List<Projectile> tankProjectiles;
  private List<Projectile> enemyProjectiles;
  private PImage projSprite;
  private PImage powerProjSprite;

  public ProjectileMatrix(PImage projSprite, PImage powerProjSprite){
    super(0, 0);
    this.projSprite = projSprite;
    this.powerProjSprite = powerProjSprite;
    this.tankProjectiles = new ArrayList<Projectile>();
    this.enemyProjectiles = new ArrayList<Projectile>();
    this.tickRate = 300;
  }

  public void setLevelSpeed(Score level){
    if (level.getScore() == 1){
      this.tickRate = 300;
    }
    else if (this.tickRate > 60){
      this.tickRate = 300 - ((level.getScore() - 1) * 60);
    }
    else if (this.tickRate <= 60){
      if (this.tickRate != 0){
        this.tickRate -= 1;
      }
    }
  }

  public void addTankProjectile(Tank tank){
    this.tankProjectiles.add(new Projectile(projSprite, tank.getX() + 11, tank.getY() - 3, 1, 3));
  }
  public void addInvaderProjectile(App app){
    Invader invader = app.getSwarm().getRandomInvader();
    Projectile enemyProj = new Projectile(projSprite, invader.getX() + 8, invader.getY() + 16, 1 , 3);
    enemyProj.setEnemyProjectile();
    if (invader.isPowered()){
      enemyProj.setPowered(powerProjSprite);
    }
    this.enemyProjectiles.add(enemyProj);

    app.getAudioPlayer().playEnemyShot(enemyProj.isPowered());
  }

  public List<Projectile> getTankProjectiles(){
    return this.tankProjectiles;
  }
  public List<Projectile> getEnemyProjectiles(){
    return this.enemyProjectiles;
  }



  public void updateInvaderCollisions(App app){
    List<Invader> invaders = app.getSwarm().getInvaderList();
    for(int i = 0; i < invaders.size(); i ++){
      for(int j = 0; j < tankProjectiles.size(); j++){
        if (checkCollision(invaders.get(i), tankProjectiles.get(j))){
            boolean armourDead = invaders.get(i).sustainDamage();
            if (armourDead){
              app.getMenu().addPoints(invaders.get(i).isArmoured(), invaders.get(i).isPowered());
              invaders.remove(i);
              app.getAudioPlayer().playInvaderExplosion();
            }
          tankProjectiles.remove(j);

          if(invaders.size() == 0){
            app.getSwarm().setLose();
            break;
          }
          else{
            if(i != 0){
              i --;
            }
            if(j != 0){
              j --;
            }
          }
        }
      }
    }
  }

  public void updateBarrierCollisions(App app){
    List<Barrier> barriers = app.getBarriers();
    for(Barrier barrier:barriers){

      for(BarrierComponent component:barrier){
        for(int i = 0; i < enemyProjectiles.size(); i++ ){
          if (!component.isDestroyed()){
            if(checkCollision(component, enemyProjectiles.get(i))){
              if (enemyProjectiles.get(i).isPowered()){
                component.instaKill();
              }
              else{
                component.sustainDamage();
              }
              app.getAudioPlayer().playExplosion();
              enemyProjectiles.remove(i);
              i --;
            }
          }
        }
        for(int i = 0; i < tankProjectiles.size(); i++){
          if(!component.isDestroyed()){
            if(checkCollision(component, tankProjectiles.get(i))){
              component.sustainDamage();
              app.getAudioPlayer().playExplosion();
              tankProjectiles.remove(i);
              i --;
            }
          }
        }
      }

    }
  }

  public void updateTankCollisions(App app){
    for(int i = 0; i < enemyProjectiles.size(); i++){
      if(checkCollision(app.getPlayerOne(), enemyProjectiles.get(i))){
        if(enemyProjectiles.get(i).isPowered()){
          app.getPlayerOne().instaKill();
        }
        else{
          app.getPlayerOne().sustainDamage();
        }
        app.getAudioPlayer().playTankDamage();
        enemyProjectiles.remove(i);
        i--;
      }
    }
  }

  public void updatePlayerShots(App app){
    PlayerOne player1 = app.getPlayerOne();
    if (player1.isShooting() && player1.isReloaded() && !app.getMenu().isInTransition()){
      addTankProjectile(player1);
      app.getAudioPlayer().playTankShot();
      player1.finishShooting();
    }
  }

  public void clearAllShots(){
      this.tankProjectiles = new ArrayList<Projectile>();
      this.enemyProjectiles = new ArrayList<Projectile>();
  }

  public void generateSwarmShot(App app){
    //if there is at least one invader alive, create new enemy projectile.
    if (app.getSwarm().getInvaderList().size() > 0 && !(app.getMenu().isInTransition())){
      addInvaderProjectile(app);
    }
  }

  public void updateOffScreen(App app){
    //if a projectile goes off screen, remove it from the projectile matrix.
    for(int i = 0; i < this.tankProjectiles.size(); i++){
        if (this.tankProjectiles.get(i).getY() < 0){
          this.tankProjectiles.remove(i);
          if (tankProjectiles.size() == 0){
            break;
          }
          i--;
        }
    }
    for(int i = 0; i < this.enemyProjectiles.size(); i++){
        if(this.enemyProjectiles.get(i).getY() > 480){
          this.enemyProjectiles.remove(i);
          if (enemyProjectiles.size() == 0){
            break;
          }
          i--;
      }
    }
  }

  public int getTickRate(){
    return this.tickRate;
  }

  public void fastTick(App app){
    updatePlayerShots(app);
    updateInvaderCollisions(app);
    updateBarrierCollisions(app);
    updateTankCollisions(app);
  }

  public void slowTick(App app){
    generateSwarmShot(app);
    updateOffScreen(app);
  }

  public void draw(App app){
    for(Projectile proj:tankProjectiles){
      proj.draw(app);
    }
    for(Projectile proj:enemyProjectiles){
      proj.draw(app);
    }

    fastTick(app);

    if (tickFrame()){
      slowTick(app);
    }
  }

}

//This abstract class provides methods for every turret which actually shoots a projectile
package model.turret;

import javafx.collections.ObservableList;
import model.Battlefield;
import model.enemy.Enemy;
import model.projectile.Projectile;

public abstract class TargetedTurret extends Turret {

    private Projectile projectile;
    private double range;

    public TargetedTurret(int hp, int x, int y,Battlefield battlefield,double range,Projectile projectile) {
        super(hp, x, y, battlefield);
        this.projectile = projectile;
        this.range = range;
        this.projectile.setParent(this);
    }
    
    public Enemy firstEnemyInRange() { //Finds the firstEnemyInRange
    	ObservableList<Enemy> list= this.getBattlefield().getEnemyList();
    	for (int i = 0; i < list.size(); i++) {
			Enemy e = list.get(i);
			if(Math.sqrt(Math.pow(e.getX()-this.getX(),2)+Math.pow(e.getY()-this.getY(), 2))<this.range){ // distance calculation
				if( !e.isDead())
					return e;
			}
		}
    	return null;
    }
    
    public void shoot() { //Shoots an enemy using its projectile
    	this.getBattlefield().removeProjectile(projectile);
    	if(!this.projectile.shoot(firstEnemyInRange())) { //If no enemy is found removes the projectile
    		this.getBattlefield().removeProjectile(projectile);
    	}
    	else {
        	this.getBattlefield().addProjectile(this.getProjectile());
    	}
    	this.projectile.resetProjectile();
    }
    
    public void action() {
    	if(this.isDead()) { //Remove the turret and its projectile if it is dead
        	this.getBattlefield().removeProjectile(projectile);
    		super.getBattlefield().removeTurret(this);
    	}
    	else {
    		this.shoot();
    	}
    }
    
    public Projectile getProjectile() {
    	return this.projectile;
    }

}

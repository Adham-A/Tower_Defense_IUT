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
    
    public Enemy firstEnemyInRange() {
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
    
    public void shoot() {
    	this.getBattlefield().removeProjectile(projectile);
    	if(!this.projectile.shoot(firstEnemyInRange())) {
    		this.getBattlefield().removeProjectile(projectile);
    	}
    	else {
        	this.getBattlefield().addProjectile(this.getProjectile());
    	}
    	this.resetProjectile();
    }
    
    public void resetProjectile() {
    	this.projectile.setXProperty(this.getX());
    	this.projectile.setYProperty(this.getY());
    }
    
    public void action() {
    	if(this.isDead()) {
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

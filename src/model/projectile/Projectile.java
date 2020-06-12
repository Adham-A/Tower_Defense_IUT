/*
 * Abstract Class Projectile
 * which grants to all projectiles their main attributes and the method shoot().
 */

package model.projectile;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.enemy.Enemy;
import model.turret.TargetedTurret;


public abstract class Projectile {
	
	private int damage;
	private DoubleProperty x;
	private DoubleProperty y;
	private int id;
	private static int ids = 0;
	private TargetedTurret parent;
	
	public Projectile(int damage,double x, double y) {
		this.damage = damage;
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.id = Projectile.ids;
		ids++;
	}

	// Returns true if the projectile has an enemy that exists.
	public boolean shoot(Enemy e) {
		if(e!=null) {
			e.removeHp(this);
			this.x.setValue(e.getEdge().getX()+0.1); //this helps triggering listeners when x doesn't change
			this.y.setValue(e.getEdge().getY()+0.1); //this helps triggering listeners when y doesn't change

			return true;
		}
		return false;
	}
	
	public String getId() {
		return "P" + this.id;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public DoubleProperty getXProperty() {
		return this.x;
	}
	
	public DoubleProperty getYProperty() {
		return this.y;
	}
	
	public void setXProperty(int value) {
		this.x.setValue(value);
	}
	
	public void setYProperty(int value) {
		this.y.setValue(value);
	}

	public void setParent(TargetedTurret parent) {
		this.parent = parent;
	}

	public TargetedTurret getParent() {
		return this.parent;
	}
}

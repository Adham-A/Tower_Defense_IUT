package model.projectile;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.enemy.Enemy;

public class Projectile {
	
	private int damage;
	private int speed;
	private DoubleProperty x;
	private DoubleProperty y;
		
	public Projectile(int damage, int speed,double x, double y) {
		this.damage = damage;
		this.speed = speed;
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
	}
	
	public void shoot(Enemy e) {
		if(!(e==null) ) {
			e.removeHp(this.damage);
		}
	}
	
	public int getDamage() {
		return damage;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public DoubleProperty getXProperty() {
		return this.x;
	}
	
	public DoubleProperty getYProperty() {
		return this.y;
	}
}

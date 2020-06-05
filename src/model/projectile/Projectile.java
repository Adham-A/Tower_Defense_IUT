package model.projectile;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;
import model.enemy.Enemy;

public class Projectile {
	
	private int damage;
	private int speed;
	private Timeline timeline;
	private DoubleProperty x;
	private DoubleProperty y;
		
	public Projectile(int damage, int speed,double x, double y) {
		this.damage = damage;
		this.speed = speed;
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		
		/*this.getXProperty().addListener((obs_value,old_value,new_value)-> { this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(this.getXProperty(),old_value.intValue()*32)),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(this.getXProperty(),new_value.intValue()*32))
                );
	 			this.timeline.play();
		});
		
		this.getYProperty().addListener((obs_value,old_value,new_value)-> { this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(this.getYProperty(),old_value.intValue()*32)),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(this.getYProperty(),new_value.intValue()*32))
                );
     			this.timeline.play();
     });*/
	}
	
	public void shoot(Enemy e) {
		if(!(e==null) ) {
			e.removeHp(this.damage);
			this.x.setValue(e.getEdge().getParent().getX());
			this.y.setValue(e.getEdge().getParent().getY());
			System.out.println(this.x + ", " + this.y);
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
	
	public void setXProperty(int value) {
		this.x.setValue(value);
	}
	
	public void setYProperty(int value) {
		this.y.setValue(value);
	}
}

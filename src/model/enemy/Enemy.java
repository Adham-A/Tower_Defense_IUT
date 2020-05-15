package model.enemy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Battlefield;

public abstract class Enemy {

	private String id;
	private int hp;
	private int speed;

	private IntegerProperty x, y;

	public Enemy(int hp, int x, int y) {

		this.hp = hp;

		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
	}
	
	public abstract void action();
	
	public void add(Battlefield battlefield) {
		battlefield.addEnemy(this);
	}
	
// id
	String getId() {
		return this.id;
	}

// hp
	int getHp() {
		return this.hp;
	}

	void setHp(int n) {
		this.hp = n;
	}

// speed
	int getSpeed() {
		return this.speed;
	}

	void setSpeed(int n) {
		this.speed = n;
	}

// x
	IntegerProperty getXProperty() {
		return this.x;
	}

	int getX() {
		return this.x.getValue();
	}

	void setX(int n) {
		this.x.setValue(n);
	}
// y
	IntegerProperty getYProperty() {
		return this.y;
	}

	int getY() {
		return this.y.getValue();
	}

	void setY(int n) {
		this.y.setValue(n);
	}

}

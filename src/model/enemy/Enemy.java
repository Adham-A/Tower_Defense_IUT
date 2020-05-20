package model.enemy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Battlefield;

public abstract class Enemy {

	private int id;
	private int hp;
	private int speed;
	private static int ids = 0;

	private IntegerProperty x, y;

	public Enemy(int hp, int x, int y) {
		++ids;
		this.hp = hp;
		this.id = ids;

		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
	}
	
	public abstract void action();
	
	public void add(Battlefield battlefield) {
		battlefield.addEnemy(this);
	}
	
// id
	public int getId() {
		return this.id;
	}

// hp
	public int getHp() {
		return this.hp;
	}

	public void setHp(int n) {
		this.hp = n;
	}

// speed
	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int n) {
		this.speed = n;
	}

// x
	public IntegerProperty getXProperty() {
		return this.x;
	}

	public int getX() {
		return this.x.getValue();
	}

	public void setX(int n) {
		this.x.setValue(n);
	}
// y
	public IntegerProperty getYProperty() {
		return this.y;
	}

	public int getY() {
		return this.y.getValue();
	}

	public void setY(int n) {
		this.y.setValue(n);
	}

}

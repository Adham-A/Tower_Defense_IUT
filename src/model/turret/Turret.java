package model.turret;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Turret {

	private String id;
	private int hp;

	IntegerProperty x, y;

	public Turret(int hp, int x, int y) {

		this.hp = hp;

		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
	}

	public abstract void action();

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

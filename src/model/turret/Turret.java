package model.turret;

public abstract class Turret {

	private String id;
	private int hp;
	private int x, y;

	public Turret(int hp, int x, int y) {

		this.hp = hp;

		this.x = x;
		this.y = y;
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

	int getX() {
		return this.x;
	}

	void setX(int n) {
		this.x = n;
	}

// y

	int getY() {
		return this.y;
	}

	void setY(int n) {
		this.y = n;
	}
}

package model.turret;

import model.Battlefield;

public abstract class Turret {

	private int id;
	private int hp;
	private int x, y;
	private Battlefield battlefield;
	
	private static int ids = 0;

	public Turret(int hp, int x, int y,Battlefield battlefield) {

		++ids;
		this.hp = hp;
		this.id = ids;
		this.hp = hp;

		this.x = x;
		this.y = y;
		this.battlefield = battlefield;
	}

	public abstract void action();
	
	public Battlefield getBattlefield() {
		return this.battlefield ;
	}
// id
	public String getId() {
		return "T" + this.id ;
	}

// hp
	public int getHp() {
		return this.hp;
	}

	public void setHp(int n) {
		this.hp = n;
	}

// x
	public int getX() {
		return this.x;
	}

	public void setX(int n) {
		this.x = n;
	}

// y
	public int getY() {
		return this.y;
	}

	public void setY(int n) {
		this.y = n;
	}
}

package model.enemy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Battlefield;
import model.Edge;
import model.projectile.Projectile;

public abstract class Enemy {

	protected Battlefield battlefield;
	private int id;
	private int hp;
	private int speed;
	private static int ids = 0;

	private IntegerProperty x, y;
	private Edge edge;

	public Enemy(int hp, int x, int y, Battlefield battlefield) {
		++ids;
		this.battlefield = battlefield;
		this.hp = hp;
		this.id = ids;

		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.edge = fetchEdge();
	}
	
	public void action() {
		
		if(!this.isDead()) {
			this.move();
        }
        else {
        	if ( this instanceof Quartz) {
        		this.battlefield.gainMoney(10);
        	}
        	if ( this instanceof Emerald) {
        		this.battlefield.gainMoney(20);
        	}
        	if (this instanceof Saphir) {
        		this.battlefield.gainMoney(35);
        	}
        	
        	this.battlefield.removeEnemy(this);
        }
	}
	
	public void move() {
		if(this.edge.getParent()!=null) {
			this.y.set(this.edge.getParent().getY());
			this.x.set(this.edge.getParent().getX());
			this.edge = this.edge.getParent();
		}
		else {
			System.out.println("Vous êtes à la fin du chemin ");
		}
	}
	
	public Edge fetchEdge() {
		for ( int i = this.battlefield.getGraph().getArrayListEdges().size()-1 ; i > 0 ; i-- ) {
			if (this.battlefield.getGraph().getArrayListEdges().get(i).getX() == this.getX() && this.battlefield.getGraph().getArrayListEdges().get(i).getY() == this.getY()) {
				return this.battlefield.getGraph().getArrayListEdges().get(i);
			}
		}
		return null;
	}

	public boolean isDead() {
		return this.hp <= 0;
	}
	
	public void add(Battlefield battlefield) {
		battlefield.addEnemy(this);
	}
	
// id
	public String getId() {
		return "E" + this.id;
	}

// hp
	public int getHp() {
		return this.hp;
	}

	public void setHp(int n) {
		this.hp = n;
	}
	
	public void removeHp(Projectile projectile) {
		this.hp -= projectile.getDamage();
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
	
	public Edge getEdge() {
		return this.edge;
	}

}

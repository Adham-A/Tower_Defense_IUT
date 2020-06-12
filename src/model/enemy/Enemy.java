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
	private static int ids = 0;

	private IntegerProperty x, y;
	private Edge edge;
	
	public abstract int moneyDrop(); // Return the money than give the enemy when it dies

	public Enemy(int hp, int x, int y, Battlefield battlefield) {
		++ids;
		this.battlefield = battlefield;
		this.hp = hp;
		this.id = ids;

		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.edge = fetchEdge();
	}
	
	public void action() { // Function called every gameloop to make the enemy move or die
		if(this.isDead()) {
			this.battlefield.removeEnemy(this);
			this.battlefield.addMoney(this.moneyDrop());
        }
        else {
			this.move();
        }
	}

	public void move() { // Make the enemy follow the way to the next tile
		if(this.edge.getParent()!=null) {
			this.y.set(this.edge.getParent().getY());
			this.x.set(this.edge.getParent().getX());
			this.edge = this.edge.getParent();
		}
		else {
			if(this.battlefield.getHp() > 0)
			this.battlefield.removeHp();
			this.battlefield.removeEnemy(this);
		}
	}
	
	public Edge fetchEdge() { // Find the edge where the enemy is
		for ( int i = this.battlefield.getGraph().getArrayListEdges().size()-1 ; i > 0 ; i-- ) {
			if (this.battlefield.getGraph().getArrayListEdges().get(i).getX() == this.getX() && this.battlefield.getGraph().getArrayListEdges().get(i).getY() == this.getY()) {
				return this.battlefield.getGraph().getArrayListEdges().get(i);
			}
		}
		return null;
	}

	public boolean isDead() { // Return true if the enemy is dead
		return this.hp <= 0;
	}
	
	public void add(Battlefield battlefield) { // Add the enemy to the list of enemies of his battlefield
		battlefield.addEnemy(this);
	}
	
	public String getId() { // Return the id of the enemy
		return "E" + this.id;
	}

	public int getHp() { // Return the hp of the enemy
		return this.hp;
	}

	public void setHp(int n) { // Set the hp of the enemy
		this.hp = n;
	}
	
	public void removeHp(Projectile projectile) { // Remove the hp of the enemy when it's hit by a projectile
		this.hp -= projectile.getDamage();
	}

	public void removeHp(int damage) { // Remove the hp by a certain amount
		this.hp -= damage;
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

	public Battlefield getBattlefield() {
		return this.battlefield;
	}
	
	public void setEdge(Edge e) {
		this.edge = e;
	}
}

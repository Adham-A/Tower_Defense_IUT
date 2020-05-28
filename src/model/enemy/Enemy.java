package model.enemy;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Battlefield;
import model.Edge;

public abstract class Enemy {

	protected Battlefield battlefield;
	private int id;
	private int hp;
	private int speed;
	private static int ids = 0;

	private IntegerProperty x, y;
	private Edge edge;
	private DoubleProperty x_pixel,y_pixel;

	public Enemy(int hp, int x, int y, Battlefield battlefield) {
		++ids;
		this.battlefield = battlefield;
		this.hp = hp;
		this.id = ids;

		this.x = new SimpleIntegerProperty(x);
		this.x.set(x);
		this.y = new SimpleIntegerProperty(y);
		this.y.set(y);
		this.x_pixel = new SimpleDoubleProperty(x);
		this.y_pixel = new SimpleDoubleProperty(y);
		
		this.edge = fetchEdge();
	}
	
	public abstract void action();
	
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
// x_pixel

	public DoubleProperty getX_pixelProperty() {
		return this.x_pixel;
	}
	
	public double getX_pixel() {
		return this.x_pixel.getValue();
	}

	public void setX_pixel(double x_pixel) {
		this.x_pixel.set(x_pixel);;
	}
// y_pixel
	public DoubleProperty getY_pixelProperty() {
		return this.y_pixel;
	}
	
	public double getY_pixel() {
		return this.y_pixel.getValue();
	}

	public void setY_pixel(double y_pixel) {
		this.y_pixel.set(y_pixel);
	}

}

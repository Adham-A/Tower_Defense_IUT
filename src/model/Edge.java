package model;

public class Edge {
	
	private int x,y;
	private Edge parent;
	
	public Edge( int x,int y) {
		this.x = x ;
		this.y = y ;
		this.parent = null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void addParent(Edge parent) {
		this.parent = parent;
	}
	
	public boolean isAdjacentTo(Edge e) {
		if(e.getX()-this.getX() < -1 || e.getX()-this.getX() > 1 ) { // x coordinates
			return false;
		}
		if(e.getY()-this.getY() < -1 || e.getY()-this.getY() > 1) { // y coordinates
			return false;
		}
		if(e.getX() !=0 && e.getY() !=0) { // No diagonals
			return false;
		}
		return true;
	}
	
	public String toString() {
		return this.getX()+"   "+this.getY();
	}
}

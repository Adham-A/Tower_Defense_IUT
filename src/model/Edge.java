//This class represents an edge
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
		final int prime = 11;
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

	public void setParent(Edge parent) {
		if(this.parent==null) { //Set the parent of the edge only if this edge hasn't.
			this.parent = parent;
		}
	}
	
	public Edge getParent() {
		return this.parent;
	}

}

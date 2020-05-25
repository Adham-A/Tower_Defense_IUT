package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	private Battlefield battlefield;
	private HashMap<Edge, ArrayList<Edge>> adjacencyMap;
	private ArrayList<Edge> edges;
	
	public Graph(Battlefield battlefield) {
	 this.battlefield = battlefield;	
	 adjacencyMap = new HashMap<Edge, ArrayList<Edge>>();
	}
	
	public void createGraph() {
		Edge edge,start,end;
		for (int i = 0; i < battlefield.getWidth(); i++) {
			for (int j = 0; j < battlefield.getHeight(); j++) {
				if(battlefield.isRoad(battlefield.getBattlefieldTile(i, j))) {
					edge = new Edge(i,j);
					adjacencyMap.put(edge, getAllAdjacentEdges(edge));
				}
			}
		}
		start = new Edge(battlefield.getStartCoordinates()[0],battlefield.getStartCoordinates()[1]);
		adjacencyMap.put(start,getAllAdjacentEdges(start));
		
		end = new Edge(battlefield.getEndCoordinates()[0], battlefield.getEndCoordinates()[1]);
		adjacencyMap.put(end,getAllAdjacentEdges(end));
		
	}
	
	public ArrayList<Edge> getAllAdjacentEdges(Edge e) {
		
		ArrayList<Edge> adjacentEdges = new ArrayList<Edge>();
		int x,y;
		x = e.getX()+1;
		y = e.getY();
		
		if(x<battlefield.getWidth() && ( (this.battlefield.isRoad(battlefield.getBattlefieldTile(x,y)) ) ||( this.battlefield.isStart(battlefield.getBattlefieldTile(x,y)) ) ||( this.battlefield.isEnd(battlefield.getBattlefieldTile(x,y))) ) )
		{
			adjacentEdges.add(new Edge(x, y));
		}
		x = e.getX()-1;
		if(x>-1 && ( (this.battlefield.isRoad(battlefield.getBattlefieldTile(x,y)) ) ||( this.battlefield.isStart(battlefield.getBattlefieldTile(x,y)) ) ||( this.battlefield.isEnd(battlefield.getBattlefieldTile(x,y))) ) ){
			adjacentEdges.add(new Edge(x, y));
		}
		
		x = e.getX();
		y = e.getY()-1;
		if(y >-1  && ( (this.battlefield.isRoad(battlefield.getBattlefieldTile(x,y)) ) ||( this.battlefield.isStart(battlefield.getBattlefieldTile(x,y)) ) ||( this.battlefield.isEnd(battlefield.getBattlefieldTile(x,y))) ) ){
			adjacentEdges.add(new Edge(x, y));
		}
		
		y = e.getY()+1;
		if(y<battlefield.getHeight() && ( (this.battlefield.isRoad(battlefield.getBattlefieldTile(x,y)) ) ||( this.battlefield.isStart(battlefield.getBattlefieldTile(x,y)) ) ||( this.battlefield.isEnd(battlefield.getBattlefieldTile(x,y))) ) ){
			adjacentEdges.add(new Edge(x, y));
		}
		
		return adjacentEdges;
	}
	
	public void BFS() {
		Edge end = new Edge(battlefield.getEndCoordinates()[0], battlefield.getEndCoordinates()[1]);
		Queue<Edge> bfsQueue = new LinkedList<Edge>();
		ArrayList<Edge> removedEdges = new ArrayList<Edge>();
		Edge edge;		
		
		bfsQueue.add(end);
		while(!bfsQueue.isEmpty()) {
			edge = bfsQueue.peek();
			for (int i = 0; i<adjacencyMap.get(edge).size();i++ ) {
				if(! removedEdges.contains(adjacencyMap.get(edge).get(i))) {
					adjacencyMap.get(edge).get(i).setParent(edge);
					bfsQueue.add(adjacencyMap.get(edge).get(i));
				}
			}
			removedEdges.add(edge);
			bfsQueue.remove();
		}
		this.edges = removedEdges;
	}
	
	public ArrayList<Edge> getArrayListEdges(){
		return this.edges;
	}
	
}

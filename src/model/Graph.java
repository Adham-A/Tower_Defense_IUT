package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	private Battlefield battlefield;
	private HashMap<Edge, ArrayList<Edge>> adjacencyMap;
	
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
		
		System.out.println(adjacencyMap);
	}
	
	public ArrayList<Edge> getAllAdjacentEdges(Edge e) {
		ArrayList<Edge> adjacentEdges = new ArrayList<Edge>();
		int x,y;
		x = e.getX()+1;
		y = e.getY();
		
		if(x<15 && this.battlefield.isRoad(battlefield.getBattlefieldTile(x,y))){
			adjacentEdges.add(new Edge(x, y));
		}
		x = e.getX()-1;
		if(x>-1 && this.battlefield.isRoad(battlefield.getBattlefieldTile(x,y))){
			adjacentEdges.add(new Edge(x, y));
		}
		
		x = e.getX();
		y = e.getY()-1;
		if(y >-1  && this.battlefield.isRoad(battlefield.getBattlefieldTile(x,y))){
			adjacentEdges.add(new Edge(x, y));
		}
		
		y = e.getY()+1;
		if(y<27 && this.battlefield.isRoad(battlefield.getBattlefieldTile(x,y))){
			adjacentEdges.add(new Edge(x, y));
		}
		
		return adjacentEdges;
	}
}

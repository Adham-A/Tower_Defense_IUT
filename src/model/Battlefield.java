// This  class represents the battlefield with the IDs of the tiles used
package model;

import model.enemy.Enemy;
import model.turret.Turret;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Battlefield {
	
	private int width;
	private int height;
	private int[][] battlefield;
	private TerrainLoader terrainLoader;
	private ObservableList<Enemy> enemyList;
	private ObservableList<Turret> turretList;
	private Graph graph;

	public Battlefield(String path) {
		this.terrainLoader = new TerrainLoader(path);
		this.height = terrainLoader.parseBattlefieldHeight();
		this.width = terrainLoader.parseBattlefieldWidth();
		this.battlefield = terrainLoader.parseBattlefieldFromFile();
		this.enemyList = FXCollections.observableArrayList();
		this.turretList = FXCollections.observableArrayList();
		this.graph = null;
	}
	
	public void addGraph() {
		this.graph = new Graph(this);
		graph.createGraph();
		graph.BFS();
	}
	
	public void addEnemy(Enemy enemy) {
		this.enemyList.add(enemy);
	}
	
	public void addTurret(Turret t) {
		this.turretList.add(t);
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getBattlefieldTile(int x,int y) { 
		return this.battlefield[x][y]; 
	}

	public boolean isEnd(int id) {
		return id>20 && id<40 ;
	}
	
	public boolean isStart(int id) {
		return id>0 && id<21 ;
	}
	
	public boolean isRoad(int id) {
		return id>40 && id<101 ;
	}
	
	public int[] getStartCoordinates(){
		for (int i = 0; i < battlefield.length; i++) {
			for (int j = 0; j < battlefield[0].length; j++) {
				if( isStart(this.battlefield[i][j] ) ) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
	
	public int[] getEndCoordinates() {
		for (int i = 0; i < battlefield.length; i++) {
			for (int j = 0; j < battlefield[0].length; j++) {
				if(isEnd(getBattlefieldTile(i, j))) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
	
	public ObservableList<Enemy> getEnemyList(){
		return this.enemyList;
	}
	
	public ObservableList<Turret> getTurretList(){
		return this.turretList;
	}
	
	public Graph getGraph() {
		return this.graph;
	}

}

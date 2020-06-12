//This class manages the terrain
package model;

import exception.TerrainLoaderException;

public class Terrain {
	private int width;
	private int height;
	private int[][] terrain_map;
	private TerrainLoader terrainLoader;
	
	public Terrain(String path) throws TerrainLoaderException {
		this.terrainLoader = new TerrainLoader(path);
		this.height = terrainLoader.parseBattlefieldHeight();
		this.width = terrainLoader.parseBattlefieldWidth();
		this.terrain_map = terrainLoader.parseBattlefieldFromFile();
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getTerrainTile(int x,int y) { 
		return this.terrain_map[x][y]; 
	}
	
	//Returns true if the tile is an end tile
	public boolean isEnd(int id) {
		return id>20 && id<40 ;
	}
	
	//Returns true if the tile is a start tile
	public boolean isStart(int id) {
		return id>0 && id<21 ;
	}
	
	//Returns true if the tile is a road
	public boolean isRoad(int id) {
		return id>40 && id<101 ;
	}
	
	//Returns true if the tile is available for a turret placement
	public boolean isFree(int id) {
		return id>500 && id<601 ;
	}
	
	//Finds the start tile coordinates
	public int[] getStartCoordinates(){
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				if( isStart(this.terrain_map[i][j] ) ) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
	
	//Finds the end tile coordinates
	public int[] getEndCoordinates() {
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				if(isEnd(this.terrain_map[i][j] )) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
	
}

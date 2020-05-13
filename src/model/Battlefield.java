// This  class represents the battlefield with the IDs of the tiles used
package model;

import java.util.ArrayList;

public class Battlefield {
	
	private int width;
	private int height;
	private int[][] battlefield;
	private BattlefieldLoader battlefieldLoader;
	private ArrayList<Enemy> listEnemy;
	private ArrayList<Turret> listTurret;
	
	public Battlefield(String path) {
		this.battlefieldLoader = new BattlefieldLoader(path);
		this.height = battlefieldLoader.parseBattlefieldHeight();
		this.width = battlefieldLoader.parseBattlefieldWidth();
		this.battlefield = battlefieldLoader.parseBattlefieldFromFile();
		listEnemy = new ArrayList<Enemy>();
		listTurret = new ArrayList<Turret>();
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
	
	public int[] getStartCoordinates(){
		for (int i = 0; i < battlefield.length; i++) {
			for (int j = 0; j < battlefield[0].length; j++) {
				if(this.battlefield[i][j]>0 && this.battlefield[i][j]<21) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
	
	public ArrayList<Enemy> getListEnemy(){
		return this.listEnemy;
	}
	
	public ArrayList<Turret> getListTurret(){
		return this.listTurret;
	}

}

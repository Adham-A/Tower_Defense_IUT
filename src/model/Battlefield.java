// This  class represents the battlefield with the IDs of the tiles used
package model;

import model.enemy.Enemy;
import model.turret.Turret;

import java.util.ArrayList;

public class Battlefield {
	
	private int width;
	private int height;
	private int[][] battlefield;
	private BattlefieldLoader battlefieldLoader;
	private ArrayList<Enemy> enemyList;
	private ArrayList<Turret> turretList;
	
	public Battlefield(String path) {
		this.battlefieldLoader = new BattlefieldLoader(path);
		this.height = battlefieldLoader.parseBattlefieldHeight();
		this.width = battlefieldLoader.parseBattlefieldWidth();
		this.battlefield = battlefieldLoader.parseBattlefieldFromFile();
		this.enemyList = new ArrayList<Enemy>();
		this.turretList = new ArrayList<Turret>();
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
	
	public ArrayList<Enemy> getEnemyList(){
		return this.enemyList;
	}
	
	public ArrayList<Turret> getTurretList(){
		return this.turretList;
	}

}

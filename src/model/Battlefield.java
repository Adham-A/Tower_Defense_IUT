// This  class represents the battlefield with the IDs of the tiles used
package model;

public class Battlefield {
	
	private int width;
	private int height;
	private int[][] battlefield;
	private BattlefieldLoader battlefieldLoader;
	
	public Battlefield(String path) {
		this.battlefieldLoader = new BattlefieldLoader(path);
		this.height = battlefieldLoader.parseBattlefieldHeight();
		this.width = battlefieldLoader.parseBattlefieldWidth();
		this.battlefield = battlefieldLoader.parseBattlefieldFromFile();
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

}

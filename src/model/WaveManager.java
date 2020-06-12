//This class manages the spawn rate and the turns of the game
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import mineralsRevenge.mRUtil;
import model.enemy.Diamond;
import model.enemy.Emerald;
import model.enemy.Quartz;
import model.enemy.Saphir;

public class WaveManager {

	private IntegerProperty turnNumber;
	private Battlefield battlefield;
	private int max_turns; //This describes the turn at which the game should end
	/*
	 * Those counters are used to spawn enemies every turn
	 */
	private int quartz_counter;
	private int emerald_counter;
	private int saphir_counter;
	private int diamond_counter;

	public WaveManager(Battlefield battlefield) {
		this.turnNumber = new SimpleIntegerProperty(0);
		this.battlefield = battlefield;
		this.quartz_counter = 0;
		this.emerald_counter = 0;
		this.saphir_counter = 0;
		this.diamond_counter = 0;
		this.max_turns = mRUtil.maxTurn ;
	}
	public int getTurnNumber() {
		return this.turnNumber.getValue();
	}

	public IntegerProperty getTurnNumberProperty() {
		return this.turnNumber;
	}
	
	public int getMaxTurns() {
		return this.max_turns;
	}

	public void wave() {
		int start_x = battlefield.getTerrain().getStartCoordinates()[0];
		int start_y = battlefield.getTerrain().getStartCoordinates()[1];
		
		this.turnNumber.setValue(this.turnNumber.getValue()+1); //Increase turn by 1

		if(this.turnNumber.getValue() >= mRUtil.quartz_spawnTurn) { //Start adding emeralds at turn x
			
			if( quartz_counter%mRUtil.quartz_spawnrate == 0 ) { //Adds a Quartz every 2 turn
				battlefield.addEnemy(new Quartz(start_x,start_y,battlefield));
			}
			
			quartz_counter++;
		}
		
		if(this.turnNumber.getValue() >= mRUtil.emerald_spawnTurn) { //Start adding emeralds at turn x
			
			if( emerald_counter%mRUtil.emerald_spawnrate == 0 ) { // Adds an Emerald every x turn
 				battlefield.addEnemy(new Emerald(start_x, start_y, battlefield));		
			}
			
			emerald_counter++;
		}
		
		if(this.turnNumber.getValue() >= mRUtil.saphir_spawnTurn) { //Start adding saphirs at turn x
			
			if( saphir_counter%mRUtil.saphir_spawnrate == 0 ) { // Adds a saphir every x turn
				battlefield.addEnemy(new Saphir(start_x, start_y, battlefield));
			}
			
			saphir_counter++;
		}
		
		if(this.turnNumber.getValue()>=mRUtil.diamond_spawnTurn) { //Start adding diamonds at turn x
			
			if( diamond_counter%mRUtil.diamond_spawnrate == 0 ) {// Adds a diamond every x turn
				battlefield.addEnemy(new Diamond(start_x, start_y, battlefield));
			}
			
			diamond_counter++;
		}

	}
}

//This class manages the spawn rate and the turns of the game
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
		this.max_turns = 100 ;
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
		int start_x = 7;
		int start_y = 26;
		
		this.turnNumber.setValue(this.turnNumber.getValue()+1); //Increase turn by 1

		
		if(quartz_counter%2 == 0) { //Adds a Quartz every 2 turn
			battlefield.addEnemy(new Quartz(start_x,start_y,battlefield));
		}
		
		if(this.turnNumber.getValue() > 5) { //Start adding emeralds at turn 5
			if(emerald_counter%3 == 0) { // Adds an Emerald every 3 turn
 				battlefield.addEnemy(new Emerald(start_x, start_y, battlefield));
			}
		}
		
		if(this.turnNumber.getValue() > 20) { //Start adding saphirs at turn 20
			if(saphir_counter%5 == 0) { // Adds a saphir every 5 turn
				battlefield.addEnemy(new Saphir(start_x, start_y, battlefield));
			}
		}
		if(this.turnNumber.getValue() > 50) { //Start adding diamonds at turn 50
			if(diamond_counter%10 == 0) {// Adds a diamond every 10 turn
				battlefield.addEnemy(new Diamond(start_x, start_y, battlefield));
			}
		}
		/*
		 * Counters increase
		 */
		quartz_counter++;
		emerald_counter++;
		saphir_counter++;
		diamond_counter++;

	}
}

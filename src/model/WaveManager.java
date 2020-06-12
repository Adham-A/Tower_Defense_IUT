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
	private int max_turns;
	private int quartz_counter;
	private int emerald_counter;
	private int saphir_counter;
	private int diamond_counter;

	public WaveManager(Battlefield battlefield) {
		this.turnNumber = new SimpleIntegerProperty(0);
		this.battlefield = battlefield;
		this.quartz_counter = 0;
		this.emerald_counter = 1;
		this.saphir_counter = 2;
		this.diamond_counter = 3;
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
		
		this.turnNumber.setValue(this.turnNumber.getValue()+1);

		
		if(quartz_counter%2 == 0) {
			battlefield.addEnemy(new Quartz(start_x,start_y,battlefield));
		}
		
		if(this.turnNumber.getValue() > 5) {
			if(emerald_counter%3 == 0) {
				battlefield.addEnemy(new Emerald(start_x, start_y, battlefield));
			}
		}
		
		if(this.turnNumber.getValue() > 20) {
			if(saphir_counter%5 == 0) {
				battlefield.addEnemy(new Saphir(start_x, start_y, battlefield));
			}
		}
		if(this.turnNumber.getValue() > 50) {
			if(diamond_counter%10 == 0) {
				battlefield.addEnemy(new Diamond(start_x, start_y, battlefield));
			}
		}
		
		quartz_counter++;
		emerald_counter++;
		saphir_counter++;
		diamond_counter++;

	}
}

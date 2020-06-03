package model.enemy;

import model.Battlefield;

public class Quartz extends Mineral {
	
	public Quartz(int x, int y, Battlefield battlefield) {
		super(20, x, y, battlefield);
	}

	@Override
	public void action() {
		this.move();
	}

}

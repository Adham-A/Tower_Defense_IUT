package model.enemy;

import model.Battlefield;

public class Quartz extends Mineral {
	
	public Quartz(int hp,int x, int y, Battlefield battlefield) {
		super(hp, x, y, battlefield);
	}

	@Override
	public void action() {
		super.action();
	}

	@Override
	public int moneyDrop() {
		return 15;
	}

}

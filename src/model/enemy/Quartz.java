package model.enemy;

import mineralsRevenge.mRUtil;
import model.Battlefield;

public class Quartz extends Mineral {
	
	public Quartz(int x, int y, Battlefield battlefield) {
		super(mRUtil.quartz_hp, x, y, battlefield);
	}

	@Override
	public void action() {
		super.action();
	}

	@Override
	public int moneyDrop() {
		return mRUtil.quartz_money;
	}

}

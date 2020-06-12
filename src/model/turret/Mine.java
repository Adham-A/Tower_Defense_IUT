package model.turret;

import mineralsRevenge.mRUtil;
import model.Battlefield;

public class Mine extends Turret {

	public Mine(int x, int y, Battlefield battlefield) {
		super(mRUtil.mine_hp, x, y, battlefield);
	}

	@Override
	public void action() {
		income();
	}
	
	public void income() {
		this.getBattlefield().addMoney(mRUtil.mine_income);
	}

}

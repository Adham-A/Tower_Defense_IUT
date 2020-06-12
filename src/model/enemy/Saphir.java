package model.enemy;

import mineralsRevenge.mRUtil;
import model.Battlefield;
import model.projectile.Projectile;

public class Saphir extends Mineral{

	public Saphir(int x, int y, Battlefield battlefield) {
		super(mRUtil.saphir_hp, x, y, battlefield);
	}
	
	@Override
	public void removeHp(Projectile projectile) {
		super.removeHp(projectile);
		dealBackDamage(projectile);
	}
	
	//deals back 1/saphir_returnedDamageRatio of the damage received to the turret
	public static void dealBackDamage(Projectile projectile) { // 
		projectile.getParent().removeHp(projectile.getDamage()/mRUtil.saphir_returnedDamageRatio);
	}

	@Override
	public int moneyDrop() {
		return mRUtil.saphir_money;
	}

}

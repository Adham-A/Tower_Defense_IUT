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
	
	public static void dealBackDamage(Projectile projectile) { // Return 1/8 of the damages he takes ( his speciality )
		projectile.getParent().removeHp(projectile.getDamage()/8); //deals back 1/8 of the damage received to the turret
	}

	@Override
	public int moneyDrop() {
		return mRUtil.saphir_money;
	}

}

package model.enemy;

import mineralsRevenge.mRUtil;
import model.Battlefield;
import model.projectile.Projectile;

public class Diamond extends Enemy{
	
	public boolean armor;
	
	public Diamond(int x, int y, Battlefield battlefield) {
		super(mRUtil.diamond_hp, x, y, battlefield);
		this.armor = true;
	}
	
	@Override
	public void removeHp(Projectile projectile) {
		if(!armor) {
			super.removeHp(projectile);
		}
	}
	
	@Override
	public void removeHp(int damage) {
		if(!armor) {
			super.removeHp(damage);
		}
	}

	public void setArmor(boolean b) { // Set if it has armor or no 
		this.armor = b;
	}

	public boolean hasArmor() { // Return true if it has armor
		return this.armor;
	}

	@Override
	public int moneyDrop() {
		return mRUtil.diamond_money;
	}

}

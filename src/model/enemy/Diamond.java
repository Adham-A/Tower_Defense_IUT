package model.enemy;

import model.Battlefield;
import model.projectile.Projectile;

public class Diamond extends Enemy{
	
	public boolean armor;
	
	public Diamond(int hp, int x, int y, Battlefield battlefield) {
		super(hp, x, y, battlefield);
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

	public void setArmor(boolean b) {
		this.armor = b;
	}

	public boolean hasArmor() {
		return this.armor;
	}

	@Override
	public int moneyDrop() {
		return 50;
	}

}

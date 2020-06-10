package model.enemy;

import model.Battlefield;
import model.projectile.Projectile;

public class Saphir extends Mineral{

	public Saphir(int hp, int x, int y, Battlefield battlefield) {
		super(hp, x, y, battlefield);
	}
	
	@Override
	public void removeHp(Projectile projectile) {
		super.removeHp(projectile);
		projectile.getParent().removeHp(projectile.getDamage()/2); //deals back half of the damage received to the turret
	}

}

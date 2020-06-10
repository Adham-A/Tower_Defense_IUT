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
		dealBackDamage(projectile);
	}
	
	public static void dealBackDamage(Projectile projectile) {
		projectile.getParent().removeHp(projectile.getDamage()/8); //deals back 1/8 of the damage received to the turret
	}

}

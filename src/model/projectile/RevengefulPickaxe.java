/*
 * Sub class RevengefulPickaxe that extends Projectile,
 * represents the projectile of the DwarfRenegade.
 */
package model.projectile;

import mineralsRevenge.mRUtil;
import model.enemy.Enemy;
import model.turret.Turret;

public class RevengefulPickaxe extends Projectile {

	private Enemy parent;
	
	public RevengefulPickaxe(double x, double y) {
		super(mRUtil.revengefulPickaxe_damage,x, y);
	}

	public boolean shoot(Turret t) {
		if(t!=null) {
			t.removeHp(this.getDamage());
			this.getXProperty().setValue(t.getX()+0.1); //this helps triggering listeners when x doesn't change
			this.getYProperty().setValue(t.getY()+0.1); //this helps triggering listeners when y doesn't change
			return true;
		}
		return false;
	}
	
	@Override
	public void resetProjectile() {
		this.getXProperty().setValue(parent.getX());
		this.getYProperty().setValue(parent.getY());
	}
	
	public void setParent(Enemy e) {
		this.parent = e;
	}

}

/*
 * Sub class DwarfRenegade that extends Enemy,
 * can shoot like a turret.
 */

package model.enemy;

import javafx.collections.ObservableList;
import mineralsRevenge.mRUtil;
import model.Battlefield;
import model.projectile.RevengefulPickaxe;
import model.turret.Turret;

public class DwarfRenegade extends Enemy {
	
	private RevengefulPickaxe projectile;

	public DwarfRenegade(int x, int y, Battlefield battlefield) {
		super(mRUtil.dwarfRenegade_hp, x, y, battlefield);
		this.projectile = new RevengefulPickaxe(x, y);
		this.projectile.setParent(this);
	}
	
	private Turret firstTowerInRange() {
		ObservableList<Turret> list= this.getBattlefield().getTurretList();
    	for (int i = 0; i < list.size(); i++) {
			Turret t = list.get(i);
			if(Math.sqrt(Math.pow(t.getX()-this.getX(),2)+Math.pow(t.getY()-this.getY(), 2))<mRUtil.dwarfRenegade_range){ // distance calculation
				if( !t.isDead())
					return t;
			}
		}
    	return null;
	}

	@Override
	public int moneyDrop() {
		return mRUtil.dwarfRenegade_money;
	}
	
	public void shoot() { //Shoots a turret using its projectile
    	this.getBattlefield().removeProjectile(this.projectile);
    	if(!this.projectile.shoot(firstTowerInRange())) { //If no enemy is found removes the projectile
    		this.getBattlefield().removeProjectile(this.projectile);
    	}
    	else {
        	this.getBattlefield().addProjectile(this.projectile);
    	}
    	this.projectile.resetProjectile();

    }
	
	@Override
	public void action() {
		if(this.isDead()) {
			this.getBattlefield().removeProjectile(this.projectile);
		}
		else {
			this.shoot();
		}
		super.action();
	}
}

/*
 * Sub class Pickaxe that extends Projectile, represents the projectile of DwarfMiner.
 *
 */

package model.projectile;

import mineralsRevenge.mRUtil;

public class Pickaxe extends Projectile{

	public Pickaxe(double x,double y) {
		super(mRUtil.pickaxe_damage, x, y);
	}
	
}

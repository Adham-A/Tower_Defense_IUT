/*
 * Sub Class Potion that extends Projectile, represents the projectile of DwarfScientist.
 * Contains the method to debuff.
 */

package model.projectile;

import mineralsRevenge.mRUtil;
import model.enemy.Diamond;
import model.enemy.Enemy;

public class Potion extends Projectile {

    public Potion(double x, double y) {
        super(mRUtil.potion_damage,x, y);
    }

    // Method that overrides shoot() of Projectile and do debuff().
    @Override
    public boolean shoot(Enemy e) {
        boolean b = super.shoot(e);

        // Against a Diamond, it tanks the first attack before getting debuff.
        if(e!=null && e instanceof Diamond && ((Diamond) e).hasArmor()) {
            debuff((Diamond) e);
        }

        return b;
    }

    // Method that set the attribute Armor of the diamond to false.
    public void debuff(Diamond d) {
        d.setArmor(false);
    }

}

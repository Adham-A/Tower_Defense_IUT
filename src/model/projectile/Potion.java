package model.projectile;

import mineralsRevenge.mRUtil;
import model.enemy.Diamond;
import model.enemy.Enemy;

public class Potion extends Projectile {

    public Potion(double x, double y) {
        super(mRUtil.potion_damage,x, y);
    }
    
    @Override
    public boolean shoot(Enemy e) {
        boolean b = super.shoot(e);

        if(e!=null && e instanceof Diamond && ((Diamond) e).hasArmor()) {
            debuff((Diamond) e);
        }

        return b;
    }

    public void debuff(Diamond d) {
        d.setArmor(false);
    }

}

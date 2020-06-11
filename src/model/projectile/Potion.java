package model.projectile;

import model.enemy.Diamond;
import model.enemy.Enemy;

public class Potion extends Projectile {

    public Potion(int damage, int speed, double x, double y) {
        super(damage, speed, x, y);
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

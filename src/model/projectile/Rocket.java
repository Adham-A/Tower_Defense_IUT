/*
 * Sub class Rocket that extends Projectile, represents the projectile of DwarfScientist.
 * Contains the method to deal damages in an area.
 */

package model.projectile;

import mineralsRevenge.mRUtil;
import model.Edge;
import model.enemy.Enemy;

public class Rocket extends Projectile {

    private int radius;

    public Rocket(double x, double y) {
        super(mRUtil.rocket_damage,x, y);
        this.radius = mRUtil.rocket_radius;
    }

    // Method that overrides shoot() of Projectile and do collateralDamages().
    @Override
    public boolean shoot(Enemy e) {
        boolean b = super.shoot(e); // Only the targeted enemy takes full damage in shoot()

        if(e!=null) {
            collateralDamages(e);
        }
        return b;
    }

    /*
     * Method that does damages to enemies around the targeted enemy.
     * The further the enemy is the less damage they take.
     */
    public void collateralDamages(Enemy e) {
        Edge targetEdge = e.getEdge(); // Edge where is the targeted enemy

        for(Enemy enemy: e.getBattlefield().getEnemyList()) {
            for(int i = targetEdge.getX() - (radius-1); i < targetEdge.getX() + radius; i++) {
                for (int j = targetEdge.getY() - (radius-1); j < targetEdge.getY() + radius; j++) {
                    if(i == targetEdge.getX() || j == targetEdge.getY()) {
                        if (enemy.getX() == i && enemy.getY() == j && enemy != e) {
                            // Damage calculation
                            int damage = super.getDamage() - (Math.abs(targetEdge.getX() - i + targetEdge.getY() - j) + 1) * 3;
                            enemy.removeHp(damage);
                        }
                    }
                }
            }
        }
    }
}

package model.projectile;

import model.Edge;
import model.enemy.Enemy;

public class Rocket extends Projectile {

    private int radius;

    public Rocket(int damage, int speed, double x, double y, int radius) {
        super(damage, speed, x, y);
        this.radius = radius; // deals full damage to the target but collateral damages are reduced.
    }

    @Override
    public boolean shoot(Enemy e) {
        boolean b = super.shoot(e);

        if(e!=null) {
            collateralDamages(e);
        }

        return b;
    }

    public void collateralDamages(Enemy e) {
        Edge targetEdge = e.getEdge(); // Edge where is the targeted enemy

        for(Enemy enemy: e.getBattlefield().getEnemyList()) {
            for(int i = targetEdge.getX() - (radius-1); i < targetEdge.getX() + radius; i++) {
                for (int j = targetEdge.getY() - (radius-1); j < targetEdge.getY() + radius; j++) {
                    if(i == targetEdge.getX() || j == targetEdge.getY()) {
                        if (enemy.getX() == i && enemy.getY() == j && enemy != e) {
                            int damage = super.getDamage() - (Math.abs(targetEdge.getX() - i + targetEdge.getY() - j) + 1) * 3; // the further the enemy is the less damage they take; only the target take full damage
                            enemy.removeHp(damage);
                            System.out.println(damage);
                        }
                    }
                }
            }
        }
    }
}

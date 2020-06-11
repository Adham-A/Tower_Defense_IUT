package model.turret;

import model.Battlefield;
import model.projectile.Projectile;
import model.projectile.Rocket;

public class DwarfSoldier extends TargetedTurret{
    public DwarfSoldier(int x, int y, Battlefield battlefield, double range) {
        super(45, x, y, battlefield, 1, range, new Rocket(12, 8, x, y, 3));
    }
    
    public static int getPrice() {
    	return 30;
    }
}

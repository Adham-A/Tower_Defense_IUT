package model.turret;

import model.Battlefield;
import model.projectile.Potion;

public class DwarfScientist extends TargetedTurret {

    public DwarfScientist(int x, int y, Battlefield battlefield, double range) {
        super(17, x, y, battlefield, 1, range, new Potion(3, 5, x, y));
    }
    
    public static int getPrice() {
    	return 30;
    }

}

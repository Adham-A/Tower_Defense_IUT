package model.turret;

import model.Battlefield;
import model.projectile.Pickaxe;

public class DwarfMiner extends TargetedTurret {
	
	
    public DwarfMiner(int x, int y, Battlefield battlefield,double range) {
        super(20, x, y, battlefield, 1, range, new Pickaxe(10, 15, x, y));
    }
    
    public static int getPrice() {
    	return 10;
    }

}

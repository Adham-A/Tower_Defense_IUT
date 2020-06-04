package model.turret;

import model.Battlefield;
import model.projectile.Pickaxe;

public class DwarfMiner extends TargetedTurret {
	
    public DwarfMiner(int x, int y, Battlefield battlefield,int range) {
        super(20, x, y, battlefield, 1, range, new Pickaxe(5, 15, x, y));
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
    }
}

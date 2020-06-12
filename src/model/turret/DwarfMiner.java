//This class models the basic DwarfMiner
package model.turret;

import mineralsRevenge.mRUtil;
import model.Battlefield;
import model.projectile.Pickaxe;

public class DwarfMiner extends TargetedTurret {
	
    public DwarfMiner(int x, int y, Battlefield battlefield) {
        super(mRUtil.dwarfMiner_hp, x, y, battlefield,mRUtil.dwarfMiner_range, new Pickaxe(x, y));
    }
    
}

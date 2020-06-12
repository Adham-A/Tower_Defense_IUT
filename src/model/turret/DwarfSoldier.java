//This class models the DwarfSoldier who deals damage in a zone
package model.turret;

import mineralsRevenge.mRUtil;
import model.Battlefield;
import model.projectile.Rocket;

public class DwarfSoldier extends TargetedTurret{
	
    public DwarfSoldier(int x, int y, Battlefield battlefield) {
        super(mRUtil.dwarfSoldier_hp,x, y, battlefield,mRUtil.dwarfSoldier_range, new Rocket(x, y));
    }
    
}

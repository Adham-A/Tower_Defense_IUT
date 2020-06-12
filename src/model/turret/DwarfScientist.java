package model.turret;

import mineralsRevenge.mRUtil;
import model.Battlefield;
import model.projectile.Potion;

public class DwarfScientist extends TargetedTurret {

    public DwarfScientist(int x, int y, Battlefield battlefield, double range) {
        super(mRUtil.dwarfScientist_hp, x, y, battlefield,mRUtil.dwarfScientist_range, new Potion(x, y));
    }
    
}

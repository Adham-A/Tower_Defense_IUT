package model.turret;

import mineralsRevenge.mRUtil;
import model.Battlefield;
import model.projectile.Dynamite;

public class DwarfDemolitionist extends TargetedTurret{

	public DwarfDemolitionist(int x, int y, Battlefield battlefield) {
		super(mRUtil.dwarfDemolitionist_hp, x, y, battlefield,mRUtil.dwarfDemolitionist_range, new Dynamite(x,y, battlefield));
	}

}

package model.turret;

import model.Battlefield;
import model.projectile.Projectile;
import model.projectile.Dynamit;

public class DwarfDemolitionist extends TargetedTurret{

	public DwarfDemolitionist(int x, int y, Battlefield battlefield, double range) {
		super(35, x, y, battlefield,1, range, new Dynamit(2,8,x,y,battlefield));
	}
	
	public static int getPrice() {
		return 40; 
	}

}

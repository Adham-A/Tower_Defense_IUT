package model.enemy;

import mineralsRevenge.mRUtil;
import model.Battlefield;

public class Emerald extends Mineral {
	
	boolean notAChild;
// Initial constructor of basic Emerald
    public Emerald(int x, int y, Battlefield battlefield) {
        super(mRUtil.emerald_hp, x, y, battlefield);
        notAChild = true;
    }

// Constructor of children when the parent die
    public Emerald(Emerald em, Battlefield battlefield) {
        super(mRUtil.emerald_hp/2, em.getX(), em.getY(), battlefield);
        notAChild = false;
    }

    public void action() {
    	if(this.isDead() && notAChild) {
			this.split();
        }
    	super.action();
    }

    private void split() { // Split the emerald in two children when it dies
        for(int numberOfChild = 0; numberOfChild < 2; numberOfChild++)
        battlefield.addEnemy(new Emerald(this, this.battlefield));
    }

	@Override
	public int moneyDrop() {
		return mRUtil.emerald_money;
	}
	
	public boolean isAChild() { // Return true if the emerald is a children ( to don't split an infinite time the emeralds )
		return !this.notAChild;
	}



}

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
			this.giveBirth();
        }
    	super.action();
    }

    private void giveBirth() {
        battlefield.addEnemy(new Emerald(this, this.battlefield));
        battlefield.addEnemy(new Emerald(this, this.battlefield));
    }

	@Override
	public int moneyDrop() {
		return mRUtil.emerald_money;
	}
	
	public boolean isAChild() {
		return !this.notAChild;
	}



}

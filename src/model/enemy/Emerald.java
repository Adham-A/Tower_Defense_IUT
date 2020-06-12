package model.enemy;

import model.Battlefield;

public class Emerald extends Mineral {
	
	boolean notAChild;
// Initial constructor of basic Emerald
    public Emerald(int hp,int x, int y, Battlefield battlefield) {
        super(hp, x, y, battlefield);
        notAChild = true;
    }

// Constructor of children when the parent die
    public Emerald(Emerald em, Battlefield battlefield) {
        super(17, em.getX(), em.getY(), battlefield);
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
		return 25;
	}
	
	public boolean isAChild() {
		return !this.notAChild;
	}



}

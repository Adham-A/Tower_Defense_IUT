package model.enemy;

import model.Battlefield;

public class Emerald extends Mineral {
	
	private static int split_counter = 0;
// Initial constructor of basic Emerald
    public Emerald(int x, int y, Battlefield battlefield) {
        super(25, x, y, battlefield);
    }

// Constructor of children when the parent die
    public Emerald(Emerald em, Battlefield battlefield) {
        super(17, em.getX(), em.getY(), battlefield);
    }

    public void action() {
    	super.action();
        if(split_counter<5) {
        	this.removeHp(5);
        }
        else if(split_counter==5) {
            this.giveBirth();
        }
    
        split_counter++;
    }

    private void giveBirth() {
        battlefield.addEnemy(new Emerald(this, this.battlefield));
        battlefield.addEnemy(new Emerald(this, this.battlefield));
    }

}

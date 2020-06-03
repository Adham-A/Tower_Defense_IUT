package model.enemy;

import model.Battlefield;

public class Emerald extends Mineral {

// Initial constructor of basic Emerald
    public Emerald(int x, int y, Battlefield battlefield) {
        super(25, x, y, battlefield);
    }

// Constructor of children when the parent die
    public Emerald(Emerald em, Battlefield battlefield) {
        super(17, em.getX(), em.getY(), battlefield);
    }

    public void action() {
        if(!this.isDead()) {
            this.move();
        }
        else {
            this.giveBirth();
            this.battlefield.removeEnemy(this);
        }
    }

    private void giveBirth() {
        Emerald e1 = new Emerald(this, this.battlefield);
        Emerald e2 = new Emerald(this, this.battlefield);
        battlefield.addEnemy(e1);
        battlefield.addEnemy(e2);
    }

}

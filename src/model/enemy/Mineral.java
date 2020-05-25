package model.enemy;

import model.Battlefield;

public abstract class Mineral extends Enemy {

    public Mineral(int x, int y, Battlefield battlefield) {
        super(0, x, y, battlefield);
    }

}

package model.enemy;

import model.Battlefield;

public abstract class Mineral extends Enemy {

    public Mineral(int hp, int x, int y, Battlefield battlefield) {
        super(hp, x, y, battlefield);
    }

}

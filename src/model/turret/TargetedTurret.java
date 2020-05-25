package model.turret;

public abstract class TargetedTurret extends Turret {

    int atkSpeed;

    public TargetedTurret(int hp, int x, int y, int atkSpeed) {
        super(hp, x, y);
        this.atkSpeed = atkSpeed;
    }

}

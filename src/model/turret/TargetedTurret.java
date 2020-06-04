package model.turret;

import model.Battlefield;
import model.projectile.Projectile;

public abstract class TargetedTurret extends Turret {

    private int atkSpeed;
    private Projectile projectile;
    private double range;

    public TargetedTurret(int hp, int x, int y,Battlefield battlefield ,int atkSpeed,double range,Projectile projetcile) {
        super(hp, x, y, battlefield);
        this.atkSpeed = atkSpeed;
        this.projectile = projetcile;
        this.range = range;
    }
    
    
    

}

package model.projectile;

import model.enemy.Enemy;
import mineralsRevenge.mRUtil;
import model.Battlefield;
import model.Edge;

public class Dynamite extends Projectile {
	
	private Battlefield battlefield;

	public Dynamite(double x, double y,Battlefield battlefield) {
		super(mRUtil.dynamite_damage, x, y);
		this.battlefield = battlefield;
	}
	
	@Override
    public boolean shoot(Enemy e) {
        boolean b = super.shoot(e);

        if(e!=null) {
            moveBack(e);
        }

        return b;
    }
	
	private void moveBack(Enemy e) {
		for ( int i = this.battlefield.getGraph().getArrayListEdges().size()-2 ; i > 0 ; i--) {
			if (this.battlefield.getGraph().getArrayListEdges().get(i).getParent().getParent() == e.getEdge()) {
						e.setX(this.battlefield.getGraph().getArrayListEdges().get(i).getX());
						e.setY(this.battlefield.getGraph().getArrayListEdges().get(i).getY());
						e.setEdge(this.battlefield.getGraph().getArrayListEdges().get(i));
				break;
			}
		}
	}

}

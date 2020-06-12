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
		Edge targetEdge = e.getEdge();
		for ( int i = this.battlefield.getGraph().getArrayListEdges().size()-2 ; i > 0 ; i--) {
			if (this.battlefield.getGraph().getArrayListEdges().get(i).getParent().getParent() == e.getEdge()) {
				for ( int j = 0 ; j < this.battlefield.getEnemyList().size() ; j++) {
					if (this.battlefield.getEnemyList().get(j).getEdge() == targetEdge) {
						this.battlefield.getEnemyList().get(j).setX(this.battlefield.getGraph().getArrayListEdges().get(i).getX());
						this.battlefield.getEnemyList().get(j).setY(this.battlefield.getGraph().getArrayListEdges().get(i).getY());
						this.battlefield.getEnemyList().get(j).setEdge(this.battlefield.getGraph().getArrayListEdges().get(i));
					}
				}
				break;
			}
		}
	}

}

package controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import model.turret.Turret;
import view.BattlefieldView;

public class TurretListListener implements ListChangeListener<Turret> {
	
	private BattlefieldView battlefieldView;
	
	public TurretListListener(BattlefieldView battlefieldView) {
		this.battlefieldView = battlefieldView;
	}
	
	@Override
	public void onChanged(Change<? extends Turret> c) {
		while ( c.next() ) {
			if (c.wasAdded()) {
				Turret turret = c.getAddedSubList().get(c.getAddedSize() - 1);
				battlefieldView.createTurret(turret);
			}
			
			if (c.wasRemoved()) {
				Turret turret = c.getRemoved().get(c.getRemovedSize() - 1);
				String id = turret.getId();
				ImageView imageView = (ImageView) this.battlefieldView.getPane().lookup("#" + id);
				this.battlefieldView.getPane().getChildren().remove(imageView);
			}
		}
	}

}

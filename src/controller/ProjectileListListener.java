package controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import model.projectile.Projectile;
import view.BattlefieldView;

public class ProjectileListListener implements ListChangeListener<Projectile> {
	
	private BattlefieldView battlefieldView;
	
	public ProjectileListListener(BattlefieldView battlefieldView) {
		this.battlefieldView = battlefieldView;
	}

	@Override
	public void onChanged(Change<? extends Projectile> c) {
		while ( c.next() ) {
			if (c.wasAdded()) {
				Projectile projectile = c.getAddedSubList().get(c.getAddedSize() - 1);
				battlefieldView.createProjectile(projectile);
			}
			
			if (c.wasRemoved()) {
				Projectile projectile = c.getRemoved().get(c.getRemovedSize() - 1);
				String id = projectile.getId();
				ImageView imageView = (ImageView) this.battlefieldView.getPane().lookup("#" + id);
				this.battlefieldView.getPane().getChildren().remove(imageView);
			}
		}
	}

}

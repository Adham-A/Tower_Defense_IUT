package controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import model.enemy.Enemy;
import view.BattlefieldView;

public class EnemyListListener implements ListChangeListener<Enemy>{ 

	private BattlefieldView battlefieldView;
	
	public EnemyListListener(BattlefieldView battlefieldView) {
		this.battlefieldView = battlefieldView;
	}
	
	@Override
	public void onChanged(Change<? extends Enemy> c) {
		while (c.next()) {
			if (c.wasAdded()) {
				Enemy enemy = c.getAddedSubList().get(c.getAddedSize() - 1);
				battlefieldView.createEnemy(enemy);
			}
			if (c.wasRemoved()) {
				Enemy enemy = c.getRemoved().get(c.getRemovedSize() - 1);
				String id = enemy.getId();
				ImageView imageView = (ImageView) this.battlefieldView.getPane().lookup("#" + id);
				this.battlefieldView.getPane().getChildren().remove(imageView);
			}
		}
	}
}
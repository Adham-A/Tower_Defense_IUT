package controller;

import javafx.collections.ListChangeListener;
import model.enemy.Enemy;
import view.BattlefieldView;

public class EnemyListListener implements ListChangeListener<Enemy>{ 

	private BattlefieldView battlefieldView;
	
	public EnemyListListener(BattlefieldView battlefieldView) {
		this.battlefieldView = battlefieldView;
	}
	
	@Override
	public void onChanged(Change<? extends Enemy> c) {
		while(c.next()) {
		Enemy enemy = c.getAddedSubList().get(c.getAddedSize()-1);
		battlefieldView.createEnemy(enemy);
		}
	}
	
	
}
package model;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import model.enemy.Enemy;
import view.BattlefieldView;

public class EnemyListListener implements ListChangeListener<Enemy>{ 
	
	@FXML
	private TilePane tilepane;
	private Battlefield battlefield;
	private BattlefieldView battlefieldView;
	
	public EnemyListListener(TilePane tilepane, Battlefield battlefield, BattlefieldView battlefieldView) {
		this.tilepane = tilepane;
		this.battlefield = battlefield;
		this.battlefieldView = battlefieldView;
	}
	
	@Override
	public void onChanged(Change<? extends Enemy> c) {
		Enemy enemy = c.getAddedSubList().get(c.getAddedSize()-1);
		battlefieldView.createEnemy(enemy);
		enemy.getXProperty().addListener((obs,old,nouv)-> ((Group) this.tilepane.getChildren().get(enemy.getY()*this.battlefield.getWidth()+enemy.getX())).getChildren().add(((Group) this.tilepane.getChildren().get(enemy.getY()*this.battlefield.getWidth()+old.intValue()).lookup("#" + enemy.getId()))));
		enemy.getYProperty().addListener((obs,old,nouv)-> ((Group) this.tilepane.getChildren().get(enemy.getY()*this.battlefield.getWidth()+enemy.getX())).getChildren().add(((Group) this.tilepane.getChildren().get(old.intValue()*this.battlefield.getWidth()+enemy.getX()).lookup("#" + enemy.getId()))));
    	
	}
	
	
}
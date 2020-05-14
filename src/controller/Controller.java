package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import model.Battlefield;
import view.BattlefieldView;

public class Controller implements Initializable{	
	@FXML
	private TilePane tilepane;
	private Battlefield battlefield;
    private BattlefieldView battlefieldView;

    public void initialize(URL arg0, ResourceBundle arg1) {
    	battlefield = new Battlefield("battlefields/battlefield1.json");
    	battlefieldView = new BattlefieldView(battlefield, tilepane);
    	battlefieldView.createView();
    }
    
    public void waveLoop() {
    	while(true) {
    		for (int i = 0 ; i < battlefield.getListEnemy().size() ; i++) {
    			battlefield.getListEnemy().get(i).action();
    		}
    		for (int i = 0 ; i < battlefield.getListTurret().size() ; i++) {
    			battlefield.getListTurret().get(i).action();
    		}
    	}
    }

}

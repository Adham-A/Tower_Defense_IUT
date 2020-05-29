package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import model.*;
import model.enemy.*;
import model.turret.*;
import view.BattlefieldView;

public class Controller implements Initializable{	
	@FXML
	private TilePane tilepane;
	@FXML
    private Pane pane;
	private Battlefield battlefield;
    private BattlefieldView battlefieldView;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
    	battlefield = new Battlefield("battlefields/battlefield1.json");
    	battlefieldView = new BattlefieldView(battlefield, tilepane,pane);
    	battlefieldView.createView();
    	
    	battlefield.getEnemyList().addListener(new EnemyListListener(battlefieldView));
    	
    	Quartz q1 = new Quartz(battlefield.getTerrain().getStartCoordinates()[0],battlefield.getTerrain().getStartCoordinates()[1],this.battlefield);
    	DwarfMiner d = new DwarfMiner(7,19);
    	battlefield.addTurret(d);
    	battlefieldView.createTurret(d);
    	battlefield.addEnemy(q1);	
    }
    
    @FXML
    void move_button(ActionEvent event) {
    	waveLoop();
    }
    
    public void waveLoop() {
            Timeline gameLoop = new Timeline();
            gameLoop.setCycleCount(Timeline.INDEFINITE);

            KeyFrame kf = new KeyFrame(
                    Duration.seconds(0.5),
                    (ev ->{
                        int time=0;
                        if(time==1000){
                            gameLoop.stop();
                        }
                        else{
                    	   for (int i = 0 ; i < battlefield.getEnemyList().size() ; i++) {
                    		   battlefield.getEnemyList().get(i).move();
                    	   }
                       }
                       time++;
                    })
           );
           gameLoop.getKeyFrames().add(kf);
           gameLoop.play();
    }

}

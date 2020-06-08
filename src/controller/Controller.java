package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
	@FXML
    private ImageView dwarfImage;
	@FXML
    private VBox boardBox;

	private Battlefield battlefield;
    private BattlefieldView battlefieldView;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
    	battlefield = new Battlefield("battlefields/battlefield1.json");
    	battlefieldView = new BattlefieldView(battlefield, tilepane,pane, boardBox);
    	battlefieldView.createView();
    	
    	battlefield.getEnemyList().addListener(new EnemyListListener(battlefieldView));
    	battlefield.getProjectileList().addListener(new ProjectileListListener(battlefieldView));
    	
       //Emerald e1 =  new Emerald(battlefield.getTerrain().getStartCoordinates()[0],battlefield.getTerrain().getStartCoordinates()[1],this.battlefield);
        Quartz q = new Quartz(battlefield.getTerrain().getStartCoordinates()[0],battlefield.getTerrain().getStartCoordinates()[1],this.battlefield);
    	battlefieldView.createTurretBoard(dwarfImage);
    	battlefield.addEnemy(q);
    	//battlefield.addEnemy(e1);
    	dwarfImage.setId(101 + "");
    }
    
    @FXML
    void move_button(ActionEvent event) {
    	startLoop();
    }
    
    static int time=0;
    public void startLoop() {
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.5),
                (ev ->{
                    if(time==1000){
                        gameLoop.stop();
                    }
                    else{
                        battlefield.turnLoop();
//                        Quartz q = new Quartz(battlefield.getTerrain().getStartCoordinates()[0],battlefield.getTerrain().getStartCoordinates()[1],this.battlefield);
//                        this.battlefield.addEnemy(q);
                    }
                    if(time%3==0) {
//                        Emerald e1 =  new Emerald(battlefield.getTerrain().getStartCoordinates()[0],battlefield.getTerrain().getStartCoordinates()[1],this.battlefield);
//                      battlefield.addEnemy(e1);
                    }
                    time++;
                })

        );
           gameLoop.getKeyFrames().add(kf);
           gameLoop.play();
    }

    @FXML
    void handleDragDetection(MouseEvent event) {
    	Dragboard db = dwarfImage.startDragAndDrop(TransferMode.ANY);

    	ClipboardContent cb = new ClipboardContent();
    	cb.putImage(dwarfImage.getImage());
    	cb.putString("101");

    	db.setContent(cb);
    	event.consume();
    }

    @FXML
    void handleImageDrop(DragEvent event) {
    	int x = ((int)event.getX())/32;
    	int y = ((int)event.getY())/32;
    	if(battlefield.getTerrain().isFree(battlefield.getTerrain().getTerrainTile(x, y))) {
    		if(event.getDragboard().getString() == "101") {
    	    	DwarfMiner d = new DwarfMiner(x,y,this.battlefield, 4);
    	    	battlefield.addTurret(d);
    	    	battlefieldView.createTurret(d);
    	    	}
    	}
    }

    @FXML
    void handleImageOver(DragEvent event) {
    	if (event.getDragboard().hasImage()) {
    		event.acceptTransferModes(TransferMode.ANY);
    	}
    }

}

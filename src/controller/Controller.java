package controller;

import java.net.URL;
import java.util.ResourceBundle;

import exception.TerrainLoaderException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import mineralsRevenge.mRUtil;
import model.*;
import model.turret.*;
import view.BattlefieldView;

public class Controller implements Initializable{
	@FXML
	private TilePane tilepane;
	@FXML
    private Pane pane;
	@FXML
    private ImageView minerImage;
	@FXML
    private ImageView soldierImage;
	@FXML
    private ImageView scientistImage;
	@FXML
    private ImageView demolitionistImage;
	@FXML
    private VBox boardBox;
	@FXML
    private VBox startBox;
	@FXML
    private VBox dwarfBoard;
	@FXML
    private MenuButton levelList;
	@FXML
    private Button start;
	@FXML
    private Label money;
	@FXML
    private Label hp;
	@FXML
    private Label result;
	@FXML
    private MenuItem menuItem1;
	@FXML
    private MenuItem menuItem2;
	@FXML
    private MenuItem menuItem3;
    @FXML
    private MenuItem menuItem4;
    @FXML
    private MenuItem menuItem5;

	private boolean move = true;
	private Battlefield battlefield;
    private BattlefieldView battlefieldView;
    
    private final String idMiner = "101";
    private final String idSoldier = "111";
	private final String idDemolitionist = "121";
    private final String idScientist = "131";

    public void initialize(URL arg0, ResourceBundle arg1) {

    	try {
			battlefield = new Battlefield("battlefields/battlefield1.json");
	    	battlefieldView = new BattlefieldView(battlefield, tilepane,pane, boardBox);
	    	battlefieldView.createView();
	    	tilepane.setVisible(false);
	    	dwarfBoard.setVisible(false);

	    	ImageView accueil = new ImageView();
	    	Image imageAccueil = new Image("view/StartImage.png");
	    	accueil.setImage(imageAccueil);
	    	pane.getChildren().add(0,accueil);
	    	money.textProperty().bind(battlefield.getMoneyProperty().asString());
	    	hp.textProperty().bind(battlefield.getHpProperty().asString());

	    	battlefield.getEnemyList().addListener(new EnemyListListener(battlefieldView));
	    	battlefield.getProjectileList().addListener(new ProjectileListListener(battlefieldView));
	    	battlefield.getTurretList().addListener(new TurretListListener(battlefieldView));
	    	
	    	battlefieldView.createTurretBoard(minerImage, idMiner);
	    	battlefieldView.createTurretBoard(soldierImage, idSoldier);
			battlefieldView.createTurretBoard(demolitionistImage, idDemolitionist);
	    	battlefieldView.createTurretBoard(scientistImage, idScientist);

		} catch (TerrainLoaderException e) {
			System.err.println(e.getMessage());
			Label error = new Label();
			Font font = Font.font(45);
			error.setTextFill(Color.RED);
			error.setFont(font);
			error.setText(e.getMessage());
			this.pane.getChildren().add(error); 
			this.boardBox.setVisible(false); 
		}
    	
    	//levelList.set

    }
    
    @FXML
    void move_button(ActionEvent event) {
    	if(move) {
    		move = false;
    		startLoop();
    	}
    }
    
    static int time=0;
    public void startLoop() {
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.5),
                (ev ->{
                    if(time==battlefield.getWaveManager().getMaxTurns() || this.battlefield.isDead() ){
                        gameLoop.stop();
                        tilepane.setVisible(false);
                        dwarfBoard.setVisible(false);

                        battlefield.clearBattlefield();
                        
                    	if(this.battlefield.getHp() > 0) {
                        	this.result.setText("You Won!");
                        }
                    	else {
                    		this.result.setText("You lost");
                    	}

                    }
                    else{
                    	battlefield.turnLoop();
                    }
                    time++;
                })

        );
           gameLoop.getKeyFrames().add(kf);
           gameLoop.play();
    }

    @FXML
    void handleDragDetection(MouseEvent event) {
    	ClipboardContent cb = new ClipboardContent();
    	ImageView target = null;

    	if (event.getTarget() == minerImage) {
    		target = minerImage;
    	}
    	else if(event.getTarget() == soldierImage) {
    		target = soldierImage;
    	}
		else if(event.getTarget() == demolitionistImage){
			target = demolitionistImage;
		}
    	else {
    		target = scientistImage;
    	}

    	Dragboard db = target.startDragAndDrop(TransferMode.ANY);
    	cb.putImage(target.getImage());
    	cb.putString(target.getId());

    	db.setContent(cb);
    	event.consume();
    }

    @FXML
    void handleImageDrop(DragEvent event) {
    	int x = ((int)event.getX())/32;
    	int y = ((int)event.getY())/32;
    	if(battlefield.getTerrain().isFree(battlefield.getTerrain().getTerrainTile(x, y))) {
    		if(event.getDragboard().getString() == idMiner && this.battlefield.buy(mRUtil.dwarfMiner_price)) {
    	    	Turret d = new DwarfMiner(x,y,this.battlefield);
    	    	battlefield.addTurret(d);
    	    }
    		else if (event.getDragboard().getString() == idSoldier && this.battlefield.buy(mRUtil.dwarfSoldier_price)) {
    			Turret d = new DwarfSoldier(x,y,this.battlefield);
    	    	battlefield.addTurret(d);
    		}
			else if (event.getDragboard().getString() == idDemolitionist && this.battlefield.buy(mRUtil.dwarfDemolitionist_price)){
				Turret d = new DwarfDemolitionist(x,y,this.battlefield);
				battlefield.addTurret(d);
			}
    		else if (event.getDragboard().getString() == idScientist && this.battlefield.buy(mRUtil.dwarfScientist_price)) {
    			Turret d = new DwarfScientist(x,y,this.battlefield);
    	    	battlefield.addTurret(d);
    		}
    		else {
    			System.out.println("non");
    		}
    	}
    }

    @FXML
    void handleImageOver(DragEvent event) {
    	if (event.getDragboard().hasImage()) {
    		event.acceptTransferModes(TransferMode.ANY);
    	}
    }

    @FXML
    void startLevel(ActionEvent event) {
    	tilepane.setVisible(true);
    	dwarfBoard.setVisible(true);
    	startBox.setVisible(false);
    	pane.getChildren().remove(0);
    }
    
    @FXML
    void handleLevel(ActionEvent event) {
    	if ( event.getSource() == menuItem1) {
    		this.levelList.setText(menuItem1.getText());
    	}
    	else if ( event.getSource() == menuItem2) {
    		this.levelList.setText(menuItem2.getText());
    		mRUtil.difficulty = 1;
    	}
    	else if ( event.getSource() == menuItem3) {
    		this.levelList.setText(menuItem3.getText());
    		mRUtil.difficulty = 2;
    	}
    	else if ( event.getSource() == menuItem4) {
    		this.levelList.setText(menuItem4.getText());
    		mRUtil.difficulty = 3;
    	}
    	else if ( event.getSource() == menuItem5) {
    		this.levelList.setText(menuItem5.getText());
    		mRUtil.difficulty = 4;
    	}
    	
    }

}
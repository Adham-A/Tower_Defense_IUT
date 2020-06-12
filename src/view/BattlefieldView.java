//This class setups the view of the battlefield
package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import mineralsRevenge.mRUtil;
import model.Battlefield;
import model.enemy.*;
import model.projectile.*;
import model.turret.*;

public class BattlefieldView {
	
	@FXML
    private Pane pane;
	@FXML
	private TilePane tilepane;
	@FXML
    private VBox boardBox;
	private Timeline timeline;
	private BufferedImage tileset;
	private Battlefield battlefield;
	
	public BattlefieldView(Battlefield battlefield, TilePane tilepane,Pane pane, VBox boardBox) {
		this.battlefield = battlefield;
		this.tilepane = tilepane;
		this.pane = pane;
		this.boardBox = boardBox;
		try {
			this.tileset = ImageIO.read(new File("tileset/tileset.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private BufferedImage cropImage(BufferedImage src, int number) {
		return src.getSubimage(((number-1)%10)*mRUtil.tileSize,(number-1)/10*mRUtil.tileSize, mRUtil.tileSize,mRUtil.tileSize);
	}
	
	public void createView() {
		int width = this.battlefield.getTerrain().getWidth();
		int heigth= this.battlefield.getTerrain().getHeight();

		for (int i = 0; i < width*heigth; i++) { //fills the tilepane with empty image views
			this.tilepane.getChildren().add(new ImageView());
		}

		Map<Integer, Image> hashmap = new HashMap<>();
		for ( int i = 0; i < heigth; i++) {
			for(int j = 0; j < width ; j++) {			
				if(! hashmap.containsKey(battlefield.getTerrain().getTerrainTile(j, i) )) {
					Image src = SwingFXUtils.toFXImage(cropImage(tileset,this.battlefield.getTerrain().getTerrainTile(j, i)),null);
					hashmap.put(battlefield.getTerrain().getTerrainTile(j, i), src);
				}
			}
		}

		for (int k = 0; k < heigth; k++) { //this loop fills every tile with the corresponding image
			for (int l = 0; l < width; l++) {
				((ImageView)tilepane.getChildren().get((k)*width+l)).setImage(hashmap.get(battlefield.getTerrain().getTerrainTile(l, k))); 
			}
		}
		
	}
	
	private static int emerald_flip = 0;
	public void createEnemy(Enemy enemy) {
		 ImageView imageView = new ImageView();
	        imageView.setPreserveRatio(true);
	        int id = 0;
	        if( enemy instanceof Quartz) {
	            id = mRUtil.quartz_id;
	        }
	        else if (enemy instanceof Emerald) {
	            id = mRUtil.emerald_id;
	            if(((Emerald) enemy).isAChild()) {
	            	if(emerald_flip==0) {
	            		emerald_flip = 1;
	            		id = mRUtil.emerald_baby1_id;
	            	}
	            	else {
	            		emerald_flip = 0;
	            		id = mRUtil.emerald_baby2_id;
	            	}
	            }
	        }
	        else if (enemy instanceof Saphir) {
	            id = mRUtil.saphir_id;
	        }
	        else if(enemy instanceof Diamond) {
	            id = mRUtil.diamond_id;
	        }
	 
	        Image image = SwingFXUtils.toFXImage(cropImage(tileset,id),null);
	        imageView.setId(enemy.getId() + "");
	        imageView.setImage(image);
	        this.pane.getChildren().add(imageView);
	       
	        imageView.translateXProperty().set(enemy.getXProperty().multiply(mRUtil.tileSize).getValue());
	        imageView.translateYProperty().set(enemy.getYProperty().multiply(mRUtil.tileSize).getValue());
	 
	         enemy.getXProperty().addListener((obs_value,old_value,new_value)-> { this.timeline = new Timeline(
	                    new KeyFrame(Duration.seconds(0), new KeyValue(imageView.translateXProperty(),(int)old_value*mRUtil.tileSize)),
	                    new KeyFrame(Duration.seconds(0.5), new KeyValue(imageView.translateXProperty(),(int)new_value*mRUtil.tileSize))
	                    );
	                    this.timeline.play();
	          });
	         
	         enemy.getYProperty().addListener((obs_value,old_value,new_value)-> { this.timeline = new Timeline(
	                    new KeyFrame(Duration.seconds(0), new KeyValue(imageView.translateYProperty(),(int)old_value*mRUtil.tileSize)),
	                    new KeyFrame(Duration.seconds(0.5), new KeyValue(imageView.translateYProperty(),(int)new_value*mRUtil.tileSize))
	                    );
	                    this.timeline.play();
	         });
	}

	public void createTurretBoard(ImageView imageView, int id) {
		Image image = SwingFXUtils.toFXImage(cropImage(tileset,id),null);
		imageView.setImage(image);
		imageView.setId(id+"");
		
	}
	
	public void createTurret(Turret turret) {
		int id = 0;

		if(turret instanceof DwarfMiner) {
			id = mRUtil.dwarfMiner_id;
		}
		else if(turret instanceof DwarfSoldier) {
			id = mRUtil.dwarfSoldier_id;
		}
		else if(turret instanceof DwarfDemolitionist) {
			id = mRUtil.dwarfDemolitionist_id;
		}
		else if(turret instanceof DwarfScientist){
			id = mRUtil.dwarfScientist_id;
		}


		Image image = SwingFXUtils.toFXImage(cropImage(tileset,id),null);
		ImageView imageView = new ImageView();
		imageView.setId(turret.getId() + "");
		imageView.setImage(image);
		imageView.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
            	if (t.getButton() == MouseButton.SECONDARY) {
            		for ( int i = 0 ;  i < battlefield.getTurretList().size() ; i++) {
            			if (battlefield.getTurretList().get(i) == turret) {
            				battlefield.removeTurret(battlefield.getTurretList().get(i));
            				break;
            			}
            		}
            	}
            }
        });
		
		this.pane.getChildren().add(imageView);

		imageView.setX(turret.getX()*mRUtil.tileSize);
		imageView.setY(turret.getY()*mRUtil.tileSize);
		
	}
	
	public void createProjectile(Projectile projectile) {
		   int id = 0 ;

		   if(projectile instanceof Pickaxe) {
		   		id = mRUtil.pickaxe_id;
		   }
		   else if(projectile instanceof Rocket) {
		   		id = mRUtil.rocket_id;
		   }
		   else if(projectile instanceof Dynamite) {
				id = mRUtil.dynamite_id;
		   }
		   else if(projectile instanceof Potion){
		   		id = mRUtil.potion_id;
		   }
		   
		   Image image = SwingFXUtils.toFXImage(cropImage(tileset,id),null);
		   ImageView imageView = new ImageView();
		   imageView.setImage(image);
		   imageView.setId(projectile.getId()+"");
		   
		   imageView.translateXProperty().set(projectile.getXProperty().multiply(mRUtil.tileSize).getValue());
		   imageView.translateYProperty().set(projectile.getYProperty().multiply(mRUtil.tileSize).getValue());
		   this.pane.getChildren().add(imageView);
		   
		   projectile.getXProperty().addListener((obs_value,old_value,new_value)-> {
		            this.timeline = new Timeline(
		                new KeyFrame(Duration.seconds(0), new KeyValue(imageView.translateXProperty(),new_value.intValue()*mRUtil.tileSize)),
		                new KeyFrame(Duration.seconds(0.5), new KeyValue(imageView.translateXProperty(),old_value.intValue()*mRUtil.tileSize)),
		                new KeyFrame(Duration.seconds(0), new KeyValue(imageView.rotateProperty(),0)),
		                new KeyFrame(Duration.seconds(0.5), new KeyValue(imageView.rotateProperty(),360))
		                );
		            this.timeline.play();
		      });
		    
		   projectile.getYProperty().addListener((obs_value,old_value,new_value)-> {
		            this.timeline = new Timeline(
		                new KeyFrame(Duration.seconds(0), new KeyValue(imageView.translateYProperty(),new_value.intValue()*mRUtil.tileSize)),
		                new KeyFrame(Duration.seconds(0.5), new KeyValue(imageView.translateYProperty(),old_value.intValue()*mRUtil.tileSize)),
		                new KeyFrame(Duration.seconds(0), new KeyValue(imageView.rotateProperty(),0)),
		                new KeyFrame(Duration.seconds(0.5), new KeyValue(imageView.rotateProperty(),360))
		                );
		             this.timeline.play();
		    });
		   
	}
	
	public Timeline getTimeline() {
		return this.timeline;
	}

    public Pane getPane() {
		return this.pane;
    }
}

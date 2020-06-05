//This class setups the view of the battlefield
package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;



import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Battlefield;
import model.enemy.*;
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
		return src.getSubimage(((number-1)%10)*32,(number-1)/10*32, 32,32);
	}
	
	public void createView() {
		int width = this.battlefield.getTerrain().getWidth();
		int heigth= this.battlefield.getTerrain().getHeight();

		for (int i = 0; i < width*heigth; i++) { //fills the tilepane with empty image views
			this.tilepane.getChildren().add(new ImageView());
		}

		Map<Integer, Image> hashmap = new HashMap<Integer, Image>();
		for ( int i = 0; i < heigth; i++) {
			for(int j = 0; j < width ; j++) {			
				if(! hashmap.containsKey( (Integer) battlefield.getTerrain().getTerrainTile(j, i) )) {
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
	
	public void createEnemy(Enemy enemy) {
		int id = 0;
		if( enemy instanceof Quartz) {
			id = 201;
		}
		else if (enemy instanceof Emerald) {
			id = 211;
		}

		Image image = SwingFXUtils.toFXImage(cropImage(tileset,id),null);
		ImageView imageView = new ImageView();
		imageView.setId(enemy.getId() + "");
		imageView.setImage(image);
		this.pane.getChildren().add(imageView);
		
		imageView.translateXProperty().set(enemy.getXProperty().multiply(32).getValue());
		imageView.translateYProperty().set(enemy.getYProperty().multiply(32).getValue());

		 enemy.getXProperty().addListener((obs_value,old_value,new_value)-> { this.timeline = new Timeline(
	                new KeyFrame(Duration.seconds(0), new KeyValue(imageView.translateXProperty(),(int)old_value*32)),
	                new KeyFrame(Duration.seconds(0.5), new KeyValue(imageView.translateXProperty(),(int)new_value*32))
	                );
		 			this.timeline.play();
	      });
		 
	     enemy.getYProperty().addListener((obs_value,old_value,new_value)-> { this.timeline = new Timeline(
	                new KeyFrame(Duration.seconds(0), new KeyValue(imageView.translateYProperty(),(int)old_value*32)),
	                new KeyFrame(Duration.seconds(0.5), new KeyValue(imageView.translateYProperty(),(int)new_value*32))
	                );
	     			this.timeline.play();
	     });

	}

	public void createTurretBoard(ImageView imageView) {
		int id = 101;

		Image image = SwingFXUtils.toFXImage(cropImage(tileset,id),null);
		imageView.setImage(image);
		
	}
	
	public void createTurret(Turret turret) {
		int id = 0;//666

		if(turret instanceof DwarfMiner) {
			id = 101;
			Image image1 = SwingFXUtils.toFXImage(cropImage(tileset,291),null);
			ImageView imageView2 = new ImageView();
			imageView2.setImage(image1);
			this.pane.getChildren().add(imageView2);
			System.out.println(imageView2.getTranslateX());
			
			imageView2.translateXProperty().set(((DwarfMiner) turret).getProjectile().getXProperty().multiply(32).getValue());
			imageView2.translateYProperty().set(((DwarfMiner) turret).getProjectile().getYProperty().multiply(32).getValue());

			((DwarfMiner) turret).getProjectile().getXProperty().addListener((obs_value,old_value,new_value)-> { this.timeline = new Timeline(
		                new KeyFrame(Duration.seconds(0), new KeyValue(imageView2.translateXProperty(),old_value.intValue()*32)),
		                new KeyFrame(Duration.seconds(1), new KeyValue(imageView2.translateXProperty(),new_value.intValue()*32))
		                );
			 			this.timeline.play();
		      });
			 
			((DwarfMiner) turret).getProjectile().getYProperty().addListener((obs_value,old_value,new_value)-> { this.timeline = new Timeline(
		                new KeyFrame(Duration.seconds(0), new KeyValue(imageView2.translateYProperty(),old_value.intValue()*32)),
		                new KeyFrame(Duration.seconds(1), new KeyValue(imageView2.translateYProperty(),new_value.intValue()*32))
		                );
		     			this.timeline.play();
		    });
		}

		Image image = SwingFXUtils.toFXImage(cropImage(tileset,id),null);
		ImageView imageView = new ImageView();
		imageView.setId(turret.getId() + "");
		imageView.setImage(image);
		this.pane.getChildren().add(imageView);

		imageView.setX(turret.getX()*32);
		imageView.setY(turret.getY()*32);
		
	}
	
	public Timeline getTimeline() {
		return this.timeline;
	}

    public Pane getPane() {
		return this.pane;
    }
}

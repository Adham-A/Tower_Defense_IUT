//This class setups the view of the battlefield
package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import model.Battlefield;
import model.enemy.Enemy;
import model.enemy.Quartz;

public class BattlefieldView {
	
	private Battlefield battlefield;
	private TilePane tilepane;
	private BufferedImage tileset;
	
	public BattlefieldView(Battlefield battlefield, TilePane tilepane) {
		super();
		this.battlefield = battlefield;
		this.tilepane = tilepane;
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
		int width = this.battlefield.getWidth();
		int heigth= this.battlefield.getHeight();
		
		ArrayList<Group> Groups = new ArrayList<Group>();
		for (int i = 0; i < width*heigth; i++) { //fills the tilepane with empty image views
			Groups.add(new Group(new ImageView()));
			this.tilepane.getChildren().add(Groups.get(i));
		}
		
		for (Group group : Groups) {
			group.setBlendMode(BlendMode.MULTIPLY);
		}
		
		Map<Integer, Image> hashmap = new HashMap<Integer, Image>();
		for ( int i = 0; i < heigth; i++) {
			for(int j = 0; j < width ; j++) {			
				if(! hashmap.containsKey( (Integer) battlefield.getBattlefieldTile(j, i) )) {
					Image src = SwingFXUtils.toFXImage(cropImage(tileset,this.battlefield.getBattlefieldTile(j, i)),null);
					hashmap.put(battlefield.getBattlefieldTile(j, i), src);
				}
			}
		}

		for (int k = 0; k < heigth; k++) { //this loop fills every tile with the corresponding image
			for (int l = 0; l < width; l++) {
				((ImageView)Groups.get((k)*width+l).getChildren().get(0) ).setImage(hashmap.get(battlefield.getBattlefieldTile(l, k))); 
			}
		}
	}
	
	public void createEnemy(Enemy enemy) {
		int id = 0;
		if( enemy instanceof Quartz) {
			id = 201;
		}
		Image image = SwingFXUtils.toFXImage(cropImage(tileset,id),null);
		Group g = ((Group) tilepane.getChildren().get(battlefield.getWidth()*battlefield.getStartCoordinates()[1]+battlefield.getStartCoordinates()[0]));
		ImageView imageView = new ImageView();
		imageView.setId(enemy.getId() + "");
		imageView.setImage(image);
		g.getChildren().add(imageView);
	}
	
}

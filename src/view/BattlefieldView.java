//This class setups the view of the battlefield
package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import model.Battlefield;

public class BattlefieldView {
	
	private Battlefield battlefield;
	private TilePane tilepane;
	
	public BattlefieldView(Battlefield battlefield, TilePane tilepane) {
		super();
		this.battlefield = battlefield;
		this.tilepane = tilepane;
	}
	
	public void test() {
		Image quartz = null;
		try {
			quartz = new Image(new FileInputStream("tileset/quartz_1.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView image = new ImageView(quartz);
		int[] startCoordinates = battlefield.getStartCoordinates();
		Group g1 = (Group) tilepane.getChildren().get(startCoordinates[1]*battlefield.getWidth()+startCoordinates[0]);
		g1.getChildren().add(image);
		
	}

	public void moveTest() {
		for ( int i = 0 ; i < tilepane.getChildren().size() ; i++) {
			if ( ((Group)tilepane.getChildren().get(i)).getChildren().size()>1) {
				((Group)tilepane.getChildren().get(i-15)).getChildren().add(((Group)tilepane.getChildren().get(i)).getChildren().get(1));
			}
		}
	}
	
	private BufferedImage cropImage(BufferedImage src, int number) {
		return src.getSubimage(((number-1)%10)*16,(number-1)/10*16, 16,16);
	}
	
	public void createView() {
		BufferedImage tileset = null;
		int width = this.battlefield.getWidth();
		int heigth= this.battlefield.getHeight();
		
		try {
			tileset = ImageIO.read(new File("tileset/tileset.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<Group> Groups = new ArrayList<Group>();
		for (int i = 0; i < width*heigth; i++) { //fills the tilepane with empty image views
			Groups.add(new Group(new ImageView()));
			tilepane.getChildren().add(Groups.get(i));
		}
		
		for (Group group : Groups) {
			group.setBlendMode(BlendMode.MULTIPLY);
		}
		
		for ( int i = 0; i < heigth; i++) {
			for(int j = 0; j < width ; j++) {			
				if( ( (ImageView) Groups.get((i)*width+j).getChildren().get(0)).getImage() ==null) {
					Image src = SwingFXUtils.toFXImage(cropImage(tileset,this.battlefield.getBattlefieldTile(j, i)),null);					
					for (int k = 0; k < heigth; k++) { //this loop fills every tile with the corresponding image
						for (int l = 0; l < width; l++) {
							if(battlefield.getBattlefieldTile(j, i) == battlefield.getBattlefieldTile(l, k)) {
								((ImageView)Groups.get((k)*width+l).getChildren().get(0) ).setImage(src); 
							}
						}
					}
					
				}
			}
		}
	}
	
}

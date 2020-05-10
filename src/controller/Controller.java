package controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import model.Battlefield;

public class Controller implements Initializable{
	
	@FXML
    private TilePane pane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Battlefield battlefield = new Battlefield("battlefields/battlefield1.json");
		int[][] battlefieldArray = battlefield.getBattlefield();
		try {
			createView(battlefieldArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private BufferedImage cropImage(BufferedImage src, int number) {
	      BufferedImage dest = src.getSubimage(number%160,number/10*16, 16,16);
	      BufferedImage copyOfImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
	      Graphics g = copyOfImage.createGraphics();
	      g.drawImage(src, 0, 0, null);
	      return dest; 
	}
	
	//private WritableImage cropImage()
	
	void createView(int[][] array) throws IOException {
		for ( int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length ; j++) {
				ImageView image = new ImageView();
				Image src = SwingFXUtils.toFXImage(cropImage(ImageIO.read(new File("tileset/tileset.png")),array[i][j]), null );
				image.setImage(src);
				this.pane.getChildren().add(image);
			}
		}
	}
}

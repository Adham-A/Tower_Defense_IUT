//This class setups the view of the battlefield
package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
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
		
		ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
		for (int i = 0; i < width*heigth; i++) { //fills the tilepane with empty image views
			imageViews.add(new ImageView());
			tilepane.getChildren().add(imageViews.get(i));
		}
		
		for ( int i = 0; i < heigth; i++) {
			for(int j = 0; j < width ; j++) {			
				if(imageViews.get((i)*width+j).getImage()==null) {
					Image src = SwingFXUtils.toFXImage(cropImage(tileset,this.battlefield.getBattlefieldTile(j, i)),null);					
					for (int k = 0; k < heigth; k++) { //this loop fills every tile with the corresponding image
						for (int l = 0; l < width; l++) {
							if(battlefield.getBattlefieldTile(j, i) == battlefield.getBattlefieldTile(l, k)) {
								imageViews.get((k)*width+l).setImage(src); 
							}
						}
					}
					
				}
			}
		}
	}
	
}

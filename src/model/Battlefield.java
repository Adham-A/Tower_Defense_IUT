package model;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Battlefield {
	
	private int width;
	private int height;
	private int[][] battlefield;
	
	public Battlefield(String path) {
		parseTerrainFromFile(path);
	}
	
	public void parseTerrainFromFile(String path) {
		Object obj = null;
		try {
			obj = new JSONParser().parse(new FileReader(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jo = (JSONObject) obj;
		this.height = ((Long) jo.get("height")).intValue();
		this.width = ((Long) jo.get("width")).intValue();
		
		this.battlefield = new int [this.width][this.height];

		JSONArray jsonArray = (JSONArray) jo.get("data");
		Iterator<?> iterator = jsonArray.iterator();

		for (int line = 0; line < this.height; line++) {
			for (int column = 0; column < this.width; column++) {
				if(iterator.hasNext()) {
					battlefield[column][line] = ( (Long) iterator.next()).intValue();
				}
			}
		}
		
	}
	
	/*private BufferedImage cropImage(BufferedImage src, int number) {
	      BufferedImage dest = src.getSubimage(0, 0, (number-((number%15)*15))*16,(number%15)*16);
	      BufferedImage copyOfImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
	      Graphics g = copyOfImage.createGraphics();
	      g.drawImage(src, 0, 0, null);
	      return dest; 
	}*/
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[][] getBattlefield() {
		return battlefield;
	}

}

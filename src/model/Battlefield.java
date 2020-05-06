package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Battlefield {
	
	private int width;
	private int height;
	private int[][] battlefield;
	
	public Battlefield(String path) throws FileNotFoundException, IOException, ParseException {
		parseTerrainFromFile(path);
	}
	
	public void parseTerrainFromFile(String path) throws FileNotFoundException, IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader(path)); 
		JSONObject jo = (JSONObject) obj;
		this.height = ((Long) jo.get("height")).intValue();
		this.width = ((Long) jo.get("width")).intValue();
		
		int[][] battlefield = new int [this.height][this.width];

		JSONArray jsonArray = (JSONArray) jo.get("data");
		Iterator<?> iterator = jsonArray.iterator();

		for (int line = 0; line < this.width; line++) {
			for (int column = 0; column < this.height; column++) {
				if(iterator.hasNext()) {
					battlefield[column][line] = ( (Long) iterator.next()).intValue();
				}
			}
		}
		this.battlefield = battlefield;
	}

}

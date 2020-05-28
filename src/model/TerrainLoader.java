// This class loads the JSON and provide getters on width,height and data.
package model;

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TerrainLoader {
	
	private JSONObject jo;
	
	public TerrainLoader(String path) {
		Object obj = null;
		try {
			obj = new JSONParser().parse(new FileReader(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.jo = (JSONObject) obj;
	}
	
	public int parseBattlefieldWidth() {
		return ((Long) this.jo.get("width")).intValue();
	}
	
	public int parseBattlefieldHeight() {
		return ((Long) this.jo.get("height")).intValue();
	}
	
	public int[][] parseBattlefieldFromFile() {

		int height = parseBattlefieldHeight();
		int width = parseBattlefieldWidth();
		
		int[][] battlefield = new int [width][height];

		JSONArray jsonArray = (JSONArray) this.jo.get("data");
		Iterator<?> iterator = jsonArray.iterator();

		for (int line = 0; line < height; line++) {
			for (int column = 0; column < width; column++) {
				if(iterator.hasNext()) {
					battlefield[column][line] = ( (Long) iterator.next()).intValue();
				}
			}
		}
		return battlefield;
	}
	
}

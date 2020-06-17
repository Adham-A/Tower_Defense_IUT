// This class loads the JSON and provide getters on width,height and data.
package model;

import java.io.InputStream;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import exception.TerrainLoaderException;

public class TerrainLoader {
	
	private JSONObject jo;
	
	public TerrainLoader(String path) {
		Object obj = null;
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
			int read;
			String json = "";
			while ((read = is.read()) != -1)
				json += (char) read;
			
			obj = new JSONParser().parse(json); //Creates the JSON object
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.jo = (JSONObject) obj;
	}
	
	public int parseBattlefieldWidth() {
		return ((Long) this.jo.get("width")).intValue(); //Returns width of the battlefield
	}
	
	public int parseBattlefieldHeight() {
		return ((Long) this.jo.get("height")).intValue(); //Returns height of the battlefield
	}
	
	public int[][] parseBattlefieldFromFile() throws TerrainLoaderException { //Creates the whole battlefield
		int[][] battlefield ;
		
		try {
			int height = parseBattlefieldHeight();
			int width = parseBattlefieldWidth();
			
			battlefield = new int [width][height];
	
			JSONArray jsonArray = (JSONArray) this.jo.get("data"); //Creates an array with every tile id
			Iterator<?> iterator = jsonArray.iterator();
			
			/*
			 * //Fills battlefield array with corresponding tileset ids
			 */
			
			for (int line = 0; line < height; line++) {
				for (int column = 0; column < width; column++) {
					if(iterator.hasNext()) {
						battlefield[column][line] = ( (Long) iterator.next()).intValue();
					}
				}
			}
			
		}
		catch(Exception e) {
			throw new TerrainLoaderException("Loading of Terrain failed"); //Throws an exception if loading failed
		}

		return battlefield;	
	}
	
}

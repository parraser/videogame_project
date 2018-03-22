package gameResources;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import projectileTypes.AmmoBox;

public class MapReader {
	private MapMaker mapMaker;
	public final static int WALL_DIM = 40;
	public MapReader(MapMaker mapMaker){
		this.mapMaker = mapMaker;
	}
	
	/**
	 * Reads all the map files from the directory and picks one at random.
	 * @param string path to Directory to read from
	 */
	public void readDirectoryRandom(String dir) {
		// TODO Auto-generated method stub
		List<File> maps = new ArrayList<>();
		Pattern p = Pattern.compile(".*\\.map");
		Matcher m;
		
		try{
			File directory = new File(dir);
			if(!directory.isDirectory()){
				System.out.println("Not A Directory");
				return;
			}
			File[] files = directory.listFiles();
			for(File f : files){
				if(f.isFile()){
					m = p.matcher(f.getName());
					if(m.matches()){
						maps.add(f);
					}
				}
			}
			Random r = new Random();
			int randomMap = r.nextInt(maps.size());
			this.readFile(maps.get(randomMap).getAbsolutePath());
		}catch(Exception ex){
			System.out.println("Directory may not exist");
			return;
		}
		
	}
	/**
	 * Interprets characters given and returns the appropriate game object.
	 * @param y Position in the y axis
	 * @param x Position in the x axis
	 * @param c Character to interpret
	 * @return The object interpreted
	 */
	private GameObject readCharBlock(int x, int y, char c){
		switch(c){
			case 'X':
				return new Wall(x, y, WALL_DIM, WALL_DIM);
			case ' ': // Empty space dont add anything
				return null;
			case '1':
				return mapMaker.createPlayerOne(x, y);
			case '2':
				return mapMaker.createPlayerTwo(x, y);
			case 'B':
				return new AmmoBox(x, y);
			default:
				System.out.println("CANNOT READ: "+c);
				return null;
				
		}
	}
	
	/**
	 * Reads a map file and creates the map.
	 * @param string File path
	 */
	public void readFile(String path){
		int offsetX = 0;
		int offsetY = 0;
		
		try{
			File f = new File(path);
			BufferedReader in = new BufferedReader(new FileReader(f));
			GameObject obj;
			
			String line;
		    while ((line = in.readLine()) != null) {
		    	for(char c : line.toCharArray()){
		    		obj = this.readCharBlock(offsetX, offsetY, c);
		    		
		    		if(obj != null){
		    			this.mapMaker.addObj(obj);
		    		}
		    		offsetX += WALL_DIM;
		    	}
		    	offsetX = 0;
		        offsetY += WALL_DIM;
		    }
		    in.close();
		}catch(IOException ex){
			System.out.println("Cannot read from file!");
		}
	}

}

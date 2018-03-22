package gameResources;

import java.awt.Color;
import java.awt.event.KeyEvent;

import projectileTypes.AmmoBox;

public class MapMaker {
	//private Game game;
	private GameObjectHandlerView gohv;
	public static final int P_SIZE = 20;
	public MapMaker(GameObjectHandlerView game){
		this.gohv = game;
	}
	
	/**
	 * Adds the object to the game
	 * @param obj GameObject to add
	 */
	public void addObj(GameObject obj){
		if(obj instanceof Player) {
			this.gohv.addPlayer((Player) obj);
		}else if(obj instanceof Wall) {
			this.gohv.addWall((Wall) obj);
		}else if(obj instanceof AmmoBox) {
			this.gohv.addAmmoBox((AmmoBox)obj);
		}else{
			this.gohv.addObject(obj);
		}
	}
	
	public Player createPlayerOne(int x, int y){
		PlayerBuilder pBuild = new PlayerBuilder();
		pBuild.setColor(Color.PINK);
		pBuild.setUp(KeyEvent.VK_W);
		pBuild.setRight(KeyEvent.VK_D);
		pBuild.setDown(KeyEvent.VK_S);
		pBuild.setLeft(KeyEvent.VK_A);
		pBuild.setShoot(KeyEvent.VK_SPACE);
		pBuild.setGohv(this.gohv);
		pBuild.setHeight(P_SIZE);
		pBuild.setWidth(P_SIZE);
		pBuild.setName("neo");
		Player p = pBuild.get();
		p.setX(x+(MapReader.WALL_DIM/2)-(P_SIZE/2));
		p.setY(y+(MapReader.WALL_DIM/2)-(P_SIZE/2));
		return p;
		
	}
	public  Player createPlayerTwo(int x, int y){
		PlayerBuilder pBuild = new PlayerBuilder();
		pBuild.setColor(Color.ORANGE);
		pBuild.setUp(KeyEvent.VK_UP);
		pBuild.setRight(KeyEvent.VK_RIGHT);
		pBuild.setDown(KeyEvent.VK_DOWN);
		pBuild.setLeft(KeyEvent.VK_LEFT);
		pBuild.setShoot(KeyEvent.VK_ENTER);
		pBuild.setGohv(this.gohv);
		pBuild.setHeight(P_SIZE);
		pBuild.setWidth(P_SIZE);
		pBuild.setName("wot");
		Player p = pBuild.get();
		p.setX(x+(MapReader.WALL_DIM/2)-(P_SIZE/2));
		p.setY(y+(MapReader.WALL_DIM/2)-(P_SIZE/2));
		return p;
	}

}

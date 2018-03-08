import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameObjectHandlerView {
	private List<GameObject> gameObjects;
	private List<Trail> trails;
	private KeyHandler keyHand;
	private List<GameObject> walls;
	private List<Player> playerList;

	
	public GameObjectHandlerView() {
		gameObjects = new ArrayList<GameObject>();
		trails = new LinkedList<Trail>();
		keyHand = new KeyHandler();
		walls = new ArrayList<GameObject>();
		playerList = new ArrayList<Player>();
	}
	/*
	 * What to do after every game tick
	 * - player movement?
	 * - position update?
	 * - game mechanics update?
	 */
	public void tickAll(){
		for (GameObject go : this.gameObjects){
			go.tick();
		}
		for (Player go : this.playerList) {
			go.tick();
		}
		for (GameObject go : this.walls){
			go.tick();
		}
		Trail t;
		for(int i = 0; i < this.trails.size(); i++){
			t = this.trails.get(i);
			if(t.isDead()){
				this.trails.remove(i);
			}else{
				t.tick();
			}
		}
	}
	
	/*
	 * Manages the buffer and draws the next available one
	 */
	public void renderAll(Graphics g){
		
		
		/*Draw GUI first here */
		
		// Tell all game objects here to render themselves
		for (GameObject go : this.gameObjects){
			go.render(g);
		}
		for (Player go : this.playerList) {
			go.render(g);
		}
		for(GameObject go : this.trails){
			go.render(g);
		}
		for(GameObject go : this.walls){
			go.render(g);
		}
		
	}
	
	public void addPlayer(Player p){
		keyHand.addObserver(p);
		this.playerList.add(p);
	}
	
	public void addObject(GameObject obj){
		this.gameObjects.add(obj);
	}
	public void addTrail(Trail trail){
		this.trails.add(trail);
	}
	public KeyHandler getKeyHandler() {
		return this.keyHand;
	}

	public void addWall(GameObject obj){
		this.walls.add(obj);
	}
	
	public List<GameObject> getWalls() {
		return walls;
	}
	
	public List<Player> getPlayers(){
		return playerList;
	}
}
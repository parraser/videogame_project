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
	private List<ProjectileObject> projectiles;
	private KeyHandler keyHand;

	public GameObjectHandlerView() {
		gameObjects = new ArrayList<GameObject>();
		trails = new LinkedList<Trail>();
		keyHand = new KeyHandler();
		this.projectiles = new LinkedList<ProjectileObject>();
	}
	/*
	 * What to do after every game tick
	 * - player movement?
	 * - position update?
	 * - game mechanics update?
	 */
	public void tickAll(){
		gameObjects.addAll(this.projectiles);
		this.projectiles.clear();
		for (GameObject go : this.gameObjects){
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
		// Removing Projectiles off screen
		for (int i = gameObjects.size() - 1; i >= 0; i--) {
			if (gameObjects.get(i) instanceof ProjectileObject) {
				ProjectileObject p = (ProjectileObject)gameObjects.get(i);
				if (p.getX() < 0 - p.getWidth() || p.getX() > Game.WIDTH + p.getWidth() ||
						p.getY() < 0 - p.getHeight() || p.getY() > Game.HEIGHT + p.getHeight()) {
					this.gameObjects.remove(p);
				}
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
		for(GameObject go : this.trails){
			go.render(g);
		}
		
	}
	
	public void addPlayer(Player p){
		keyHand.addObserver(p);
		this.gameObjects.add(p);
	}
	
	public void addObject(GameObject obj){
		this.gameObjects.add(obj);
	}
	
	public void addProjectile(ProjectileObject obj){
		this.projectiles.add(obj);
	}
	
	public void addTrail(Trail trail){
		this.trails.add(trail);
	}
	public KeyHandler getKeyHandler() {
		return this.keyHand;
	}

}

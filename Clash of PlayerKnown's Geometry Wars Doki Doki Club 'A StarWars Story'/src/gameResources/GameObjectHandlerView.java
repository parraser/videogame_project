package gameResources;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import projectileTypes.AmmoBox;

public class GameObjectHandlerView {
	private List<GameObject> gameObjects;
	private List<Trail> trails;
	private List<ProjectileObject> projectileBuffer; // Add new projectiles here (to prevent concurrency issues)
	private List<ProjectileObject> projectiles; // projectiles tick and render here
	private KeyHandler keyHand;
	private List<GameObject> walls;
	private List<Player> playerList;
	private List<AmmoBox> ammoBoxes;
	
	public GameObjectHandlerView() {
		gameObjects = new ArrayList<GameObject>();
		trails = new LinkedList<Trail>();
		this.projectileBuffer = new LinkedList<ProjectileObject>();
		this.projectiles = new LinkedList<ProjectileObject>();
		keyHand = new KeyHandler();
		walls = new ArrayList<GameObject>();
		playerList = new ArrayList<Player>();
		ammoBoxes = new ArrayList<AmmoBox>();
	}
	/*
	 * What to do after every game tick
	 * - player movement?
	 * - position update?
	 * - game mechanics update?
	 */
	public void tickAll(){
		this.projectiles.addAll(this.projectileBuffer);
		this.projectileBuffer.clear();
		// Removing Projectiles off screen (and dead projectiles for now)
		for (int i = this.projectiles.size() - 1; i >= 0; i--) {
			ProjectileObject p = this.projectiles.get(i);
			if (p.isDead()) {
				this.projectiles.remove(p);
			}
		}
		for (GameObject go : this.gameObjects){
			go.tick();
		}
		for (ProjectileObject go : this.projectiles){
			go.tick();
		}
		for (Player go : this.playerList) {
			go.tick();
		}
		for (GameObject go : this.walls){
			go.tick();
		}
		for(AmmoBox go : this.ammoBoxes) {
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
		for (ProjectileObject go : this.projectiles){
			go.render(g);
		}
		for(AmmoBox go : this.ammoBoxes) {
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
	
	public void addProjectile(ProjectileObject obj){
		this.projectileBuffer.add(obj);
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
	
	public void clear() {
		gameObjects.clear();
		trails.clear();
		this.projectileBuffer.clear();
		this.projectiles.clear();
		//keyHand.clear();
		walls.clear();
		playerList.clear();
	}
	
	public void removekeyObservers() {
		keyHand.remObservers();
	}
	
	public List<AmmoBox> getAmmoBoxes() {
		return ammoBoxes;
	}
	public void addAmmoBox(AmmoBox obj) {
		ammoBoxes.add(obj);
		
	}
}

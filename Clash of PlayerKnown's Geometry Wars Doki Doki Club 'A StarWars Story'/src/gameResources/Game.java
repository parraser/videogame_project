package gameResources;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import java.util.Set;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 800, HEIGHT = 625;
	public static final int NANOPERSEC = 1000000000;
	private boolean running = false;
	private Thread thread;
	//private List<GameObject> gameObjects;
	//private List<Trail> trails;
	private Player playerOne;
	private MapMaker mapMaker;
	private MapReader mapReader;
	private MainMenu mainMenu;
	private EndScreen endscreen;
	private Options options;
	public enum State {
		GAME, MAIN_MENU, OPTIONS, END
	}
	protected static State state; // TODO temp protected static, change back to private if keeping
	private GameObjectHandlerView gohv;
	
	/*
	 * Game initialization, what to do when the game first starts
	 */
	public Game(){
		gohv = new GameObjectHandlerView();
		//gameObjects = new ArrayList<GameObject>();
		//trails = new LinkedList<Trail>();
		
		KeyHandler keyHand = new KeyHandler();
		this.addKeyListener(keyHand);
		this.addKeyListener(gohv.getKeyHandler());
		//mapMaker = new MapMaker(this);
		mapMaker = new MapMaker(gohv);
		mapReader = new MapReader(mapMaker);
		mapReader.readDirectoryRandom("Maps");
		this.mainMenu = new MainMenu(this);
		keyHand.addObserver(mainMenu);
		this.state = State.MAIN_MENU;
		this.endscreen = new EndScreen(this);
		keyHand.addObserver(endscreen);
		this.options = new Options(this);
		keyHand.addObserver(options);
		
		//Create a new window to place our game objects
		new Window(WIDTH, HEIGHT, "Clash of PlayerKnown's Geometery Wars Doki Doki Club 'A StarWars Story'", this);
		
		this.start();
		
	}
	
	public synchronized void start(){
		//creates a new thread and once it starts, call the run() method when allowed
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			//wait for thread die
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void run(){
		this.requestFocus();
		//measure the time in nano seconds
		long lastTime = System.nanoTime();
		long amountOfTicks = 60;
		
		// amount of nano seconds to count before we do a game tick
		long tickPerSecond = NANOPERSEC / amountOfTicks;
		long delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running){
			//System.out.println("" + this.gameObjects.get(0).getX() + " " + this.gameObjects.get(0).getY());
			long now = System.nanoTime();
			// determine the amount of seconds has elapsed
			delta += now - lastTime;
			lastTime = now;
			
			// when we are due for a tick and render
			while (delta >= tickPerSecond){
				this.gohv.tickAll();
				for (Player p: this.gohv.getPlayers()) {
					if (p.getHealth() <= 0) {
						for (Player p2: this.gohv.getPlayers()){
							if (p2 != p)
								p2.increaseScore();
						}
						//this.gohv.removekeyObservers();
						List<Player> al = this.gohv.getPlayers();
						this.setGohv(new GameObjectHandlerView());
						KeyHandler keyHand = new KeyHandler();
						this.addKeyListener(keyHand);
						this.addKeyListener(gohv.getKeyHandler());
						this.setMapMaker(new MapMaker(this.getGohv()));
				        this.setMapReader(new MapReader(this.getMapMaker()));
				        this.getMapReader().readDirectoryRandom("Maps");
				        for (int i = 0; i < al.size(); i++){
							System.out.println(i);
							this.gohv.getPlayers().get(i).setScore(al.get(i).getScore());
						}
						state = State.END;
					}
				}
				render();
				
				// if a second has elapsed update the FPS
				frames++;
				if (System.currentTimeMillis() - timer > 1000){
					timer += 1000;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
				
				delta = 0;
			}
		}
		stop();
	}

	public State getState() {
		return state;
	}

	/*
	 * Manages the buffer and draws the next available one
	 */
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		if (state == State.MAIN_MENU) { // should prly encapsulate in mainMenu
			// render main menu here
			mainMenu.render(g);
		} else if (state == State.GAME) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			gohv.renderAll(g);
		} else if (state == State.END) {
			endscreen.render(g);
		} else if(state == State.OPTIONS){
			options.render(g);
		}

		
		/*Draw GUI first here */
		
		g.dispose();
		bs.show();
	}
	
	public MapMaker getMapMaker() {
		return mapMaker;
	}

	public void setMapMaker(MapMaker mapMaker) {
		this.mapMaker = mapMaker;
	}

	public MapReader getMapReader() {
		return mapReader;
	}

	public void setMapReader(MapReader mapReader) {
		this.mapReader = mapReader;
	}

	public GameObjectHandlerView getGohv() {
		return gohv;
	}

	public void setGohv(GameObjectHandlerView gohv) {
		this.gohv = gohv;
	}

	public void setState(State s) {
		this.state = s;
	}

	public static void main(String args[]){
		new Game();
	}

}

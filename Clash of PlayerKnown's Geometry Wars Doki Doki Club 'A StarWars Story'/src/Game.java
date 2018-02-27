
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 800, HEIGHT = 625;
	public static final int NANOPERSEC = 1000000000;
	private boolean running = false;
	private Thread thread;
	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private Player playerOne;
	private MapMaker mapMaker;
	private MapReader mapReader;
	private enum State {
		GAME, MAIN_MENU
	}
	private State state = State.MAIN_MENU;
	
	/*
	 * Game initialization, what to do when the game first starts
	 */
	public Game(){
		KeyHandler keyHand = new KeyHandler();
		this.addKeyListener(keyHand);
		
		mapMaker = new MapMaker(this);
		mapReader = new MapReader(mapMaker);
		mapReader.readDirectoryRandom("Maps");
		
		playerOne = new Player("Player1");
		keyHand.addObserver(playerOne);
		gameObjects.add(playerOne);
		
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
				tick();
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
	
	/*
	 * What to do after every game tick
	 * - player movement?
	 * - position update?
	 * - game mechanics update?
	 */
	private void tick(){
		if (state == State.GAME) {
			for (GameObject go : this.gameObjects) {
				go.tick();
			}
		}
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
		
		if (state == State.MAIN_MENU) {
			// render main menu here
		} else if (state == State.GAME) {
			g.setColor(Color.PINK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			// Tell all game objects here to render themselves
			for (GameObject go : this.gameObjects){
				go.render(g);
			}
		}
		
		g.dispose();
		bs.show();
	}

	public void addObject(GameObject obj){
		this.gameObjects.add(obj);
	}
	
	public static void main(String args[]){
		new Game();
	}

}

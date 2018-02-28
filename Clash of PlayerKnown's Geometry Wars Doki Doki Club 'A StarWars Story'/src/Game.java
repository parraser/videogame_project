
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
	private List<GameObject> gameObjects;
	private List<Trail> trails;
	private Player playerOne;
	private MapMaker mapMaker;
	private MapReader mapReader;
	private MainMenu menu = new MainMenu();
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

	private BufferedImage background = null;
	
	
	private enum State {
		GAME, MAIN_MENU
	}
	private State state = State.MAIN_MENU;
	
	/*
	 * Game initialization, what to do when the game first starts
	 */
	public Game(){
		gameObjects = new ArrayList<GameObject>();
		trails = new LinkedList<Trail>();
		
		KeyHandler keyHand = new KeyHandler();
		this.addKeyListener(keyHand);
		
		mapMaker = new MapMaker(this);
		mapReader = new MapReader(mapMaker);
		mapReader.readDirectoryRandom("Maps");
		
		/* TEMPORARY */
		playerOne = new Player("Player1", this);
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
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		try {
		    background = ImageIO.read(getClass().getResource("space.jpg"));
		    
		} catch (IOException e) {
			
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image,0,0, WIDTH,HEIGHT, this);
		g.drawImage(background,0,0, this);
		if (state == State.MAIN_MENU) {
			// render main menu here
			menu.render(g);
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
	public void addTrail(Trail trail){
		this.trails.add(trail);
	}
	public static void main(String args[]){
		new Game();
	}

}

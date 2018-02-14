
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 1080, HEIGHT = 720;
	public static final int NANOPERSEC = 1000000000;
	private boolean running = false;
	private Thread thread;
	
	
	/*
	 * Game initialization, what to do when the game first starts
	 */
	public Game(){
		KeyHandler keyHand = new KeyHandler();
		this.addKeyListener(keyHand);
		
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
			long now = System.nanoTime();
			
			// determine the amount of seconds has elapsed
			delta += (now - lastTime)/tickPerSecond;
			lastTime = now;
			
			// when we are due for a tick
			while (delta >= 1){
				tick();
				delta--;
			}
			// render game objects
			if (running)
				render();
			
			// if a second has elapsed update the FPS
			frames++;
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
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
		
		g.setColor(Color.PINK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Tell all game objects here to render themselves
		
		g.dispose();
		bs.show();
	}

	
	
	public static void main(String args[]){
		new Game();
	}

}

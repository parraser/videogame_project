import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/* The player class object */
public class Player extends MovableObject implements Observer{
	
	final static int WALK = 8;
	
	String name;
	Game game;
	
	public Player(String name, Game game){
		this.name = name;
		this.x = 0;
		this.y = 0;
		this.width = 20;
		this.height = 20;
		this.game = game;
		this.game.addObject(this);
	}
	
	
	public void collision() {
		
	}

	@Override
	public void update(Observable o, Object e) {
		// TODO Auto-generated method stub
		
		int key = ((KeyEvent) e).getKeyCode();
		int keyAction = ((KeyEvent) e).getID();

		//since actions are recorded once, releasing the key
		//shall input an equal negative velocity
		// could open a bug where one key is not recorded 
		// it will have twice the speed
		if(key == KeyEvent.VK_W) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				this.setVelY(-WALK);
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				this.setVelY(0);
			}
			
		}else if(key == KeyEvent.VK_S) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				this.setVelY(WALK);
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				this.setVelY(0);
			}
		}
		
		if(key == KeyEvent.VK_A) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				this.setVelX(-WALK);
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				this.setVelX(0);
			}
			
		}else if(key == KeyEvent.VK_D) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				this.setVelX(WALK);
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				this.setVelX(0);
			}
		}
		
		if (key == KeyEvent.VK_SPACE) {
			if (keyAction == KeyEvent.KEY_PRESSED) {
				int sourcex = this.x + this.width/2;
				int sourcey = this.y + this.height/2;
				game.addObject(new ProjectileObject(game, sourcex, sourcey, Math.cos(Math.PI/6), Math.sin(Math.PI/6))); // direction is temporary placeholder until rotation is implemented
			}
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.x += this.getVelX();
		this.y += this.getVelY();
		
		
		//REPLACE: KEEPING PLAY WITHIN GAME WALLS
		if (this.x >= (Game.WIDTH-this.width-5))
			this.x = Game.HEIGHT-this.width-5;
		else if (this.x < 0)
			this.x = 0;
		if (this.y >= Game.HEIGHT-this.height-25)
			this.y = Game.HEIGHT-this.height-25;
		else if(this.y < 0)
			this.y = 0;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
}

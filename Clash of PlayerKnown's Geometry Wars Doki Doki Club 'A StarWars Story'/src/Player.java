import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/* The player class object */
public class Player extends MovableObject implements Observer{
	
	final static int WALK = 5;
	public final static int WIDTH = 20;
	public final static int HEIGHT = 20;
	private GameObjectHandlerView gohv;
	private Color color;
	private boolean moveRight, moveLeft, moveDown, moveUp;
	
	String name;
	
	public Player(String name, GameObjectHandlerView gohv){
		this.name = name;
		this.x = 0;
		this.y = 0;
		this.width = WIDTH;
		this.height = HEIGHT;
		this.gohv = gohv;
		this.color = Color.GRAY;
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
				//this.setVelY(-WALK);
				this.moveUp = true;
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				//this.setVelY(0);
				this.moveUp = false;
			}
			
		}else if(key == KeyEvent.VK_S) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				//this.setVelY(WALK);
				this.moveDown = true;
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				//this.setVelY(0);
				this.moveDown = false;
			}
		}
		
		if(key == KeyEvent.VK_A) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				//this.setVelX(-WALK);
				this.moveLeft = true;
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				//this.setVelX(0);
				this.moveLeft = false;
			}
			
		}else if(key == KeyEvent.VK_D) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				//this.setVelX(WALK);
				this.moveRight = true;
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				//this.setVelX(0);
				this.moveRight = false;
			}
		}
	}

	@Override
	public void tick() {
		
		this.gohv.addTrail(new Trail(this.x, this.y));
		
		this.velX = 0;
		this.velY = 0;
		
		if (this.moveDown) this.velY+=this.WALK;
		if (this.moveUp) this.velY-=this.WALK;
		if (this.moveRight) this.velX+=this.WALK;
		if (this.moveLeft) this.velX-=this.WALK;
		
		// TODO Auto-generated method stub
		this.x += this.getVelX();
		this.y += this.getVelY();
		
		
		//REPLACE: KEEPING PLAY WITHIN GAME WALLS
		if (this.x >= (Game.WIDTH-this.width-5))
			this.x = Game.WIDTH-this.width-5;
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
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
}

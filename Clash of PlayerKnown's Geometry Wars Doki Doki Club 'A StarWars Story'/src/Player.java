import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
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
	private int up, down, left, right;
	
	private int angle;
	
	String name;
	
	public Player(int up, int down, int left, int right, int width, int height,
			Color color, String name, GameObjectHandlerView gohv){
		if (color == null || name == null || gohv == null || width <= 0 || height <= 0)
			throw new IllegalArgumentException();
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.width = width;
		this.height = height;
		this.color = color;
		this.name = name;
		this.gohv = gohv;
		this.angle = 0;
	}
	
	public Player(String name, GameObjectHandlerView gohv){
		this.name = name;
		this.x = 0;
		this.y = 0;
		this.width = WIDTH;
		this.height = HEIGHT;
		this.gohv = gohv;
		this.color = Color.GRAY;
		this.up = KeyEvent.VK_W;
		this.down = KeyEvent.VK_S;
		this.right = KeyEvent.VK_D;
		this.left = KeyEvent.VK_A;
		this.angle = 0;
	}
	
	public void collision() {
		if(!wallCollisionX() && !playerCollisionX()) {
			this.x += this.getVelX();
		}
		if(!wallCollisionY() && !playerCollisionY()) {
			this.y += this.getVelY();
		}
	}
	
	public boolean wallCollisionX() {
		
		for(GameObject wallIterator : this.gohv.getWalls()) {
			Rectangle tempWall = this.getRect();
			
			tempWall.setLocation(this.getX()+ this.velX, this.getY());
			
			if(wallIterator.getRect().intersects(tempWall)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean wallCollisionY() {
		
		for(GameObject wallIterator : this.gohv.getWalls()) {
			Rectangle tempWall = this.getRect();
			
			tempWall.setLocation(this.getX(), this.getY()+ this.velY);
			
			if(wallIterator.getRect().intersects(tempWall)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean playerCollisionX() {
		for(Player playerIter : this.gohv.getPlayers()) {
			if (playerIter == this) {
				//skip, can't collide with self
			}
			else {
				Rectangle tempPlayer1 = this.getRect();
				tempPlayer1.setLocation(this.getX()+ this.velX, this.getY());
				Rectangle tempPlayer2 = playerIter.getRect();
				if(tempPlayer1.intersects(tempPlayer2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean playerCollisionY() {
		for(Player playerIter : this.gohv.getPlayers()) {
			if (playerIter == this) {
				//skip, can't collide with self
			}
			else {
				Rectangle tempPlayer1 = this.getRect();
				tempPlayer1.setLocation(this.getX(), this.getY()+ this.velY);
				Rectangle tempPlayer2 = playerIter.getRect();
				if(tempPlayer1.intersects(tempPlayer2)) {
					return true;
				}
			}
		}
		return false;
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
		if(key == this.up) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				//this.setVelY(-WALK);
				this.moveUp = true;
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				//this.setVelY(0);
				this.moveUp = false;
			}
			
		}else if(key == this.down) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				//this.setVelY(WALK);
				this.moveDown = true;
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				//this.setVelY(0);
				this.moveDown = false;
			}
		}
		
		if(key == this.left) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				//this.setVelX(-WALK);
				this.moveLeft = true;
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				//this.setVelX(0);
				this.moveLeft = false;
			}
			
		}else if(key == this.right) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				//this.setVelX(WALK);
				this.moveRight = true;
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				//this.setVelX(0);
				this.moveRight = false;
			}
		}else if(key == KeyEvent.VK_Q) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				//this.setVelX(WALK);
				this.angle = (this.angle-45)%360;
			}
		}else if(key == KeyEvent.VK_E) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				//this.setVelX(WALK);
				this.angle = (this.angle+45)%360;
			}
		}
	}

	@Override
	public void tick() {
		
		this.gohv.addTrail(new Trail(this.x, this.y, this.color));
		
		this.velX = 0;
		this.velY = 0;
		
		if (this.moveDown) this.velY+=this.WALK;
		if (this.moveUp) this.velY-=this.WALK;
		if (this.moveRight) this.velX+=this.WALK;
		if (this.moveLeft) this.velX-=this.WALK;
		
		// TODO Auto-generated method stub
		collision();
		
		
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

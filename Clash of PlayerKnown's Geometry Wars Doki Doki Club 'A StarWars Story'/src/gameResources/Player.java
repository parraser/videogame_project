package gameResources;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import projectileTypes.AmmoBox;
import projectileTypes.BulletFactory;

/* The player class object */
public class Player extends MovableObject {
	
	final static int WALK = 5;
	public final static int WIDTH = 20;
	public final static int HEIGHT = 20;
	public final static int AMMO_LIMIT = 15; // Amount of bullets a player can shoot before reloading
	public final static int AMMO_RELOAD_TIME = 60*3; // Time it takes to reload in ticks
	private GameObjectHandlerView gohv;
	private Color color;
	private boolean moveRight, moveLeft, moveDown, moveUp;
	private int up, down, left, right, shoot;
	private int angle;
	private int bulType, bulTypeTime, bulCount, bulReloadTime;
	private String name;
	
	public Player(int up, int down, int left, int right, int shoot, int width, int height,
			Color color, String name, GameObjectHandlerView gohv){
		if (color == null || name == null || gohv == null || width <= 0 || height <= 0)
			throw new IllegalArgumentException();
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.shoot = shoot;
		this.width = width;
		this.height = height;
		this.color = color;
		this.name = name;
		this.gohv = gohv;
		this.angle = 0;
		this.bulType = BulletFactory.BUL_DEFAULT;
		this.bulCount = AMMO_LIMIT;
		this.bulReloadTime = 0;
	}
	
	//REMOVE?
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
		this.bulType = BulletFactory.BUL_DEFAULT;
	}
	
	public void collision() {
		int a = ammoBoxCollision();
		if(a != BulletFactory.BUL_DEFAULT) {
			this.bulCount = AMMO_LIMIT;
			this.bulReloadTime = 0;
			this.bulType = a;
		}
		if(!wallCollisionX() && !playerCollisionX()) {
			this.x += this.getVelX();
		}
		if(!wallCollisionY() && !playerCollisionY()) {
			this.y += this.getVelY();
		}
	}
	
	//return the AmmoBox type the player collides with
	public int ammoBoxCollision() {
		Rectangle temp = this.getRect();
		temp.setLocation(this.getX()+this.velX,this.getY()+this.velY);
		for(AmmoBox ammoBox : this.gohv.getAmmoBoxes()) {
			if(temp.intersects(ammoBox.getRect())&&!ammoBox.isPickedUp()) {
				ammoBox.pickUp();
				return ammoBox.getType();
			}
		}
		return 0;
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

	public void update(int key, int keyAction) {
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
		
		if (key == this.shoot && keyAction == KeyEvent.KEY_PRESSED && this.bulCount > 0) {
			this.bulCount --;
			if (this.bulCount <= 0) {
				this.bulReloadTime = AMMO_RELOAD_TIME;
			}
			this.gohv.addProjectile(BulletFactory.shoot(this));
		}
	}


	@Override
	public void tick() {
		this.gohv.addTrail(new Trail(this.x, this.y, this.color));
		
		// Update how much more time before ammo is reloaded
		if (this.bulCount <= 0) {
			this.bulReloadTime --;
			if (this.bulReloadTime == 0) {
				this.bulType = BulletFactory.BUL_DEFAULT;
				this.bulCount = AMMO_LIMIT;
			}
		}
		
		this.velX = 0;
		this.velY = 0;
		
		if (this.moveDown) this.velY+=this.WALK;
		if (this.moveUp) this.velY-=this.WALK;
		if (this.moveRight) this.velX+=this.WALK;
		if (this.moveLeft) this.velX-=this.WALK;

		if(this.bulTypeTime < 0){
			this.bulType = BulletFactory.BUL_DEFAULT;
		}else {
			this.bulTypeTime--;
		}
		
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

	public void setAmmo(int bul){
		this.bulType = bul;
	}

	public GameObjectHandlerView getGOHV() {
		// TODO Auto-generated method stub
		return this.gohv;
	}
	public Color getColor(){
		return this.color;
	}

	public int getAmmo() {
		// TODO Auto-generated method stub
		return this.bulType;
	}
}

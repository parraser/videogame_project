package gameResources;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ProjectileObject extends MovableObject implements HealthObject{
	
	protected GameObjectHandlerView gohv;
	protected int numBounces;
	protected double angle; // in radians
	protected Color color;
	public static final int BUL_DEFAULT_SIZE = 10;
	public static final int BUL_DEFAULT_SPEED = 8;
	public static final int BUL_DEFAULT_BOUNCE = 5;
	
	public ProjectileObject(GameObjectHandlerView gohv, int posx, int posy, double angle){
		this.x = posx;
		this.y = posy;
		this.angle = angle;
		this.width = BUL_DEFAULT_SIZE;
		this.height = BUL_DEFAULT_SIZE;
		this.velX = (int) (BUL_DEFAULT_SPEED*Math.cos(this.angle));
		this.velY = (int) (BUL_DEFAULT_SPEED*Math.sin(this.angle));
		this.gohv = gohv;
		this.color = Color.YELLOW;
		this.numBounces = BUL_DEFAULT_BOUNCE; // number of collisions for projectile to die (disappears on nth collision)
	}
	
	public Color getColor() {
		return this.color;
	}
	public void setColor(Color c) {
		this.color = c;
	}
	
	public void collision() {
		if (collisionX()) {
			this.velX = -this.velX;
			this.numBounces --;
		}else {
			this.x += this.velX;
		}
		if (collisionY()) {
			this.velY = -this.velY;
			this.numBounces --;
		}else {
			this.y += this.velY;
		} 
		if (playerCollisionX() != null) {

		} else if (playerCollisionY() != null) {
		}
	}
	
	public boolean collisionX() {
		Rectangle tempRect = this.getRect();
		for(GameObject wallIterator : this.gohv.getWalls()) {
			
			tempRect.setLocation(this.getX()+ this.velX, this.getY());
			
			if(wallIterator.getRect().intersects(tempRect)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean collisionY() {
		Rectangle tempRect = this.getRect();
		for(GameObject wallIterator : this.gohv.getWalls()) {
			
			tempRect.setLocation(this.getX(), this.getY()+ this.velY);
			
			if(wallIterator.getRect().intersects(tempRect)) {
				return true;
			}
		}
		return false;
	}
	
public GameObject playerCollisionX() {
		
		for(GameObject playerIterator : this.gohv.getPlayers()) {
			Rectangle tempPlayer = this.getRect();
			
			tempPlayer.setLocation(this.getX()+ this.velX, this.getY());
			
			if(playerIterator.getRect().intersects(tempPlayer)) {
				((Player)playerIterator).depleteHealth(2);
				return playerIterator;
			}
		}
		return null;
	}

public GameObject playerCollisionY() {
	
	for(GameObject playerIterator : this.gohv.getPlayers()) {
		Rectangle tempPlayer = this.getRect();
		
		tempPlayer.setLocation(this.getX()+ this.getX(), this.velY);
		
		if(playerIterator.getRect().intersects(tempPlayer)) {
			((Player)playerIterator).depleteHealth(2);
			return playerIterator;
		}
	}
	return null;
}

	
	@Override
	public void tick() {
		collision();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	@Override
	public boolean isDead() {
		return this.numBounces <= 0;
	}

}

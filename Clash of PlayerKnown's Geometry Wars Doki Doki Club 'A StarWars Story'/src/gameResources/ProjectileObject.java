package gameResources;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ProjectileObject extends MovableObject implements HealthObject{
	
	GameObjectHandlerView gohv;
	int numBounces;
	private double angle;
	
	public ProjectileObject(GameObjectHandlerView gohv, int posx, int posy, double angle){
		this.x = posx;
		this.y = posy;
		this.angle = angle;
		this.width = 6;
		this.height = 6;
		this.velX = 8;
		this.velY = 8;
		this.gohv = gohv;
		this.numBounces = 5; // number of collisions for projectile to die (disappears on nth collision)
	}
	
	public void collision() {
		if (collisionX()) {
			this.x += this.velX*Math.cos(this.angle);
			this.velX = -this.velX;
			this.numBounces --;
		} else if (collisionY()) {
			this.y += this.velY*Math.sin(this.angle);
			this.velY = -this.velY;
			this.numBounces --;
		} else if (playerCollisionX() != null) {

		} else if (playerCollisionY() != null) {
		}
	}
	
	public boolean collisionX() {
		
		for(GameObject wallIterator : this.gohv.getWalls()) {
			Rectangle tempRect = this.getRect();
			
			tempRect.setLocation(this.getX()+ this.velX, this.getY());
			
			if(wallIterator.getRect().intersects(tempRect)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean collisionY() {
		
		for(GameObject wallIterator : this.gohv.getWalls()) {
			Rectangle tempRect = this.getRect();
			
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
		this.x += this.velX*Math.cos(this.angle);
		this.y += this.velY*Math.sin(this.angle);
		collision();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
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

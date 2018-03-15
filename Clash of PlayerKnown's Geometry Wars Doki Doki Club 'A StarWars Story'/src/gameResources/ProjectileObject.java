package gameResources;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ProjectileObject extends MovableObject implements HealthObject{
	
	double dirX, dirY; // dirX and dirY for setting direction/angle of projectile motion
	GameObjectHandlerView gohv;
	int numBounces;
	private double angle;
	
	public ProjectileObject(GameObjectHandlerView gohv, int posx, int posy, double angle){
		this.x = posx;
		this.y = posy;
		this.angle = angle;
		this.dirX = Math.cos(angle);
		this.dirY = Math.sin(angle);
		this.width = 6;
		this.height = 6;
		this.velX = 8;
		this.velY = 8;
		this.gohv = gohv;
		this.numBounces = 5; // number of bounces before it dies
	}
	
	public void collision() {
		this.x += this.velX*this.dirX;
		this.y += this.velY*this.dirY;
		if (collisionX()) {
			this.x += this.velX*this.dirX;
			this.velX = -this.velX;
			this.numBounces --;
		} else if (collisionY()) {
			this.y += this.velY*this.dirY;
			this.velY = -this.velY;
			this.numBounces --;
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
	
	@Override
	public void tick() {
		//this.x += this.velX*dirX;
		//this.y += this.velY*dirY;
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

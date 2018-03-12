import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ProjectileObject extends MovableObject implements HealthObject{
	
	double dirX, dirY; // dirX and dirY for setting direction/angle of projectile motion
	GameObjectHandlerView gohv;
	int numBounces;
	
	public ProjectileObject(GameObjectHandlerView gohv, int posx, int posy, double dirX, double dirY){
		this.x = posx;
		this.y = posy;
		this.dirX = dirX; //cos(angle)
		this.dirY = dirY; //sin(angle)
		this.width = 4;
		this.height = 4;
		this.velX = 3;
		this.velY = 3;
		this.gohv = gohv;
		this.numBounces = 5; // number of bounces before it dies
	}
	
	public void collision() {
		if(!collisionX()) {
			this.x += this.velX*this.dirX;
		} else {
			this.velX = -this.velX;
			this.numBounces --;
		}
		if(!collisionY()) {
			this.y += this.velY*this.dirY;
		} else {
			System.out.println(this.velY);
			this.velY = -this.velY;
			System.out.println(this.velY);
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
		g.setColor(Color.red);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	@Override
	public boolean isDead() {
		return this.numBounces == 0;
	}

}

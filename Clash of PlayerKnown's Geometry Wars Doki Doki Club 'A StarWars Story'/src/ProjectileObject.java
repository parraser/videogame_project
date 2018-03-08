import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ProjectileObject extends MovableObject{
	
	double dirx, diry; // dirx and diry for setting direction/angle of projectile motion
	GameObjectHandlerView gohv;
	
	public ProjectileObject(GameObjectHandlerView gohv, int posx, int posy, double dirx, double diry){
		this.x = posx;
		this.y = posy;
		this.dirx = dirx; //cos(angle)
		this.diry = diry; //sin(angle)
		this.width = 4;
		this.height = 4;
		this.velX = 5;
		this.velY = 5;
		this.gohv = gohv;
	}
	
	public void collision() {
		if(!collisionX()) {
			this.x += this.getVelX()*this.dirx;
		}
		if(!collisionY()) {
			this.y += this.getVelY()*this.diry;
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
		//this.x += this.velX*dirx;
		//this.y += this.velY*diry;
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

}

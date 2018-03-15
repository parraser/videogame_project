package gameResources;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ProjectileObject extends MovableObject implements HealthObject{
	
	protected double dirX; // dirX and dirY for setting direction/angle of projectile motion
	protected double dirY;
	protected GameObjectHandlerView gohv;
	public final static int DEFAULT_MAX_BOUNCE = 5;
	public final static int DEFAULT_BUL_SPEED = 5;
	public final static int BUL_SIZE = 10;
	public final static Color DEFAULT_BUL_COLOR = new Color(0, 204, 0);
	protected int numBounces;
	private double angle;
	protected Color color;
	
	
	public ProjectileObject(GameObjectHandlerView gohv, int posx, int posy, double angle){
		this.x = posx;
		this.y = posy;
		this.angle = angle;
		this.dirX = Math.cos(angle); //cos(angle)
		this.dirY = Math.sin(angle); //sin(angle)
		this.width = BUL_SIZE;
		this.height = BUL_SIZE;
		this.velX = DEFAULT_BUL_SPEED;
		this.velY = DEFAULT_BUL_SPEED;
		this.gohv = gohv;
		this.numBounces = DEFAULT_MAX_BOUNCE; // number of bounces before it dies
		this.color = DEFAULT_BUL_COLOR;
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

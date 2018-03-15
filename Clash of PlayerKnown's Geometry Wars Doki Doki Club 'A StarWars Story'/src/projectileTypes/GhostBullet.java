package projectileTypes;

import java.awt.Color;
import java.awt.Rectangle;

import gameResources.*;

public class GhostBullet extends ProjectileObject {
/*
 * Phases through walls decreasing it's speed speeds up once in air, bounces only on world edges, does after 5 bounces
 * (higher than default bullet)
 */
	public final static int B_SIZE = 10;
	public final static int GHOST_MAX_BOUNCE = 10;
	public GhostBullet(int x,int y, double angle, Color color, GameObjectHandlerView gohv){
		super(gohv, x, y, angle);
		this.color = color;
		this.numBounces = GHOST_MAX_BOUNCE;
	}
	
	@Override
	public boolean collisionX(){
		Rectangle tempRect = this.getRect();
		for(GameObject go : this.gohv.getWalls()) {
			tempRect.setLocation(this.getX()+ this.velX, this.getY());
			
			if(go.getRect().intersects(tempRect)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean collisionY(){
		Rectangle tempRect = this.getRect();
		for(GameObject go : this.gohv.getWalls()) {
			tempRect.setLocation(this.getX(), this.getY()+ this.velY);
			
			if(go.getRect().intersects(tempRect)) {
				return true;
			}
		}
		return false;
	}
	public void collision(){
		int newX = this.x;
		int newY = this.y;
		
		if(collisionX()) {
			newX += (this.getVelX()/2)*this.dirX;
		}else{
			newX += this.getVelX()*this.dirX;
		}
		if(collisionY()) {
			newX += (this.getVelY()/2)*this.dirY;
		}else{
			newX += this.getVelY()*this.dirY;
		}
		
		if(newX<0||newX+this.width>Game.WIDTH){
			this.velX = -1*this.velX;
			newX = this.x;
			this.numBounces--;
		}
		if(newY<0||newY+this.height>Game.HEIGHT){
			this.velY = -1*this.velY;
			newY=this.y;
			this.numBounces--;
		}
		this.x = newX;
		this.y = newY;
	}
}

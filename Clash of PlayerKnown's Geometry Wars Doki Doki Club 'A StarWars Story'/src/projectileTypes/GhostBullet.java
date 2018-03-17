package projectileTypes;

import java.awt.Color;
import java.awt.Rectangle;

import gameResources.*;

public class GhostBullet extends ProjectileObject {
/*
 * Phases through walls decreasing it's speed speeds up once in air, bounces only on world edges, does after 5 bounces
 * (higher than default bullet)
 */
	public final static int BUL_GHOST_SPEED = 3;
	public final static int GHOST_MAX_BOUNCE = 10;
	public GhostBullet(int x,int y, double angle, Color color, GameObjectHandlerView gohv){
		super(gohv, x, y, angle);
		this.color = color;
		this.velX = BUL_GHOST_SPEED;
		this.velY = BUL_GHOST_SPEED;
		this.numBounces = GHOST_MAX_BOUNCE;
	}
	@Override
	public void tick() {
		collision();
	}
	public void collision(){
		int newX = this.x;
		int newY = this.y;
		
		if(collisionX()) {
			newX += (this.getVelX()/2)*Math.cos(this.angle);
		}else{
			newX += this.getVelX()*Math.cos(this.angle);
		}
		if(collisionY()) {
			newY += (this.getVelY()/2)*Math.sin(this.angle);
		}else{
			newY += this.getVelY()*Math.sin(this.angle);
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

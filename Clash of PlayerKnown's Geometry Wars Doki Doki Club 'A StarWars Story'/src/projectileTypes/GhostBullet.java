package projectileTypes;

import java.awt.Color;
import java.awt.Rectangle;

import gameResources.*;

public class GhostBullet extends ProjectileObject {
/*
 * Phases through walls decreasing it's speed speeds up once in air, bounces only on world edges, does after 5 bounces
 * (higher than default bullet)
 */
	public final static int BUL_GHOST_SPEED = 5;
	public final static int GHOST_MAX_BOUNCE = 3;
	public final static Color BUL_GHOST_COLOR = new Color(179, 224, 255);
	public GhostBullet(int x,int y, double angle, GameObjectHandlerView gohv){
		super(gohv, x, y, angle);
		this.color = BUL_GHOST_COLOR;
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
			newX += ((float)this.getVelX()*(2f/3f))*Math.cos(this.angle);
		}else{
			newX += this.getVelX()*Math.cos(this.angle);
		}
		if(collisionY()) {
			newY +=  ((float)this.getVelY()*(2f/3f))*Math.sin(this.angle);
		}else{
			newY += this.getVelY()*Math.sin(this.angle);
		}
		playerCollisionX();
		playerCollisionY();
		
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
		// TODO temporary
		playerCollisionX();
		playerCollisionY();
	}
}

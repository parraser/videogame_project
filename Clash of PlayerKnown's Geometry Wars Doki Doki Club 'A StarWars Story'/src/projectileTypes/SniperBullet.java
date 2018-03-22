package projectileTypes;

/*
 * Pierces blocks of a certain depth, it's life decreases the longer it pierces blocks. Really fast, does not bounce.
 */

import java.awt.Color;
import java.awt.Rectangle;

import gameResources.*;

public class SniperBullet extends ProjectileObject{
/*
 * Phases through walls decreasing it's speed speeds up once in air, bounces only on world edges, does after 2-3 bounces
 */
	int pLife;
	public final static int SNIPEBULL_PENETRATE_LIFE = 22;
	public final static int B_SIZE = 10;
	public final static int BUL_SNIPE_SPEED = 7;
	public final static Color BUL_SNIPE_COLOR = new Color(255, 255, 128);
	public SniperBullet(int x,int y, double angle, GameObjectHandlerView gohv){
		super(gohv, x, y, angle);
		this.color = BUL_SNIPE_COLOR;
		this.pLife = SNIPEBULL_PENETRATE_LIFE;
		this.velX = BUL_SNIPE_SPEED;
		this.velY = BUL_SNIPE_SPEED;
	}
	public void collision(){
		if(collisionX() || collisionY()){
			this.pLife -=1;
		}
		
		playerCollisionX();
		playerCollisionY();
		//When the bullet is out of bounds simply kill
		if(this.x > Game.WIDTH || this.x + this.width < 0 || this.y > Game.HEIGHT || this.y + this.height < 0 ){
			this.pLife = -1;
		}
		this.x += this.velX*Math.cos(this.angle);
		this.y += this.velY*Math.sin(this.angle);
		// TODO temporary
		playerCollisionX();
		playerCollisionY();
	}
	public boolean isDead(){
		if(this.pLife < 0){
			return true;
		}
		return false;
	}
}

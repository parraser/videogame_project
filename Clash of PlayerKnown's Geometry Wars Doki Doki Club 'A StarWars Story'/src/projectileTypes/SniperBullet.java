package projectileTypes;

/*
 * Pierces blocks of a certain depth, it's life decreases the longer it pierces blocks. Really fast, does not bounce.
 */

import java.awt.Color;
import gameResources.*;

public class SniperBullet extends ProjectileObject{
/*
 * Phases through walls decreasing it's speed speeds up once in air, bounces only on world edges, does after 2-3 bounces
 */
	int pLife;
	public final static int SNIPEBULL_PENETRATE_LIFE = 60;
	public final static int B_SIZE = 10;
	public SniperBullet(int x,int y, int velX, int velY, Color color, GameObjectHandlerView gohv){
		this.x = x;
		this.y = y;
		this.velY = velY;
		this.velX = velX;
		this.color = color;
		this.width = B_SIZE;
		this.height = B_SIZE;
		this.gohv = gohv;
		this.pLife = SNIPEBULL_PENETRATE_LIFE;
	}
	private boolean collisionX(){
		for(GameObject go : this.gohv.getWalls()) {
			Rectangle tempRect = this.getRect();
			
			tempRect.setLocation(this.getX()+ this.velX, this.getY());
			
			if(go.getRect().intersects(tempRect)) {
				return true;
			}
		}
		return false;
	}
	private boolean collisionY(){
		for(GameObject go : this.gohv.getWalls()) {
			Rectangle tempRect = this.getRect();
			
			tempRect.setLocation(this.getX(), this.getY()+ this.velY);
			
			if(go.getRect().intersects(tempRect)) {
				return true;
			}
		}
		return false;
	}
	private void collision(){
		if(collisionX() || collisionY()){
			this.pLife -=1;
		}
		
		//When the bullet is out of bounds simply kill
		if(this.x > Game.WIDTH || this.x + this.width < 0 || this.y > Game.HEIGHT || this.y + this.height < 0 ){
			this.pLife = -1;
		}
	}
	public boolean isDead(){
		if(this.pLife < 0){
			return true;
		}
		return false;
	}
}

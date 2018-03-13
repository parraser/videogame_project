package projectileTypes;

import java.awt.Color;
import gameResources.*;

public class GhostBullet extends ProjectileObject {
/*
 * Phases through walls decreasing it's speed speeds up once in air, bounces only on world edges, does after 5 bounces
 * (higher than default bullet)
 */
	public final static int B_SIZE = 10;
	public GhostBullet(int x,int y, double angle, Color color, GameObjectHandlerView gohv){
		super(gohv, x, y, angle);
		this.color = color;
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
		if(collisionX()) {
			this.x += (this.getVelX()/2)*this.dirx;
		}else{
			this.x += this.getVelX()*this.dirx;
		}
		if(collisionY()) {
			this.y += (this.getVelY()/2)*this.diry;
		}else{
			this.y += this.getVelY()*this.diry;
		}
	}
}

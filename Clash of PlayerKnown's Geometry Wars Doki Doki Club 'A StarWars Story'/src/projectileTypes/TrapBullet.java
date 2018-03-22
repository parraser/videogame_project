package projectileTypes;

import java.awt.Color;
import java.awt.Rectangle;

import gameResources.GameObject;
import gameResources.GameObjectHandlerView;
import gameResources.ProjectileObject;

public class TrapBullet extends ProjectileObject{
	public final static Color BUL_TRAP_COLOR = new Color(213, 56, 57);
	public final static int BUL_TRAP_DURATION = 60*10; // in game ticks
	public final static int BUL_TRAP_SIZE = 5;
	public final static int BUL_TRAP_SPEED = 5;
	private int lifeSpan;
	private boolean trapSet;
	
	public TrapBullet(int x, int y, double angle, GameObjectHandlerView gohv){
		super(gohv, x, y, angle);
		this.color = BUL_TRAP_COLOR;
		this.lifeSpan = BUL_TRAP_DURATION;
		this.trapSet = false;
	}
	@Override
	public void collision() {
		if (collisionX()||collisionY()) {
			this.trapSet = true;
		}
		// TODO temporary
		playerCollisionX();
		playerCollisionY();
	}
	@Override
	public void tick(){
		collision();
		if(!trapSet) {
			this.x += this.velX;
			this.y += this.velY;
		}
		this.lifeSpan--;
	}
	
	@Override
	public boolean isDead() {
		return this.lifeSpan < 0;
	}
}

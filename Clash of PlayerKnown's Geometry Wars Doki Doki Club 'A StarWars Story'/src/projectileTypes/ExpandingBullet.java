package projectileTypes;

import java.awt.Color;

import gameResources.GameObjectHandlerView;
import gameResources.ProjectileObject;

public class ExpandingBullet extends ProjectileObject{
	public final static Color BUL_EXPAND_COLOR = new Color(67, 61, 112);
	public final static int BUL_EXPAND_MAX_SIZE = 40;
	public final static int BUL_EXPAND_MIN_SIZE = 5;
	public final static int BUL_EXPAND_INTERVAL = 4; // the interval in game ticks on when to increase size
	private int expandIn;
	public ExpandingBullet(int x, int y, double angle, GameObjectHandlerView gohv){
		super(gohv, x, y, angle);
		this.color = BUL_EXPAND_COLOR;
		this.velX = (int) (ProjectileObject.BUL_DEFAULT_SPEED*Math.cos(this.angle));
		this.velY = (int) (ProjectileObject.BUL_DEFAULT_SPEED*Math.sin(this.angle));
		this.expandIn = BUL_EXPAND_INTERVAL;
	}
	@Override
	public void tick() {
		collision();
		if(expandIn<1) {
			expandIn = BUL_EXPAND_INTERVAL;
			this.width++;
			this.height++;
		}else {
			expandIn--;
		}
	}
	@Override
	public boolean isDead() {
		return this.width > BUL_EXPAND_MAX_SIZE;
	}
}

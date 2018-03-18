package projectileTypes;

import java.awt.Color;

import gameResources.GameObjectHandlerView;
import gameResources.Player;
import gameResources.ProjectileObject;

public class BulletFactory {
	public static final int BUL_TYPES = 5;
	public static final int BUL_DEFAULT = 0;
	public static final int BUL_GHOST = 1;
	public static final int BUL_SNIPE = 2;
	public static final int BUL_TRAP = 3;
	public static final int BUL_EXPAND = 4;
	public static ProjectileObject shoot(Player p){
		ProjectileObject bullet;
		bullet=null;
		if(p.getAmmo() == BUL_DEFAULT){
			bullet = new ProjectileObject(p.getGOHV(), p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.random()*Math.PI*2);
		}else if(p.getAmmo() == BUL_GHOST){
			bullet = new GhostBullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.random()*Math.PI*2, p.getGOHV());
		}else if(p.getAmmo() == BUL_SNIPE){
			bullet = new SniperBullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.random()*Math.PI*2, p.getGOHV());
		}else if(p.getAmmo() == BUL_TRAP) {
			bullet = new TrapBullet(p.getX(), p.getY(), Math.random()*Math.PI*2, p.getGOHV());
		}else if(p.getAmmo() == BUL_EXPAND) {
			bullet = new ExpandingBullet(p.getX(), p.getY(), Math.random()*Math.PI*2, p.getGOHV());
		}
		return bullet;
	}
}

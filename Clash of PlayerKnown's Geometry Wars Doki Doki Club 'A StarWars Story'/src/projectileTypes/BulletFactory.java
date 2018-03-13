package projectileTypes;

import gameResources.Player;
import gameResources.ProjectileObject;

public class BulletFactory {
	public static ProjectileObject shoot(Player p){
		// TODO WAIT FOR @brian to do below
		ProjectileObject bullet;
		bullet=null;
		if(p.getBulletType() == Player.BUL_DEFAULT){
			bullet = new ProjectileObject(p.getGOHV(), p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.PI/6);
		}else if(p.getBulletType() == Player.BUL_GHOST){
			bullet = new GhostBullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.PI/6, p.getColor(), p.getGOHV());
		}else if(p.getBulletType() == Player.BUL_SNIPE){
			bullet = new SniperBullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.PI/6, p.getColor(), p.getGOHV());
		}
		return bullet;
	}
}

package projectileTypes;

import java.awt.Color;

import gameResources.Player;
import gameResources.ProjectileObject;

public class BulletFactory {
	public static ProjectileObject shoot(Player p){
		// TODO WAIT FOR @brian to do below
		ProjectileObject bullet;
		bullet=null;
		if(p.getBulletType() == Player.BUL_DEFAULT){
			bullet = new ProjectileObject(p.getGOHV(), p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.random()*Math.PI*2);
		}else if(p.getBulletType() == Player.BUL_GHOST){
			bullet = new GhostBullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.random()*Math.PI*2, new Color(179, 224, 255), p.getGOHV());
		}else if(p.getBulletType() == Player.BUL_SNIPE){
			bullet = new SniperBullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.PI/6, new Color(255, 255, 128), p.getGOHV());
		}
		return bullet;
	}
}

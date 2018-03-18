package projectileTypes;

import java.awt.Color;

import gameResources.Player;
import gameResources.ProjectileObject;

public class BulletFactory {
	public static ProjectileObject shoot(Player p){
		ProjectileObject bullet;
		bullet=null;
		if(p.getAmmo() == AmmoBox.BUL_DEFAULT){
			bullet = new ProjectileObject(p.getGOHV(), p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.random()*Math.PI*2);
		}else if(p.getAmmo() == AmmoBox.BUL_GHOST){
			bullet = new GhostBullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.random()*Math.PI*2, p.getGOHV());
		}else if(p.getAmmo() == AmmoBox.BUL_SNIPE){
			bullet = new SniperBullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Math.PI/6, p.getGOHV());
		}
		return bullet;
	}
}

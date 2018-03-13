package projectileTypes;

import gameResources.Player;

public class BulletFactory {
	public ProjectileObject shoot(Player p){
		// TODO WAIT FOR @brian to do below
		ProjectileObject bullet;
		if(p.getBulletType() == Player.BUL_DEFAULT){
			bullet = new ProjectileObject();
		}else if(p.getBulletType() == Player.BUL_GHOST){
			bullet = new GhostBullet();
		}else if(p.getBulletType() == Player.BUL_SNIPE){
			bullet = new SniperBullet();
		}
		return bullet;
	}
}

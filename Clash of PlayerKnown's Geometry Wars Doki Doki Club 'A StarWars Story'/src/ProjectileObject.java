import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ProjectileObject extends MovableObject{
	
	double dirx, diry; // dirx and diry for setting direction/angle of projectile motion
	int velocity;
	GameObjectHandlerView gohv;
	
	public ProjectileObject(GameObjectHandlerView gohv, int posx, int posy, double dirx, double diry){
		this.x = posx;
		this.y = posy;
		this.dirx = dirx;
		this.diry = diry;
		this.width = 4;
		this.height = 4;
		this.velocity = 5;
		this.gohv = gohv;
	}
	
	@Override
	public void tick() {
		this.x += velocity*dirx;
		this.y += velocity*diry;
		/*
		if (this.x < 0 - this.width || this.x > Game.WIDTH + this.width ||
				this.y < 0 - this.height || this.y > Game.HEIGHT + this.height) {
			gohv.removeObject(this);
		}
		*/
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

}

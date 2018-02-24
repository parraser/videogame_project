import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ProjectileObject extends MovableObject{
	
	int dirx;
	int diry;
	//int timeToLive;
	
	public ProjectileObject(int posx, int posy, int dirx, int diry){
		this.x = posx;
		this.y = posy;
		this.dirx = dirx;
		this.diry = diry;
		this.width = 2;
		this.height = 2;
		//this.timeToLive = 
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.x += 3*dirx;
		this.y += 3*diry;
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

package gameResources;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject{
	// The default color for walls
	public final static Color COLOR = new Color(100,100,100);
	
	public Wall(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		/* Actually does nothing on tick; the player is the one that checks for collisions. */
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(this.COLOR);
		g.fillRect(this.x, this.y, this.width, this.height);
	}


	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/* The player class object */
public class Player extends MovableObject implements Observer{
	
	final static int WALK = 10;
	//final static int RUN = 15;
	
	String name;
	
	public Player(String name){
		this.name = name;
	}
	
	
	public void collision() {
		
	}

	@Override
	public void update(Observable o, Object e) {
		// TODO Auto-generated method stub
		
		int key = ((KeyEvent) e).getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			
			this.setVelY(-WALK);
			
		}else if(key == KeyEvent.VK_S) {
			
			this.setVelY(WALK);
			
		}else if(key == KeyEvent.VK_A) {
			
			this.setVelX(-WALK);
			
		}else if(key == KeyEvent.VK_D) {
			
			this.setVelX(WALK);
			
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.x = this.x + this.getVelX();
		this.y = this.y + this.getVelY();
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public boolean intersects(Rectangle r) {
		// TODO Auto-generated method stub
		return false;
	}
}

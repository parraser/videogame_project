import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/* The player class object */
public class Player extends MovableObject implements Observer{
	
	final static int WALK = 10;
	
	String name;
	
	public Player(String name){
		this.name = name;
		this.x = 0;
		this.y = 0;
		this.width = 10;
		this.height = 10;
	}
	
	
	public void collision() {
		
	}

	@Override
	public void update(Observable o, Object e) {
		// TODO Auto-generated method stub
		
		int key = ((KeyEvent) e).getKeyCode();
		int keyAction = ((KeyEvent) e).getID();

		//since actions are recorded once, releasing the key
		//shall input an equal negative velocity
		// could open a bug where one key is not recorded 
		// it will have twice the speed
		System.out.println("KEY: " + e.toString());
		if(key == KeyEvent.VK_W) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				this.setVelY(-WALK);
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				this.setVelY(WALK);
			}
			
		}else if(key == KeyEvent.VK_S) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				this.setVelY(WALK);
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				this.setVelY(-WALK);
			}
		}
		
		if(key == KeyEvent.VK_A) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				this.setVelY(-WALK);
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				this.setVelY(WALK);
			}
			
		}else if(key == KeyEvent.VK_D) {
			if(keyAction == KeyEvent.KEY_PRESSED) {
				this.setVelY(WALK);
			}else if (keyAction == KeyEvent.KEY_RELEASED) {
				this.setVelY(-WALK);
			}
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

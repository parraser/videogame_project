package gameResources;

import java.awt.Color;
import java.awt.Graphics;

public class Health {
	
	public static int life = 100;
	
	public void tick() {
		//life--;
	}
	
	public void render (Graphics e) {
		e.setColor(Color.gray);
		e.fillRect(15, 15, 200, 32);
		e.setColor(Color.green);
		e.fillRect(15, 15, life*2, 32);
		e.setColor(Color.WHITE);
		e.drawRect(15, 15, 200, 32);
		
	}

}

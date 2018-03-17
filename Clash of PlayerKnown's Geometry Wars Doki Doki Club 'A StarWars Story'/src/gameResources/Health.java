package gameResources;

import java.awt.Color;
import java.awt.Graphics;

import gameResources.Game.State;

public class Health {
	public Health() {
		
	}
	public int life = 100;
	
	public void tick() {
		//life--;
		/*
		if (this.life <= 0) {
			state = State.END;
		}
		*/
	}
	
	public void depleteHealth(int health) {
		this.life -= health;
	}
	
	public void render (Graphics e) {
		e.setColor(Color.gray);
		e.fillRect(15, 15, 200, 32);
		e.setColor(Color.green);
		e.fillRect(15, 15, life*2, 32);
		e.setColor(Color.WHITE);
		e.drawRect(15, 15, 200, 32);
		//System.out.print(life);
		
	}

}

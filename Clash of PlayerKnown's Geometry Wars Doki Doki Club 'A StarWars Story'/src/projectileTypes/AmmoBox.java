package projectileTypes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gameResources.GameObject;
import gameResources.HealthObject;

public class AmmoBox extends GameObject{
	public final static Color AMMOBOX_COLOR = Color.CYAN;
	public final static int AMMOBOX_MAX_SIZE = 30;
	public final static int AMMOBOX_MIN_SIZE = 20;
	public final static int AMMOBOX_RESPAWN_TIME = 60*10;//in game ticks
	private Color color;
	private int type, respawnTime, centerX, centerY;
	private boolean growing = false, pickedUp;
	
	public AmmoBox(int type) {
		this.x = 0;
		this.y = 0;
		this.width = AMMOBOX_MAX_SIZE;
		this.height = AMMOBOX_MAX_SIZE;
		this.color = AMMOBOX_COLOR;
		this.type = type;
		this.pickedUp = false;
	}
	public AmmoBox(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = AMMOBOX_MAX_SIZE;
		this.height = AMMOBOX_MAX_SIZE;
		this.centerX = x+width/2;
		this.centerY = y+height/2;
		this.color = AMMOBOX_COLOR;
		randomizeType();
		this.pickedUp = false;
	}
	
	private void randomizeType() {
		this.type = (int) (Math.random()*(BulletFactory.BUL_TYPES-1)+1);
	}
	
	@Override
	public void tick() {
		
		//respawn the ammobox when it has been picked up
		if(pickedUp) {
			if(respawnTime < 1){
				pickedUp = false;
				randomizeType();
			}else {
				respawnTime--;
			}
		}
		
		//Make the box periodically grow/shrink in size
		if(growing) {
			if(this.width > AMMOBOX_MAX_SIZE) {
				this.growing = false;
			}else {
				this.width++;
				this.height++;
			}
		}else {
			if(this.width < AMMOBOX_MIN_SIZE) {
				this.growing = true;
			}else {
				this.width--;
				this.height--;
			}
		}
		this.x = this.centerX - this.width/2;
		this.y = this.centerY - this.height/2;
	}

	@Override
	public void render(Graphics g) {
		if(!pickedUp) {
			g.setColor(this.color);
			g.fillRect(this.x, this.y, this.width, this.height);
		}
	}

	@Override
	public Rectangle getRect() {
		return new Rectangle(this.x,this.y,this.width,this.height);
	}
	public int getType() {
		return this.type;
	}
	public void pickUp() {
		this.pickedUp = true;
		this.respawnTime = AMMOBOX_RESPAWN_TIME;
	}
	public boolean isPickedUp() {
		return this.pickedUp;
	}
	
}

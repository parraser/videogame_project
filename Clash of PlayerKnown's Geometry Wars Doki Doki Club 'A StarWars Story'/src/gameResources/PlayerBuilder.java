package gameResources;

import java.awt.Color;
import java.util.function.Supplier;

public class PlayerBuilder implements Supplier<Player> {
	private int up, down, left, right, shoot, width, height;
	private String name = null;
	private GameObjectHandlerView gohv = null;
	private Color color = null;
	
	public void setUp(int up) {
		this.up = up;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public void setShoot(int shoot) {
		this.shoot = shoot;
	}
	
	public void setWidth(int width) {
		if (width <= 0)
			throw new IllegalArgumentException();
		this.width = width;
	}

	public void setHeight(int height) {
		if (height <= 0)
			throw new IllegalArgumentException();
		this.height = height;
	}

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException();
		this.name = name;
	}

	public void setGohv(GameObjectHandlerView gohv) {
		if (gohv == null)
			throw new IllegalArgumentException();
		this.gohv = gohv;
	}

	public void setColor(Color color) {
		if (color == null)
			throw new IllegalArgumentException();
		this.color = color;
	}

	public PlayerBuilder(){
		
	}
	
	@Override
	public Player get() {
		if (name == null || gohv == null || color == null)
			return null;
		return new Player(this.up, this.down, this.left, this.right, this.shoot, this.width, this.height, this.color, this.name, this.gohv);
	}

}

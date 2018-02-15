
/* A movable version of a GameObject */
public abstract class MovableObject extends GameObject {
	
	protected int velX = 0;
	protected int velY = 0;

	
	public int getVelX() {
		System.out.println(this.velX);
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;

	}

}

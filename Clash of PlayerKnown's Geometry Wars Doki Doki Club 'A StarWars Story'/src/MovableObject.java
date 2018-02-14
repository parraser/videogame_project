
/* A movable version of a GameObject */
public abstract class MovableObject extends GameObject {
	
	private int velocity;
	
	public int getVelocity() {
		return this.velocity;
	}
	
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
}

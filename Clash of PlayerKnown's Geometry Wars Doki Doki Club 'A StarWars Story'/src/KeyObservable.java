import java.util.Observable;

public class KeyObservable extends Observable {
	private int change = 0;
	public KeyObservable(){		
	}
	
	public void chang(){
		this.setChanged();
	}
}

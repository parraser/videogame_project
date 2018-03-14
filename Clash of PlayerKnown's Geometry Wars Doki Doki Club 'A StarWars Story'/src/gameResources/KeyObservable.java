package gameResources;
import java.util.Observable;

public class KeyObservable extends Observable {
	public KeyObservable(){		
	}

	public void chang(){
		this.setChanged();
	}
}

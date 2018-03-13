package gameResources;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;

public class KeyHandler extends KeyAdapter{
	protected KeyObservable obs;
	
	public KeyHandler(){
		this.obs = new KeyObservable();
	}
	
	public void keyPressed(KeyEvent e){
		//System.out.println(e.paramString());
		this.obs.chang();
		this.obs.notifyObservers(e);
	}
	public void keyReleased(KeyEvent e){
		//System.out.println(e.paramString());
		this.obs.chang();
		this.obs.notifyObservers(e);
	}
	public void addObserver(Object o){
		this.obs.addObserver((Observer) o);
	}
	
}

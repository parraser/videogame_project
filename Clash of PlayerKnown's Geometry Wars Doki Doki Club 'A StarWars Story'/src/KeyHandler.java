import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter{
	KeyObservable obs;
	
	public KeyHandler(){
		obs = new KeyObservable();
	}
	
	public void keyPressed(KeyEvent e){
		//System.out.println(e.paramString());
		this.obs.notifyObservers(e);
	}
	public void keyReleased(KeyEvent e){
		//System.out.println(e.paramString());
		this.obs.notifyObservers(e);
	}
	
}

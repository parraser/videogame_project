import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter{
	KeyObservable obs;
	
	public KeyHandler(){
		obs = new KeyObservable();
		
		System.out.println("hi\n");
	}
	
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	
}

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
	public void remObservers() {
		this.obs.deleteObservers();
	}
	
}


//package gameResources;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.util.List;
//import java.util.Observer;
//
//public class KeyHandler extends KeyAdapter{
//	private Game game; // to check state
//	private MainMenu mainMenu;
//	private List<Player> players; // to access players
//	
//	public KeyHandler(Game game, List<Player> players, MainMenu mainMenu){
//		this.game = game;
//		this.mainMenu = mainMenu;
//		this.players = players;
//	}
//	
//	public void updateObjects(KeyEvent e) {
//		int key = ((KeyEvent) e).getKeyCode();
//		int keyAction = ((KeyEvent) e).getID();
//		if (game.getState() == Game.State.MAIN_MENU || game.getState() == Game.State.END) {
//			this.mainMenu.update(key, keyAction);
//		} else if (game.getState() == Game.State.GAME) {
//			for (Player p: this.players) {
//				p.update(key, keyAction);
//			}
//		}
//	}
//	
//	public void keyPressed(KeyEvent e){
//		this.updateObjects(e);
//	}
//	
//	public void keyReleased(KeyEvent e){
//		this.updateObjects(e);
//	}
//	/*public void remObservers() {
//		this.obs.deleteObservers();
//	}*/
//	
//}


public class MapMaker {
	//private Game game;
	private GameObjectHandlerView gohv;
	public MapMaker(GameObjectHandlerView game){
		this.gohv = game;
	}
	
	/**
	 * Adds the object to the game
	 * @param obj GameObject to add
	 */
	public void addObj(GameObject obj){
		this.gohv.addObject(obj);
	}
	public void createPlayer(/* CONTROL SCHEME?????? */){
		
	}

}

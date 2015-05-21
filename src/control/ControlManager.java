package control;

public class ControlManager {
	private GameStateManager gsm;
	public ControlManager(){
		this.gsm = new GameStateManager(this);
	}
	
	public GameStateManager getGameStateManager() {
		return gsm;
	}
}

package control;

public class ControlManager {
	private GameStateManager gsm;
	private WiiMoteHandler wii;
	public ControlManager(){
		this.gsm = new GameStateManager(this);
		this.wii = new WiiMoteHandler();
	}
	
	public GameStateManager getGameStateManager() {
		return gsm;
	}
}

package control;

import view.GameFrame;

public class ControlManager {
	private GameStateManager gsm;
<<<<<<< HEAD
	private GameFrame frame;
	public ControlManager(GameFrame frame){
		this.frame = frame;
=======
	private WiiMoteHandler wii;
	public ControlManager(){
>>>>>>> 2c521b80aa213f9a7bc9779457bceddb9756fc39
		this.gsm = new GameStateManager(this);
		this.wii = new WiiMoteHandler();
	}
	
	public GameStateManager getGameStateManager() {
		return gsm;
	}
	
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
	
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
}

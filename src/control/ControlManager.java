package control;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import view.GameFrame;
import control.MusicHandler.AudioType;

public class ControlManager {
	private GameStateManager gsm;
	private GameFrame frame;
	private WiiMoteHandler wii;
	private MusicHandler audio;
	private VideoHandler video;
	private InputHandler inputHandler;
	
	public ControlManager(GameFrame frame) throws LineUnavailableException, IOException{
		//this.inputHandler = new InputHandler("COM6");
		this.frame = frame;
		this.gsm = new GameStateManager(this);
		this.wii = new WiiMoteHandler();
		this.audio = new MusicHandler();
		
		
		//this.video = new VideoHandler();

		audio.playSound(AudioType.intro);
		
	}
	
	public MusicHandler getMusicHandler(){
		return audio;
	}
	
	public GameStateManager getGameStateManager() {
		return gsm;
	}
	
	public InputHandler getInputHandler() {
		return inputHandler;
	}
	
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
	
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
}

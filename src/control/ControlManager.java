package control;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import control.MusicHandler.AudioType;
import view.GameFrame;

public class ControlManager {
	private GameStateManager gsm;
	private GameFrame frame;
	private WiiMoteHandler wii;
	private MusicHandler audio;
	
	public ControlManager(GameFrame frame) throws LineUnavailableException, IOException{
		this.frame = frame;
		this.gsm = new GameStateManager(this);
		this.wii = new WiiMoteHandler();
		this.audio = new MusicHandler();

		audio.playSound(AudioType.intro);
		
	}
	
	public MusicHandler getMusicHandler(){
		return audio;
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

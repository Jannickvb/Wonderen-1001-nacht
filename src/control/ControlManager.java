package control;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import view.GameFrame;
import control.MusicHandler.AudioType;

public class ControlManager {
	private GameStateManager gsm;
	private GameFrame frame;
	private WiiMoteHandler wii;
	private MusicHandler music;
	private MusicHandler voice;
	private VideoHandler video;
	
	public ControlManager(GameFrame frame) throws LineUnavailableException, IOException{
		this.frame = frame;
		this.gsm = new GameStateManager(this);
		this.wii = new WiiMoteHandler();
		this.music = new MusicHandler();
		this.voice = new MusicHandler();
		//this.video = new VideoHandler();

		music.playSound(AudioType.intro);
	}
	
	public void playIntroVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.introvoice);
	}
	
	public void playBoatTutorialVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.boattutorial);
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

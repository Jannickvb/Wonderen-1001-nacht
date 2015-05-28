package control;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

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
		this.video = new VideoHandler(this);

		music.playSound(AudioType.intro);
	}
	
	public void playIntroVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.introvoice);
		music.volumeUp(false, 6.0f);
	}
	
	public void stopIntroVoice() throws LineUnavailableException, IOException{
		voice.stopSound();
		music.volumeUp(true, 6.0f);
	}
	
	public void playBoatTutorialVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.boattutorial);
		music.volumeUp(false, 6.0f);
	}
	
	public VideoHandler getVideoHandler()
	{
		return video;
	}
	
	public GameStateManager getGameStateManager() {
		return gsm;
	}
	
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
}

package control;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

import view.GameFrame;
import control.MusicHandler.AudioType;

public class ControlManager {
	private GameStateManager gsm;
	private GameFrame frame;
	private InputHandler input;
	private MusicHandler music;
	private MusicHandler voice;
	private VideoHandler video;
	
	public ControlManager(GameFrame frame) throws LineUnavailableException, IOException{
		this.frame = frame;
		this.gsm = new GameStateManager(this);
		this.input = new InputHandler();
		this.music = new MusicHandler();
		this.voice = new MusicHandler();
		this.video = new VideoHandler(this);

		music.playSound(AudioType.intro);
	}
	
	public void playIntroVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.introvoice);
	}
	
	public void playMusic1() throws LineUnavailableException, IOException{
		music.stopSound();
		music.playSound(AudioType.poor);
	}
	
	public void playBoatTutorialVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.boattutorial);
	}
	
	public void playWizardVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.boattutorial);
	}
	
	public void playTrollVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.boattutorial);
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
	
	public InputHandler getInput(){
		return input;
	}
}

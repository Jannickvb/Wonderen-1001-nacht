package control;

import java.awt.Toolkit;
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
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	public ControlManager(GameFrame frame) throws LineUnavailableException, IOException{
		this.input = new InputHandler("COM6");
		this.frame = frame;
		this.gsm = new GameStateManager(this);
		
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
	
	public void playMusic2() throws LineUnavailableException, IOException{
		music.stopSound();
		music.playSound(AudioType.rich);
	}
	
	public void playMusic3() throws LineUnavailableException, IOException{
		music.stopSound();
		music.playSound(AudioType.end);
	}
	
	public void playBoatTutorialVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.boattutorial);
	}
	
	public void arrivalVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.arrival1);
	}
	
	public void travelChoiceVoice() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.arrival2);
	}
	
	public void playIntroWizard() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.introwizard);
	}
	
	public void playChoicePoor() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.choicepoor);
	}
	
	public void playChoiceRich() throws LineUnavailableException, IOException{
		voice.playSound(AudioType.choicerich);
	}

	public VideoHandler getVideoHandler()
	{
		return video;
	}
	
	public GameStateManager getGameStateManager() {
		return gsm;
	}

	public JFrame getFrame()
	{
		return frame;
	}
	
	public InputHandler getInputHandler(){
		return input;
	}
}

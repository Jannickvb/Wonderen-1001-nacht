package model.gamestates;


import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import control.ControlManager;
import control.VideoHandler;

public class IntroMovState extends GameState{

	private VideoHandler video;
	
	public IntroMovState(ControlManager cm) {
		super(cm);
	}
	
	@Override
	public void init() {
		
		String userpath = System.getProperty("user.dir").replaceAll("\\\\", "/");
		video = cm.getVideoHandler();
		video.handleVideo(userpath + "/resources/video/intro.mp4");
		
		try {
			cm.playIntroVoice();
		} catch (LineUnavailableException | IOException e1) {
			e1.printStackTrace();
		}
		
		video.run();
	}
	
	public void isFinished(boolean finished){
		if(finished){
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void draw(Graphics2D g2) {

		g2.setPaint(Color.BLACK);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, cm.getWidth(), cm.getHeight());
		g2.setBackground(Color.BLACK);
	}
	
	@Override
	public void update() {
		isFinished(video.getFinished());
	}
}

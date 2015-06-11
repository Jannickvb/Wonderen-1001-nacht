package model.gamestates.magepath;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import model.gamestates.GameState;
import control.ControlManager;
import control.VideoHandler;

public class PoorMovState extends GameState{

	private VideoHandler video;
	
	public PoorMovState(ControlManager cm) {
		super(cm);
	}
	
	@Override
	public void init() {
		
		String userpath = System.getProperty("user.dir").replaceAll("\\\\", "/");
		video = cm.getVideoHandler();
		video.handleVideo(userpath + "/resources/video/Poor.mpg");
		
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
		g2.fillRect(0, 0, ControlManager.screenWidth, ControlManager.screenHeight);
		g2.setBackground(Color.BLACK);
	}
	
	@Override
	public void update() {
		isFinished(video.getFinished());
	}
}

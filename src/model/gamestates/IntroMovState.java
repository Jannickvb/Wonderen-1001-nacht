package model.gamestates;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import control.ControlManager;
import control.VideoHandler;

public class IntroMovState extends GameState{

	private EmbeddedMediaPlayerComponent ourMediaPlayer;
	private VideoHandler video;
	
	public IntroMovState(ControlManager cm) {
		super(cm);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		String userProjectPath = System.getProperty("user.dir");
		String userpath = userProjectPath.replaceAll("\\\\", "/");
		video = new VideoHandler(userpath + "/res/video/Intro.mpg");
		video.run();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setPaint(Color.BLACK);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, cm.getWidth(), cm.getHeight());
		g2.setBackground(Color.BLACK);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

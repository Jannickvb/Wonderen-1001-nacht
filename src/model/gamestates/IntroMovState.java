package model.gamestates;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;
import javax.swing.Timer;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import control.ControlManager;
import control.VideoHandler;

public class IntroMovState extends GameState{

	private EmbeddedMediaPlayerComponent ourMediaPlayer;
	private VideoHandler video;
	private boolean finished;
	private Timer update;
	
	public IntroMovState(ControlManager cm) {
		super(cm);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		String userProjectPath = System.getProperty("user.dir");
		String userpath = userProjectPath.replaceAll("\\\\", "/");
		video = new VideoHandler(userpath + "/resources/video/Intro.mpg");
		try {
			cm.playIntroVoice();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		update = new Timer(1000/20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isFinished(video.getFinished());
			}
		});
		update.start();
		video.run();
	}
	
	public void isFinished(boolean finished){
		if(finished){
			cm.getGameStateManager().next();
			update.stop();
		}
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

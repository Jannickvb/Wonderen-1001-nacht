package model.gamestates;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import control.ControlManager;
import control.VideoHandler;

public class IntroMovState extends GameState{

	private EmbeddedMediaPlayerComponent ourMediaPlayer;
	
	
	public IntroMovState(ControlManager cm) {
		super(cm);
		new VideoHandler("E:/Documenten/Eclipse Workspace/Wonderen-1001-nacht/res/video/Intro.mpg").run();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

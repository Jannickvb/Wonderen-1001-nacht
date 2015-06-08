package model.gamestates;


import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;

import control.ControlManager;
import control.VideoHandler;

public class OutroMovState extends GameState{

	private VideoHandler video;
	
	public OutroMovState(ControlManager cm) {
		super(cm);
	}
	
	public void startOutro()
	{
		EndState end = (EndState)cm.getGameStateManager().getState(cm.getGameStateManager().getIndex());
		
		String userpath = System.getProperty("user.dir").replaceAll("\\\\", "/");
		video = cm.getVideoHandler();
		if(end.getIsRich())
		{
			video.handleVideo(userpath + "/resources/video/richKid.mp4");
		}
		else
		{
			video.handleVideo(userpath + "/resources/video/poorKid.mp4");
		}
				
		video.run();
	}
	
	@Override
	public void init() {
		startOutro();
		
	}
	
	public void isFinished(boolean finished){
		if(finished){
			cm.getGameStateManager().select(0);
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

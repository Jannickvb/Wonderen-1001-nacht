package model.gamestates;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import control.ControlManager;

public class MenuState extends GameState{

	private boolean pl1Ready,pl2Ready;
	private int width,height;
	private Image background;
	private Image readyCheck;
	public MenuState(ControlManager cm) {
		super(cm);
		width = cm.getWidth();
		height = cm.getHeight();
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(background, null,null);
		if(pl1Ready)
			g2.drawImage(background, 20, 20,null);
		if(pl2Ready)
			g2.drawImage(background, 40,40, null);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
	
	public void setPlayerStatus(boolean p1,boolean p2){
		pl1Ready = p1;
		pl2Ready = p2;
		if(pl1Ready && pl2Ready)
			cm.getGameStateManager().select(1);
	}
}

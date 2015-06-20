package model.gamestates.story;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class EndState extends GameState {
    private int scale = 1;
    private int midX,midY,bgWidth,bgHeight;
    private Image background;
	
	public EndState(ControlManager cm) {
			super(cm);
			background = ImageHandler.getImage(ImageType.endChoice);
			midX = ControlManager.screenWidth/2;
			midY = ControlManager.screenHeight/2;
			bgWidth = background.getWidth(null);
			bgHeight = background.getHeight(null);
		}
	
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		AffineTransform tx = new AffineTransform();
		AffineTransform bg = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		bg = tx;
		bg.scale(scale, scale);
		g2.setTransform(bg);
		g2.drawImage(background, -bgWidth/2,-bgHeight/2,null);
	}
	
	@Override
	public void update() {
		boolean pressurePlate1 = cm.getInputHandler().getPressurePlate1(); //Right foot
		boolean pressurePlate2 = cm.getInputHandler().getPressurePlate2(); //Left foot
		boolean pressurePlate3 = cm.getInputHandler().getPressurePlate3(); //Right foot
		boolean pressurePlate4 = cm.getInputHandler().getPressurePlate4(); //Left foot
		if(pressurePlate1 && pressurePlate3 && !pressurePlate2 && !pressurePlate4) { // Go to the rights
			
				cm.getGameStateManager().next();
		}
		else if(!pressurePlate1 && !pressurePlate3 && pressurePlate2 && pressurePlate4) {// Go to the left
			cm.getScoreHandler().bossScore += 2500;
			cm.getGameStateManager().next();
		}
		
	}
	
	public void init(boolean arm) {
		// TODO Auto-generated method stub
		
	}
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) { // Go to the rights
			cm.getScoreHandler().geldGehouden = true;
			cm.getGameStateManager().next();
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {// Go to the left
			cm.getScoreHandler().geldGehouden = false;
			cm.getGameStateManager().next();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}

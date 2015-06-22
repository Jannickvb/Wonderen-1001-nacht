package model.gamestates.story;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;
import control.ScoreHandler;

public class EndState extends GameState {
	private BufferedImage background,poor,sign;
	private boolean fadeIn= true,fadeOut = false,choiceMade = false,choicePoor = false;
	private int frame = 0,yPos = -500;
	private float opacity = 1f;
	private Rectangle2D tpRect,tpRect2,fade;
	
	public EndState(ControlManager cm) {
			super(cm);
			this.frame = 0;
			background = ImageHandler.getImage(ImageHandler.ImageType.end_bg);
			poor = ImageHandler.getImage(ImageHandler.ImageType.end_poor);
			sign = ImageHandler.getImage(ImageHandler.ImageType.end_sign);
			
			tpRect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
			tpRect2 = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
			fade = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		}
	
	
	@Override
	public void draw(Graphics2D g2) {
	
		AffineTransform tx = new AffineTransform();
		tx.translate(0, 0);
		g2.setTransform(tx);
		g2.drawImage(background,0,0,null);
	    
	    g2.drawImage(poor,0,0,null);	    
	    g2.drawImage(sign, 0, yPos, null);
	    if(fadeIn || fadeOut)
	    {
	    	g2.setColor(new Color(0,0,0,opacity));
	    	g2.fill(fade);
	    	g2.draw(fade);
	    }
		
	}
	
	@Override
	public void update() {
		boolean pressurePlate1 = cm.getInputHandler().getPressurePlate1(); //Right foot
		boolean pressurePlate2 = cm.getInputHandler().getPressurePlate2(); //Left foot
		boolean pressurePlate3 = cm.getInputHandler().getPressurePlate3(); //Right foot
		boolean pressurePlate4 = cm.getInputHandler().getPressurePlate4(); //Left foot
		
		frame++;
		if(yPos < 0){
			yPos+=8;
		}else{
			yPos = 0;
		}
		if(frame<120 && opacity>0.05f){
			opacity-=(0.1/4);
		}else if(!fadeOut){
			fadeIn = false;
			opacity = 0;
		}
		if(choiceMade && opacity < 0.95f){
			opacity += (0.1/4);
			fadeOut = true;
		}else if(choiceMade && opacity > 0.93f){
			if(choicePoor){
				//input audio
				
				try {
					cm.getInputHandler().enableTrollTalk();    		
					cm.playOhNee();
			            		
							} catch (LineUnavailableException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				cm.getScoreHandler().setScore(cm.getScoreHandler().getScore()+2500);
//				cm.getInputHandler().resetLedStrip(); 
				cm.getGameStateManager().next();
			}else{
				//input audio
				
				try {
//					cm.getInputHandler().enableTrollTalk();     		
					cm.playEvilLaugh();
							} catch (LineUnavailableException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//				cm.getInputHandler().resetLedStrip(); 
				cm.getGameStateManager().next();
			}
		}
//		if((pressurePlate1&&pressurePlate2)&&frame>1080){//frame > duur audio in sec * 60
//			choiceMade = true;
//			choicePoor = false;
//		}
//		if((pressurePlate3&&pressurePlate4)&&frame>1080){//frame > duur audio in sec * 60
//			choiceMade = true;
//			choicePoor = true;
//		}
		
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
			cm.getScoreHandler().setKeptMoney(true);
			cm.getGameStateManager().next();
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {// Go to the left
			cm.getScoreHandler().setKeptMoney(true);
			cm.getGameStateManager().next();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
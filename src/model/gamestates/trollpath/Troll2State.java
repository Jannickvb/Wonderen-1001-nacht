package model.gamestates.trollpath;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;

public class Troll2State extends GameState{
	private Image image;
	private int width,height,midX,midY,bgWidth,bgHeight,counter;
	private float alpha;
	private boolean end;
	
	public Troll2State(ControlManager cm)
	{
		super(cm);
		this.alpha = 0;
		this.counter = 0;
		image = ImageHandler.getImage(ImageHandler.ImageType.troll);
		bgWidth = image.getWidth(null);
		bgHeight = image.getHeight(null);
		
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(image, -bgWidth/2,-bgHeight/2,null);
		
		//Fade out
		Shape rect = new Rectangle2D.Double(-bgWidth/2,-bgHeight/2,ControlManager.screenWidth,ControlManager.screenHeight);
		g2.setColor(new Color(0,0,0,alpha));
		g2.fill(rect); 
	}

	@Override
	public void update() {
		if(end){
			if(alpha < 0.9f)
				alpha += 0.02;
			else
				alpha = 0.99999999f;
		}
	}

	private void setEnd(boolean bool){
		this.end = bool;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(image.equals(ImageHandler.getImage(ImageHandler.ImageType.troll))){
		try {
			cm.playTrollTalk2();
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	setEnd(true);
			            	new java.util.Timer().schedule( 
			    			        new java.util.TimerTask() {
			    			            @Override
			    			            public void run() {
			    			                cm.getGameStateManager().next();
			    			            }
			    			        }, 
			    			        2000
			    			);
			            }
			        }, 
			        9500
			);
			
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

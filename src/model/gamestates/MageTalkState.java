package model.gamestates;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import control.ControlManager;
import control.ImageHandler;

public class MageTalkState extends GameState{
	
	private Image image;
	private int midX,midY,bgWidth,bgHeight,counter;
	
	public MageTalkState(ControlManager cm)
	{
		super(cm);
		this.counter = 0;
		image = ImageHandler.getImage(ImageHandler.ImageType.mage1);
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
	}

	@Override
	public void update() {

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(image.equals(ImageHandler.getImage(ImageHandler.ImageType.mage1))){
		try {
			
				cm.playMusic1();
				cm.playIntroWizard();
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				                cm.getGameStateManager().next();
				            }
				        }, 
				        12000 
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


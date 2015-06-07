package model.gamestates;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import control.ControlManager;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class MageTalkState2 extends GameState{
	
	private Image tutorial;
	private int midX,midY,bgWidth,bgHeight,counter;
	
	public MageTalkState2(ControlManager cm)
	{
		super(cm);
		this.counter = 0;
		tutorial = ImageHandler.getScaledImage(ImageHandler.getImage(ImageType.mage2));
		bgWidth = tutorial.getWidth(null);
		bgHeight = tutorial.getHeight(null);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(tutorial, -bgWidth/2,-bgHeight/2,null);
	}

	@Override
	public void update() {
		counter++;
		if(counter > 300)
		{
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void init() {
		if(tutorial.equals(ImageHandler.getImage(ImageHandler.ImageType.mage1))){
		try {
			cm.arrivalVoice();
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


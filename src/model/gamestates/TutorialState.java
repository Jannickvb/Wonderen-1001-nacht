package model.gamestates;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import control.ControlManager;
import control.ImageHandler;

public class TutorialState extends GameState{
	
	private Image tutorial;
	private int width,height,midX,midY,bgWidth,bgHeight,counter;
	
	public TutorialState(ControlManager cm, Image image)
	{
		super(cm);
		this.counter = 0;
		this.tutorial = image;
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
		width = cm.getWidth();
		height = cm.getHeight();
		bgWidth = tutorial.getWidth(null);
		bgHeight = tutorial.getHeight(null);
		midX = width/2;
		midY = height/2;
		counter++;
		if(counter > 300)
		{
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(tutorial.equals(ImageHandler.getImage(ImageHandler.ImageType.tutorial_plate))){
		try {
			cm.stopIntroVoice();
			cm.playBoatTutorialVoice();
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

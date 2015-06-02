package model.gamestates;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import org.imgscalr.Scalr;

import control.ControlManager;
import control.ImageHandler;

public class TutorialState extends GameState{
	
	private BufferedImage tutorial,scaledTut;
	private int width,height,midX,midY,bgWidth,bgHeight,counter;
	private boolean hasScaled;
	private Scalr.Mode mode;
	public TutorialState(ControlManager cm, Image image)
	{
		super(cm);
		this.counter = 0;
		this.tutorial = (BufferedImage)image;
		this.hasScaled = false;
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(scaledTut, -bgWidth/2,-bgHeight/2,null);
	}

	@Override
	public void update() {
		width = cm.getWidth();
		height = cm.getHeight();
		if(width != 0 && !hasScaled)
		{
			tutorial = ImageHandler.getImage(ImageHandler.ImageType.tutorial_plate);
			mode = ImageHandler.getScale((BufferedImage)tutorial);
			scaledTut = Scalr.resize(tutorial, mode, width, Scalr.OP_ANTIALIAS);
			hasScaled = true;
		}
		bgWidth = scaledTut.getWidth(null);
		bgHeight = scaledTut.getHeight(null);
		midX = width/2;
		midY = height/2;
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

package model.gamestates.trollpath;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;

public class City2State extends GameState{
	private Image image;
	private int width,height,midX,midY,bgWidth,bgHeight,counter;
	
	public City2State(ControlManager cm)
	{
		super(cm);
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
	}

	@Override
	public void update() {
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(image.equals(ImageHandler.getImage(ImageHandler.ImageType.troll))){
		try {
			cm.playMusic1();
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

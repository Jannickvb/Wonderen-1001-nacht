package model.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import view.Main;
import control.ControlManager;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class Coin extends Entity {
 
	private Clip coinPickup;
	
	public Coin(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageType.coin));
		//Importing sound: 
			AudioInputStream inputStream;
				try {
					inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("/audio/coinPickup2.wav"));
					coinPickup = AudioSystem.getClip();
					coinPickup.open(inputStream);
				} catch (Exception e) {
					e.printStackTrace();
				}	
	}

	/**
	 * Drawing the coin
	 * @parma g2 - The Graphics2D object.
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),getPositionX(),getPositionY(),null);
	}

	@Override
	public void update() {
		positionY += 6;
		if(positionY > ControlManager.screenHeight+50) 
			setDead(true);
	}

	@Override
	public void init() {
		positionY = -50;
		positionX = ((ControlManager.screenWidth/4)+ 135) +(int)  Math.floor(Math.random()*(ControlManager.screenWidth/2-270-getSprite().getWidth()));
	}
	
	/**
	 * Play the pickup sound.
	 */
	public void playSound() {
		coinPickup.start();
	}
}
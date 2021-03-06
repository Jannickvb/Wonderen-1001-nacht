package model.entities;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import view.Main;
import control.ControlManager;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class Upgrade extends Entity {
 
	private int animationCounter;
	private boolean move;
	private Clip coinPickup;
	
	public Upgrade(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageType.upgrade));
		animationCounter = 0;
		//Importing sound: 
			AudioInputStream inputStream;
				try {
					inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("/audio/coinPickup.wav"));
					coinPickup = AudioSystem.getClip();
					coinPickup.open(inputStream);
				} catch (Exception e) {
					e.printStackTrace();
				}	
	}

	/**
	 * Drawing the upgrade-coin.
	 * @param g2 - The Graphics2D object.
	 */
	@Override
	public void draw(Graphics2D g2) {
		BufferedImage subImage = getSprite().getSubimage((animationCounter)%8*64,((animationCounter)/8)%8*64,64,64);
		g2.drawImage(subImage,getPositionX(),getPositionY(),null);
	}

	@Override
	public void update() {
		if(move)
			positionY += getSpeed();
		animationCounter++;
		if(positionY > ControlManager.screenHeight+50) 
			setDead(true);
	}

	@Override
	public void init() {
		positionY = -50;
		positionX = ((ControlManager.screenWidth/4)+ 135) +(int)  Math.floor(Math.random()*(ControlManager.screenWidth/2-270-getSprite().getWidth()));
		move = true;
	}
	
	/**
	 * The bounds of the entity.
	 * @return The rectangle that defines the outer space of the sprite.
	 */
	@Override
	public Rectangle2D getRectangle() {
		return  new Rectangle2D.Double(positionX,positionY,64,64);
	}
	
	/**
	 * Checks if one of the pixels is inside the boats body.
	 * @param object - the object you want to check for collision.
	 * @return if there is an intersection between the two objects.
	 */
	@Override
	public boolean containsPoint(Entity object) {
		Shape ownShape =  new Rectangle2D.Double(positionX+40,positionY,64,64);
		Rectangle2D objectRectangle = object.getRectangle();
		if(ownShape.intersects(objectRectangle)) 
			return true;
		else
			return false;
	}
	
	/**
	 * Set weather or not the coin should move.
	 * @param state
	 */
	public void setMove(boolean state) {
		move = state;
	}
	
	/**
	 * Play the pickup sound.
	 */
	public void playSound() {
		coinPickup.start();
	}
}
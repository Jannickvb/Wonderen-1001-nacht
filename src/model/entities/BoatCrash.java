package model.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import view.Main;
import control.ControlManager;
import control.ImageHandler;
import control.ImageHandler.ImageType;

/**
 * This class animates the crash of the ship object.
 * @author Wesley de Hek
 * @version 1.3
 */
public class BoatCrash extends Entity {

	private boolean move;
	private int keyFrame;
	
	/**
	 * Constructor for the crash animation.
	 * @param cm - The control manager object of the game.
	 * @param positionX - The x coordinate of the start position.
	 * @param positionY - The y coordinate of the start position.
	 */
	public BoatCrash(ControlManager cm, int positionX, int positionY, boolean move) {
		super(cm,ImageHandler.getImage(ImageType.player_boat));
		this.positionX = positionX;
		this.positionY = positionY;
		this.move = move;
		//Importing sound: 
		AudioInputStream inputStream;
		try {
			inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("/audio/boatCrash.wav"));
			Clip crashClip = AudioSystem.getClip();
			crashClip.open(inputStream);
			crashClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Draws the animation of the crash.
	 */
	@Override
	public void draw(Graphics2D g2) {
		BufferedImage subImage = getSprite().getSubimage(0*128,0,128,193);
		g2.drawImage(subImage,getPositionX(),getPositionY(),null);
	}

	/**
	 * Update method for the animation object.
	 */
	@Override
	public void update() {
		keyFrame++;
		if(keyFrame > 60)
			setDead(true);
		if(move)
			if(!isDead())
				positionY += getSpeed();
	}

	/**
	 * Init method for the crash animation object.
	 */
	@Override
	public void init() {
	}
}
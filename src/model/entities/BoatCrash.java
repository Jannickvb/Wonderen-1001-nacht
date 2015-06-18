package model.entities;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

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

	private Clip crashClip;
	
	/**
	 * Constructor for the crash animation.
	 * @param cm - The control manager object of the game.
	 * @param positionX - The x coordinate of the start position.
	 * @param positionY - The y coordinate of the start position.
	 */
	public BoatCrash(ControlManager cm, int positionX, int positionY) {
		super(cm,ImageHandler.getImage(ImageType.player_boat));
		this.positionX = positionX;
		this.positionY = positionY;
		//Importing sound: 
		AudioInputStream inputStream;
				try {
					inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("/audio/boatCrash.wav"));
					crashClip = AudioSystem.getClip();
					crashClip.open(inputStream);
				} catch (Exception e) {
					e.printStackTrace();
				}	
				crashClip.start();
		//Starting timer.		
		Timer playTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					setDead(true);
				}
			});
			playTimer.start();
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
		if(!isDead())
			positionY += 6;
	}

	/**
	 * Init method for the crash animation object.
	 */
	@Override
	public void init() {
	}
}
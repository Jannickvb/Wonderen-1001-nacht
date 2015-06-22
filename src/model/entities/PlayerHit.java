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
 * PlayerHit class.
 * @author Tim Schijvenaars
 * @version 1.3
 */
public class PlayerHit extends Entity {

		private boolean move;
		private int keyFrame;
		
		public PlayerHit(ControlManager cm, int positionX, int positionY, boolean move) {
			super(cm,ImageHandler.getImage(ImageType.player_run));
			this.positionX = positionX;
			this.positionY = positionY;
			this.move = move;
			//Importing sound: 
			AudioInputStream inputStream;
			Clip crashClip = null;
			switch((int) Math.floor(Math.random()*2)) {
			case 0:
				try {
					inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("/audio/Pain1.wav"));
					crashClip = AudioSystem.getClip();
					crashClip.open(inputStream);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 1:
				try {
					inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("/audio/Pain2.wav"));
					crashClip = AudioSystem.getClip();
					crashClip.open(inputStream);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			crashClip.start();
		}
	
		/**
		 * Draws the animation of the crash.
		 */
		@Override
		public void draw(Graphics2D g2) {
			BufferedImage subImage = getSprite().getSubimage(0*23,0,23,29);
			g2.drawImage(subImage,getPositionX(),getPositionY(),null);
		}

		/**
		 * Update method for the animation object.
		 * Checks if the crash sound is done playing, if yes the object gets set to dead.
		 */
		@Override
		public void update() {
			keyFrame++;
			if(keyFrame < 60)
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
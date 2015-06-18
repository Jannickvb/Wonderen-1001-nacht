	package model.entities;

	import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

import view.Main;
import control.ControlManager;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class PlayerHit extends Entity {


		private Clip crashClip;
		
		public PlayerHit(ControlManager cm, int positionX, int positionY) {
			super(cm,ImageHandler.getImage(ImageType.player_run));
			this.positionX = positionX;
			this.positionY = positionY;
			//Importing sound: 
					AudioInputStream inputStream;

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
			BufferedImage subImage = getSprite().getSubimage(0*23,0,23,29);
			g2.drawImage(subImage,getPositionX(),getPositionY(),null);
		}

		/**
		 * Update method for the animation object.
		 * Checks if the crash sound is done playing, if yes the object gets set to dead.
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

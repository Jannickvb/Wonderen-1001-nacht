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

public class BoatCrash extends Entity implements ActionListener {

	private Clip crashClip;
	
	public BoatCrash(ControlManager cm, int positionX, int positionY) {
		super(cm,ImageHandler.getImage(ImageType.player_boat));
		this.positionX = positionX;
		this.positionY = positionY;
		//Importing sound: 
				AudioInputStream inputStream;
				try {
					inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("/audio/crash.wav"));
					crashClip = AudioSystem.getClip();
					crashClip.open(inputStream);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				crashClip.start();
	}

	@Override
	public void draw(Graphics2D g2) {
		//Drawing ship:
		BufferedImage subImage = getSprite().getSubimage(0*128,0,128,193);
		g2.drawImage(subImage,getPositionX(),getPositionY(),null);
		
	}

	@Override
	public void update() {
		if(!crashClip.isRunning())
			setDead(true);		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

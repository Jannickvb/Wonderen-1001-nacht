package model.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import control.ControlManager;
import control.ImageHandler;
import control.InputHandler;

public class Player extends Entity implements ActionListener {

	private int screenWidth;
	private InputHandler input;
	private int animationCounter;
	
	public Player(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageHandler.ImageType.boat));
		input = cm.getInputHandler();
		animationCounter = 0;
		Timer animationTimer = new Timer(350,this);
		animationTimer.start();
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage subImage = getSprite().getSubimage((animationCounter%3)*128,0,128,193);
		g2.drawImage(subImage,getPositionX(),getPositionY(),null);
	}
	
	public void update() {
		boolean pressurePlate1 = input.getPressurePlate1(); //Right foot
		boolean pressurePlate2 = input.getPressurePlate2(); //Left foot
		boolean pressurePlate3 = input.getPressurePlate3(); //Right foot
		boolean pressurePlate4 = input.getPressurePlate4(); //Left foot
		if(pressurePlate1 && pressurePlate3 && !pressurePlate2 && !pressurePlate4) { // Go to the right
			if(positionX <= screenWidth/4*3-130) {
				positionX += 13;
			}	
		}
		else if(!pressurePlate1 && !pressurePlate3 && pressurePlate2 && pressurePlate4) {// Go to the left
			if(positionX > screenWidth/4+130)
				positionX -= 13;
		}
	}
	
	public void init() {
		screenWidth = cm.getWidth();
		positionX = cm.getWidth()/2;
		positionY = cm.getHeight()-250;
		input.turnPressurePlates(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		animationCounter++;
	}
}
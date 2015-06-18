package model.entities;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import control.ControlManager;

public class Box extends Entity {

	/**
	 * Constructor of the Box object.
	 * @param cm - The control manager of the game.
	 * @param boxImage - The sprite for the box.
	 */
	public Box(ControlManager cm, BufferedImage boxImage) {
		super(cm,boxImage);
	}
	
	/**
	 * Draws the box on the screen.
	 */
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);
	}

	/**
	 * Update method for the Box object.
	 * The box gets set to dead when it's outside the screen.
	 */
	@Override
	public void update() {
		if(positionY > ControlManager.screenHeight) 
			setDead(true);
	}

	/**
	 * Init method for the Box object.
	 * Sets the Box's start position.
	 */
	@Override
	public void init() {
		positionY = -50;
		positionX = ((ControlManager.screenWidth/4)+ 135) +(int)  Math.floor(Math.random()*(ControlManager.screenWidth/2-270-getSprite().getWidth()));
	}
}

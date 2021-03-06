package model.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import control.ControlManager;

/**
 * Box class.
 * @author Tim Schijvenaars
 * @Version 1.1;
 */
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
		positionY += getSpeed();
		if(positionY > ControlManager.screenHeight+50) 
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

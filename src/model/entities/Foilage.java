package model.entities;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import control.ControlManager;
import control.ImageHandler;

/**
 * Tree class.
 * @author Tim Schijvenaars
 * @version 1.0
 */
public class Foilage extends Entity {

	/**
	 * Constructor for the Foilage object.
	 * @param cm - The Control manager of the game.
	 * @param foilageImage - The sprite for the Foilage.
	 */
	public Foilage(ControlManager cm, BufferedImage foilageImage) {
		super(cm,foilageImage);
	}
	
	/**
	 * Draws the Foilage.
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);
	}

	/**
	 * Update method for the Foilage object.
	 * The foilage gets set to dead when it's outside the screen.
	 */
	@Override
	public void update() {
		if(positionY > ControlManager.screenHeight) 
			setDead(true);
	}

	/**
	 * Init method for the Foilage object.
	 * Sets the Foilage's start position.
	 */
	@Override
	public void init() {
		positionY = -50;
		switch((int) Math.floor(Math.random()*4)) {
		case 0:
			positionX = ((int)Math.floor(Math.random()*((ControlManager.screenWidth/4+80)-getSprite().getWidth())));
			break;
		case 1:
			positionX = ((ControlManager.screenWidth/4*3) + (int)Math.floor(Math.random()*(ControlManager.screenWidth-getSprite().getWidth())));
			break;
		case 2:
			positionX = ((int)Math.floor(Math.random()*((ControlManager.screenWidth/4+80)-getSprite().getWidth())));
			break;
		case 3:
			positionX = ((ControlManager.screenWidth/4*3+230) + (int)Math.floor(Math.random()*(ControlManager.screenWidth-getSprite().getWidth())));
			break;
		}
	}
}
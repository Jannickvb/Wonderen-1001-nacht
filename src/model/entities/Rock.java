package model.entities;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import control.ControlManager;

/**
 * The Rock class.
 * @author Wesley de Hek
 * @version 1.0
 */
public class Rock extends Entity {

	/**
	 * Constructor of the Rock object.
	 * @param cm - The control manager of the game.
	 * @param rockImage - The sprite for the rock.
	 */
	public Rock(ControlManager cm, BufferedImage rockImage) {
		super(cm,rockImage);
	}
	
	/**
	 * Draws the rock on the screen.
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);
	}

	/**
	 * Update method for the Rock object.
	 * The rock gets set to dead when it's outside the screen.
	 */
	@Override
	public void update() {
		positionY += getSpeed();
		if(positionY > ControlManager.screenHeight+50) 
			setDead(true);
	}

	/**
	 * Init method for the Rock object.
	 * Sets the Rock's start position.
	 */
	@Override
	public void init() {
		positionY = -50;
		positionX = ((ControlManager.screenWidth/4)+ 135) +(int)  Math.floor(Math.random()*(ControlManager.screenWidth/2-270-getSprite().getWidth()));
	}
}
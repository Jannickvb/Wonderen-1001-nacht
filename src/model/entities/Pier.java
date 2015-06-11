package model.entities;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import control.ControlManager;
import control.ImageHandler;
import control.ImageHandler.ImageType;

/**
 * The pier class.
 * @author Wesley de Hek
 * @version 1.1
 */
public class Pier extends Entity {
	
	/**
	 * Constructor of the Pier object.
	 * @param cm - The control manager of the game.
	 * @param positionY - The y start position of the pier.
	 */
	public Pier(ControlManager cm, int positionY) {
		super(cm,ImageHandler.getImage(ImageType.pier));
		this.positionY = positionY-getSprite().getHeight();
		this.positionX = (ControlManager.screenWidth/2)-(getSprite().getWidth()/2)-45;
	}

	/**
	 * Draws the pier on the screen.
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);	
	}

	/**
	 * Update method for the Pier object.
	 * The piers stops moving when its fully displayed.
	 */
	@Override
	public void update() {
		if(positionY >= -20 && positionY <= -5) {
			setDead(true);
			setTimer(false);
		}
	}
	
	/**
	 * Init method for the Pier object.
	 */
	@Override
	public void init() {
	}
}

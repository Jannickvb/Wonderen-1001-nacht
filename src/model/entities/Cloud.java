package model.entities;

import java.awt.Graphics2D;

import control.ControlManager;
import control.ImageHandler;

/**
 * Cloud.
 * @author Wesley de Hek
 * @Version 1.2
 */
public class Cloud extends Entity {

	/**
	 * Constructor.
	 * @param cm - The Control manager of the game.
	 */
	public Cloud(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageHandler.ImageType.cloud));
	}

	/**
	 * Drawing the cloud.
	 * @param g2 - The Graphics2D object.
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);
	}

	/**
	 * Update the position of the cloud.
	 */
	@Override
	public void update() {
		positionY += getSpeed()/2;
	}

	/**
	 * Initialize the cloud.
	 * Setting it's x and y position.
	 */
	@Override
	public void init() {
		//Setting x-position:
		switch((int)Math.floor(Math.random()*2)) {
			case 0: 
				positionX = (int) (Math.floor(Math.random()*ControlManager.screenWidth/4))-100;
				break;
			case 1: 
				positionX = ((((ControlManager.screenWidth/4)*3)+100) + (int) Math.floor(Math.random()*ControlManager.screenWidth/4))-200;
				break;
		}
		//Setting the y-position: 
		positionY = -200;
	}
}
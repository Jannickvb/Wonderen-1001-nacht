package model.entities;

import java.awt.Graphics2D;

import control.ControlManager;
import control.ImageHandler;

public class Cloud extends Entity {

	private boolean goRight;
	
	public Cloud(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageHandler.ImageType.cloud));
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);
	}

	@Override
	public void update() {
		positionY += 3;
	}

	@Override
	public void init() {
		//Setting x-position:
		switch((int)Math.floor(Math.random()*2)) {
			case 0: 
				positionX = (int) Math.floor(Math.random()*ControlManager.screenWidth/4);
				break;
			case 1: 
				positionX = (((ControlManager.screenWidth/4)*3)+100) + (int) Math.floor(Math.random()*ControlManager.screenWidth/4);
				goRight = false;
				break;
		}
		//Setting the y-position: 
		positionY = -100;
	}
}

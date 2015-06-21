package model.entities;

import java.awt.Graphics2D;

import control.ControlManager;
import control.ImageHandler;

public class Cloud extends Entity {

	public Cloud(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageHandler.ImageType.cloud));
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}

package model.entities;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import control.ControlManager;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class Pier extends Entity implements ActionListener {

	public Pier(ControlManager cm, int positionY) {
		super(cm,ImageHandler.getImage(ImageType.pier));
		this.positionY = positionY-getSprite().getHeight();
		positionX = (ControlManager.screenWidth/2)-(getSprite().getWidth()/2)-45;
		Timer timer = new Timer(1000/60,this);
		timer.start();
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);	
	}

	@Override
	public void update() {
		if(positionY > ControlManager.screenHeight)
			setDead(true);
	}

	@Override
	public void init() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		positionY+=6;
	}
}

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

public class Foilage extends Entity implements ActionListener {

	public Foilage(ControlManager cm, BufferedImage rockImage) {
		super(cm,rockImage);
		Timer moveTimer = new Timer(1000/60,this);
		moveTimer.start();
	}
	

	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);
	}

	@Override
	public void update() {
		if(positionY > cm.getHeight()) 
			setDead(true);
	}

	@Override
	public void init() {
		positionY = 0;
		switch((int) Math.floor(Math.random()*4)) {
		case 0:
			positionX = ((int)Math.floor(Math.random()*((cm.getWidth()/4+80)-getSprite().getWidth())));
			break;
		case 1:
			positionX = ((cm.getWidth()/4*3) + (int)Math.floor(Math.random()*(cm.getWidth()-getSprite().getWidth())));
			break;
		case 2:
			positionX = ((int)Math.floor(Math.random()*((cm.getWidth()/4+80)-getSprite().getWidth())));
			break;
		case 3:
			positionX = ((cm.getWidth()/4*3) + (int)Math.floor(Math.random()*(cm.getWidth()-getSprite().getWidth())));
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		positionY+=6;
	}
}
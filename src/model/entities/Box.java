package model.entities;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import control.ControlManager;

public class Box extends Entity implements ActionListener {

	public Box(ControlManager cm, BufferedImage rockImage) {
		super(cm,rockImage);
		Timer moveTimer = new Timer(1000/60,this);
		//moveTimer.start();
	}
	

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
		positionY = 0;
		positionX = ((ControlManager.screenWidth/4)+ 140) +(int)  Math.floor(Math.random()*(ControlManager.screenWidth/2-270-getSprite().getWidth()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		positionY+=3;
	}
}

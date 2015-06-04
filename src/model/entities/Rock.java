package model.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

import control.ControlManager;
import control.ImageHandler;

public class Rock extends Entity implements ActionListener {

	public Rock(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageHandler.ImageType.rock));
		Timer moveTimer = new Timer(1000/60,this);
		moveTimer.start();
	}
	

	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(),positionX,positionY,null);
	}

	@Override
	public void update() {
	}

	@Override
	public void init() {
		positionY = 0;
		positionX = (cm.getWidth()/4)+((int) Math.floor(Math.random()*cm.getWidth()/2));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		positionY+=6;
	}

}

package model.entities;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import control.ControlManager;

public class Rock extends Entity implements ActionListener {

	public Rock(ControlManager cm, BufferedImage rockImage) {
		super(cm,rockImage);
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
		positionX = ((ControlManager.screenWidth/4)+ 135) +(int)  Math.floor(Math.random()*(ControlManager.screenWidth/2-270-getSprite().getWidth()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		positionY+=6;
	}
	
	/**
	 * Checks if one of the pixels is inside the boats body.
	 * @param boat - the boat you want to check with.
	 * @return if there is an intersection between the two objects.
	 */
	public boolean containsPoint(Player boat) {
		Shape ownShape = new Rectangle2D.Double(positionX,positionY,getSprite().getWidth(),getSprite().getHeight());
		Rectangle2D boatShape = boat.getRectangleBounds();
		if(ownShape.intersects(boatShape)) 
			return true;
		else
			return false;
	}

}

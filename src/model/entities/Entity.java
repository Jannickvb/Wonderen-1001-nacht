package model.entities;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import control.ControlManager;

public abstract class Entity {

	protected int positionX, positionY;
	protected ControlManager cm;
	protected boolean isDead;
	private BufferedImage sprite;
	private Timer moveTimer;
	
	public Entity(ControlManager cm, BufferedImage sprite) {
		this.setCm(cm);
		this.sprite = sprite;
		moveTimer = new Timer(1000/60,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				positionY += 6;
			}
		});
		moveTimer.start();
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public ControlManager getCm() {
		return cm;
	}

	public void setCm(ControlManager cm) {
		this.cm = cm;
	}

	public BufferedImage getSprite() {
		return sprite;
	}
	
	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public void setTimer(boolean state) {
		if(state)
			moveTimer.start();
		else
			moveTimer.stop();
	}
	
	public Rectangle2D getRectangle() {
		return  new Rectangle2D.Double(positionX,positionY,getSprite().getWidth(),getSprite().getHeight());
	}
	
	public abstract void draw(Graphics2D g2);
	
	public abstract void update();
	
	public abstract void init();
}

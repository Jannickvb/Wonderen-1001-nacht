package model.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import control.ControlManager;

public abstract class Entity {

	protected int positionX, positionY;
	protected ControlManager cm;
	private BufferedImage sprite;
	
	
	public Entity(ControlManager cm, BufferedImage sprite) {
		this.setCm(cm);
		this.sprite = sprite;
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
	
	public abstract void draw(Graphics2D g2);
	
	public abstract void update();
	
	public abstract void init();
}
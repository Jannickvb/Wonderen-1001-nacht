package model.entities;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import control.ControlManager;
/**
 * Abstract class for all the entities.
 * @author Wesley de Hek
 * @version 1.3
 */
public abstract class Entity {

	protected int positionX, positionY;
	private boolean isDead;
	private ControlManager cm;
	private BufferedImage sprite;
	
	/**
	 * Constructor.
	 * @param cm - The control manager.
	 * @param sprite - The sprite of the entity.
	 */
	public Entity(ControlManager cm, BufferedImage sprite) {
		this.cm = cm;
		this.sprite = sprite;
	}

	/**
	 * The y position of the entity.
	 * @return the y position.
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * Set the y position of the entity to a given value.
	 * @param positionY - The new value of the y position.
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	/**
	 * The y position of the entity.
	 * @return the x position.
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * Set the x position of the entity to a given value.
	 * @param positionX - The new value of the x position.
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * The control manager of the application.
	 * @return The control manager.
	 */
	public ControlManager getCm() {
		return cm;
	}

	/**
	 * The picture of the entity.
	 * @return The sprite that displays the entity.
	 */
	public BufferedImage getSprite() {
		return sprite;
	}
	
	/**
	 * Check if the entity is ready to be disposed.
	 * @return The dead status of the entity.
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * Set the entity's state so it can be disposed.
	 * @param isDead - The new status of the entity.
	 */
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	/**
	 * The bounds of the entity.
	 * @return The rectangle that defines the outer space of the sprite.
	 */
	public Rectangle2D getRectangle() {
		return  new Rectangle2D.Double(positionX,positionY,getSprite().getWidth(),getSprite().getHeight());
	}
	
	/**
	 * Checks if one of the pixels is inside the boats body.
	 * @param object - the object you want to check for collision.
	 * @return if there is an intersection between the two objects.
	 */
	public boolean containsPoint(Entity object) {
		Shape ownShape =  new Rectangle2D.Double(positionX+40,positionY,getSprite().getWidth(),getSprite().getHeight());
		Rectangle2D objectRectangle = object.getRectangle();
		if(ownShape.intersects(objectRectangle)) 
			return true;
		else
			return false;
	}
	
	/**
	 * Abstract draw method.
	 * @param g2 - The graphics 2D object.
	 */
	public abstract void draw(Graphics2D g2);
	
	/**
	 * Abstract update method.
	 */
	public abstract void update();
	
	/**
	 * Abstract init method, gets called on initialisation of the gamestate.
	 */
	public abstract void init();
}

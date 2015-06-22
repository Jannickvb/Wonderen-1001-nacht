package model.entities;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import control.ControlManager;
import control.ImageHandler;
import control.InputHandler;

/**
 * Boat class.
 * @author Wesley de Hek
 * @Version 1.8
 */
public class Boat extends Entity {

	private InputHandler input;
	private int animationCounter;
	private float alpha;
	private boolean reachedEnd;
	private boolean deadMessage;
	private boolean collisionPier;
	private boolean move;
	
	private boolean pressurePlate1; //Right foot
	private boolean pressurePlate2; //Left foot
	private boolean pressurePlate3; //Right foot
	private boolean pressurePlate4; //Left foot
	
	/**
	 * Constructor of the Boat object.
	 * @param cm - The control manager of the game.
	 */
	public Boat(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageHandler.ImageType.player_boat));
		input = cm.getInputHandler();
		animationCounter = 0;
		alpha = 1.0f;
	}
	
	/**
	 * Draws the Boat.
	 * @param g2 - The Graphics2D object.
	 */
	@Override
	public void draw(Graphics2D g2) {
		//Drawing ship:
		if(!isDead()) {
			BufferedImage subImage = getSprite().getSubimage((animationCounter/21)%3*128,0,128,193);
			g2.drawImage(subImage,getPositionX(),getPositionY(),null);
		}
	}
	
	/**
	 * Update method for the Boat object.
	 * Get's pressure plate status and moves the boat.
	 */
	public void update() {
		boolean pressurePlate1 = input.getPressurePlate1(); //Right foot
		boolean pressurePlate2 = input.getPressurePlate2(); //Left foot
		boolean pressurePlate3 = input.getPressurePlate3(); //Right foot
		boolean pressurePlate4 = input.getPressurePlate4(); //Left foot
		if(move) {
			if(pressurePlate1 && pressurePlate3 ) { // Go to the rights
				if( positionY < 178) {
					if(positionX > ControlManager.screenWidth/2-(getSprite().getWidth()/2)-45 && positionX <= ControlManager.screenWidth/4*3-ControlManager.screenWidth/8)
						positionX += 7;
				}
				else if(positionX <= ControlManager.screenWidth/4*3-ControlManager.screenWidth/8) 
					positionX += 7;
					
			}
			else if( pressurePlate2 && pressurePlate4) {// Go to the left
				if( positionY < 178) {
					if(positionX < ControlManager.screenWidth/2+13 && positionX > ControlManager.screenWidth/4+ControlManager.screenWidth/20)
						positionX -= 7;
				}
				else if(positionX > ControlManager.screenWidth/4+ControlManager.screenWidth/20)
					positionX -= 7;
			}
		}
		//Animtating the ship: 
		if(move)
			animationCounter++;	
		//Showing dead message: 
		if(deadMessage) {
			if(alpha > 0.045) {
				if(animationCounter%12 == 0)
					setDead(!isDead());
				alpha -= 0.00375f;
			}
			else {
				deadMessage = false;
				setDead(false);
				alpha = 1.0f;
			}
		}
		
		//To the end:
		if(!reachedEnd) {
			if(!collisionPier) {
				if(positionY > 6)
					positionY -= 6;
				else {
					reachedEnd = true;
					move = false;
					collisionPier = true;
				}
			}
		}
		
	}
	
	/**
	 * Init method for the Boat object.
	 * Sets x and y starting positions, also initializes the pressure plates.
	 */
	public void init() {
		positionX = ControlManager.screenWidth/2;
		positionY = ControlManager.screenHeight - 250;
		collisionPier = true;
		move = true;
		input.turnPressurePlates(true);
	}
	
	/**
	 * Get's called when the ship collides with a Rock object.
	 */
	public void collision() {
		input.deadStageOne();
		deadMessage = true;
	}
	
	/**
	 * Resets the position to start position.
	 */
	public void reset() {
		input.deadStageTwo();
		collisionPier = true;
		reachedEnd = false;
		move = true;
		positionY = ControlManager.screenHeight-250;
		positionX = ControlManager.screenWidth/2;
	}
		
	/**
	 * The top of the screen is reached.
	 * @return if the boat has reached the top of the screen.
	 */
	public boolean reachedEnd() {
		return reachedEnd;
	}
	
	/**
	 * Get the state of the dead message
	 * @return - The state of the dead message.
	 */
	public boolean getDeadMessage() {
		return deadMessage;
	}
	
	/**
	 * Get the fade alpha.
	 * @return - The alpha.
	 */
	public float getAlpha() {
		return alpha;
	}
	
	/**
	 * Sets the reached end.
	 * @param reachedEnd - whether the boat has reached the end or not.
	 */
	public void setReachedEnd(boolean reachedEnd) {
		move = !reachedEnd;
		this.reachedEnd = reachedEnd;
	}
	
	/**
	 * Set the collision with the pier.
	 * @param state - The state of the collision.
	 */
	public void setCollisionPier(boolean state) {
		if(state)
			collisionPier = true;
		else
			collisionPier = false;
	}
	
	/**
	 * Checks if one of the pixels is inside the boats body.
	 * @param object - the object you want to check for collision.
	 * @return if there is an intersection between the two objects.
	 */
	@Override
	public boolean containsPoint(Entity object) {
		Shape boatShape =  new Rectangle2D.Double(positionX+40,positionY,57,173-40);
		Rectangle2D objectRectangle = object.getRectangle();
		if(boatShape.intersects(objectRectangle)) 
			return true;
		else
			return false;
	}
	
	/**
	 * Just for testing:
	 * @param i
	 */
	public void setPressurePlates(int i){
		if(i == 1){
			pressurePlate1 = true;
			pressurePlate3 = true;
		}else if(i == 2){
			pressurePlate2 = true;
			pressurePlate4 = true;
		}else if(i == 3){
			pressurePlate1 = false;
			pressurePlate3 = false;
		}else if(i == 4){
			pressurePlate2 = false;
			pressurePlate4 = false;
		}
	}
}
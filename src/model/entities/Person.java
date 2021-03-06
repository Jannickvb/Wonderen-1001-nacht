package model.entities;

	import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import control.ControlManager;
import control.ImageHandler;
import control.InputHandler;

public class Person extends Entity{
	
	private InputHandler input;
	private int animationCounter;
	private float alpha;
	private boolean reachedEnd;
	private boolean deadMessage;
	private boolean collisionPalace;
	
	private boolean pressurePlate1; //Right foot
	private boolean pressurePlate2; //Left foot
	private boolean pressurePlate3; //Right foot
	private boolean pressurePlate4; //Left foot
		
	/**
	 * Constructor of the Person object.
	 * @param cm - The control manager of the game.
	 */
		public Person(ControlManager cm) {
			super(cm,ImageHandler.getImage(ImageHandler.ImageType.player_run));
			input = cm.getInputHandler();
			animationCounter = 0;
			alpha = 1.0f;
		}
		
		/**
		 * Draws the Person.
		 * @param g2 - The Graphics2D object.
		 */
		@Override
		public void draw(Graphics2D g2) {
			//Drawing Person
			if(!isDead()) {
				BufferedImage subImage = getSprite().getSubimage((animationCounter/9)%3*23,0,23,29);
				g2.drawImage(subImage,getPositionX(),getPositionY(),null);
			}
			//Drawing dead message:
			if(deadMessage) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Verdana",Font.BOLD,60));
				ImageHandler.drawCenteredText("Probeer het opnieuw", g2, ControlManager.screenHeight/2);
			}
		}
		
		/**
		 * Updating the person.
		 */
		public void update() {
			pressurePlate1 = input.getPressurePlate1(); //Right foot
			pressurePlate2 = input.getPressurePlate2(); //Left foot
			pressurePlate3 = input.getPressurePlate3(); //Right foot
			pressurePlate4 = input.getPressurePlate4(); //Left foot
			if(pressurePlate1 && pressurePlate3) { // Go to the right
				if( positionY < 100) {
					if(positionX > ControlManager.screenWidth/2-(getSprite().getWidth()/2)-20)
						positionX += 7;
				}
				else if(positionX <= ControlManager.screenWidth/4*3-ControlManager.screenWidth/8 + 15) 
					positionX += 7;
					
			}
			else if(pressurePlate2 && pressurePlate4) {// Go to the left
				if( positionY < 100) {
					if(positionX < ControlManager.screenWidth/2+13)
						positionX -= 7;
				}
				else if(positionX > ControlManager.screenWidth/4+ControlManager.screenWidth/20+55)
					positionX -= 7;
			}
			//Animation person:
			animationCounter++;
			//Showing deadMessage
			if(deadMessage) {
				if(alpha > 0.045) {
					if(animationCounter%12 == 0)
						setDead(!isDead());
					alpha -= 0.01125f;
				}
				else {
					deadMessage = false;
					setDead(false);
					alpha = 1.0f;
				}
			}
			
			if(!collisionPalace) {
				if(positionY > 6)
					positionY -= 6;
				else {
					reachedEnd = true;
					collisionPalace = true;
				}
			}
		}
	
		/**
		 * Init method for the Person object.
		 * Sets x and y starting positions, also initializes the pressure plates.
		 */
		public void init() {
			positionX = ControlManager.screenWidth/2;
			positionY = ControlManager.screenHeight - 100;
			collisionPalace = true;
			input.turnPressurePlates(true);
		}
		
		/**
		 * Get's called when the Person collides with a Box object.
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
			collisionPalace = true;
			reachedEnd = false;
			positionY = ControlManager.screenHeight-100;
			positionX = ControlManager.screenWidth/2;
		}
			
		/**
		 * The top of the screen is reached.
		 * @return if the person has reached the top of the screen.
		 */
		public boolean reachedEnd() {
			return reachedEnd;
		}
		
		/**
		 * Sets the reached end.
		 * @param reachedEnd - whether the person has reached the end or not.
		 */
		public void setReachedEnd(boolean reachedEnd) {
			this.reachedEnd = reachedEnd;
		}
		
		/**
		 * Set the collision with the palace.
		 * @param state - The state of the collision.
		 */
		public void setCollisionPalace(boolean state) {
			if(state)
				collisionPalace = true;
			else
				collisionPalace = false;
		}
		
		/**
		 * Checks if one of the pixels is inside the persons body.
		 * @param object - the object you want to check for collision.
		 * @return if there is an intersection between the two objects.
		 */
		@Override
		public boolean containsPoint(Entity object) {
			Shape boatShape =  new Rectangle2D.Double(positionX,positionY,23,29);
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
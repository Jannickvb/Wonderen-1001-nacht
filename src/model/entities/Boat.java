package model.entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import control.ControlManager;
import control.ImageHandler;
import control.InputHandler;

/**
 * Boat class.
 * @author Wesley de Hek
 * @Version 1.7
 */
public class Boat extends Entity {

	private InputHandler input;
	private int animationCounter;
	private float alpha;
	private boolean reachedEnd;
	private Timer deadMessageTimer;
	private Timer endTimer;
	
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
		endTimer = new Timer(1000/60,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(positionY > 6)
					positionY -= 6;
				else {
					reachedEnd = true;
					endTimer.stop();
				}	
			}
		});
		Timer animationTimer = new Timer(350,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				animationCounter++;	
			}
		});
		animationTimer.start();
	}
	
	/**
	 * Draws the Boat.
	 * @param g2 - The Graphics2D object.
	 */
	@Override
	public void draw(Graphics2D g2) {
		//Drawing ship:
		if(!isDead()) {
			BufferedImage subImage = getSprite().getSubimage((animationCounter%3)*128,0,128,193);
			g2.drawImage(subImage,getPositionX(),getPositionY(),null);
		}
		//Drawing dead message: 
		if(deadMessageTimer != null) {
			if(deadMessageTimer.isRunning()) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Verdana",Font.BOLD,60));
				drawCenteredText("Probeer het opnieuw", g2, ControlManager.screenHeight/2);
			}
		}
	}
	
	/**
	 * Method that draws given text in the center of the screen.
	 * @param text - The text you want to display.
	 * @param g2 - The graphics2D object.
	 * @param y - The y position of the text.
	 */
	public void drawCenteredText(String text, Graphics2D g2, int y) {
		int x = (ControlManager.screenWidth-g2.getFontMetrics().stringWidth(text))/2;
		g2.drawString(text, x, y);
	}
	
	/**
	 * Update method for the Boat object.
	 * Get's pressure plate status and moves the boat.
	 */
	public void update() {
		//boolean pressurePlate1 = input.getPressurePlate1(); //Right foot
		//boolean pressurePlate2 = input.getPressurePlate2(); //Left foot
		//boolean pressurePlate3 = input.getPressurePlate3(); //Right foot
		//boolean pressurePlate4 = input.getPressurePlate4(); //Left foot
		if(pressurePlate1 && pressurePlate3 && !pressurePlate2 && !pressurePlate4) { // Go to the rights
			if( positionY < 178) {
				if(positionX > ControlManager.screenWidth/2-(getSprite().getWidth()/2)-45)
					positionX += 13;
			}
			else if(positionX <= ControlManager.screenWidth/4*3-ControlManager.screenWidth/8) 
				positionX += 13;
				
		}
		else if(!pressurePlate1 && !pressurePlate3 && pressurePlate2 && pressurePlate4) {// Go to the left
			if( positionY < 178) {
				if(positionX < ControlManager.screenWidth/2+13)
					positionX -= 13;
			}
			else if(positionX > ControlManager.screenWidth/4+ControlManager.screenWidth/20)
				positionX -= 13;
		}
	}
	
	/**
	 * Init method for the Boat object.
	 * Sets x and y starting positions, also initializes the pressure plates.
	 */
	public void init() {
		positionX = ControlManager.screenWidth/2;
		positionY = ControlManager.screenHeight - 250;
		setTimer(false);
		//input.turnPressurePlates(true);
	}
	
	/**
	 * Get's called when the ship collides with a Rock object.
	 */
	public void collision() {
		//input.deadStageOne();
			deadMessageTimer = new Timer(200,new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(alpha > 0.045) {
						setDead(!isDead());
						alpha -= 0.045f;
					}
					else {
						deadMessageTimer.stop();
						setDead(false);
						alpha = 1.0f;
					}
				}
			});
			deadMessageTimer.start();
	}
	
	/**
	 * Resets the position to start position.
	 */
	public void reset() {
		//input.deadStageTwo();
		setEndTimer(false);
		reachedEnd = false;
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
	 * Sets the reached end.
	 * @param reachedEnd - whether the boat has reached the end or not.
	 */
	public void setReachedEnd(boolean reachedEnd) {
		this.reachedEnd = reachedEnd;
	}
	
	/**
	 * Toggle the endTimer.
	 * @param state - The state of the endTimer.
	 */
	public void setEndTimer(boolean state) {
		if(state)
			endTimer.start();
		else
			endTimer.stop();
	}
	
	/**
	 * Checks if one of the pixels is inside the boats body.
	 * @param object - the object you want to check for collision.
	 * @return if there is an intersection between the two objects.
	 */
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
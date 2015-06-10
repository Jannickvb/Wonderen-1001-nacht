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

public class Player extends Entity {

	private InputHandler input;
	private int animationCounter;
	private float alpha;
	private boolean drawBoat;
	private boolean reachedEnd;
	private Timer deadMessageTimer;
	private Timer endTimer;
	
	private boolean pressurePlate1; //Right foot
	private boolean pressurePlate2; //Left foot
	private boolean pressurePlate3; //Right foot
	private boolean pressurePlate4; //Left foot
	
	/**
	 * Constructor of the Player object.
	 * @param cm - The control manager of the game.
	 */
	public Player(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageHandler.ImageType.player_boat));
		input = cm.getInputHandler();
		animationCounter = 0;
		alpha = 1.0f;
		drawBoat = true;
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
	
	@Override
	public void draw(Graphics2D g2) {
		//Drawing ship:
		if(drawBoat) {
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
	
	public void update() {
//		boolean pressurePlate1 = input.getPressurePlate1(); //Right foot
//		boolean pressurePlate2 = input.getPressurePlate2(); //Left foot
//		boolean pressurePlate3 = input.getPressurePlate3(); //Right foot
//		boolean pressurePlate4 = input.getPressurePlate4(); //Left foot
		if(pressurePlate1 && pressurePlate3 && !pressurePlate2 && !pressurePlate4) { // Go to the rights
			if(positionX <= ControlManager.screenWidth/4*3-ControlManager.screenWidth/8) {
				positionX += 13;
			}	
		}
		else if(!pressurePlate1 && !pressurePlate3 && pressurePlate2 && pressurePlate4) {// Go to the left
			if(positionX > ControlManager.screenWidth/4+ControlManager.screenWidth/20)
				positionX -= 13;
		}
	}
	
	public void init() {
		positionX = ControlManager.screenWidth/2;
		positionY = ControlManager.screenHeight - 250;
		setTimer(false);
		//input.turnPressurePlates(true);
	}
	
	public Rectangle2D getRectangleBounds() {
		return new Rectangle2D.Double(positionX+40,positionY+30,57,173-40); //193
	}
	
	
	public void collision() {
			positionY = ControlManager.screenHeight-250;
			positionX = ControlManager.screenWidth/2;
			setDead(true);
			deadMessageTimer = new Timer(200,new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(alpha > 0.045) {
						drawBoat = !drawBoat;
						alpha -= 0.045f;
					}
					else {
						deadMessageTimer.stop();
						drawBoat = true;
						alpha = 1.0f;
					}
				}
			});
			deadMessageTimer.start();
	}
	
	public void reset() {
			positionY = ControlManager.screenHeight-250;
			setDead(false);
	}
	
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
	
	public void endGame() {
		endTimer.start();
	}
	
	/**
	 * Checks if one of the pixels is inside the boats body.
	 * @param object - the object you want to check for collision.
	 * @return if there is an intersection between the two objects.
	 */
	public boolean containsPoint(Entity object) {
		Shape boatShape = getRectangleBounds();
		Rectangle2D objectRectangle = object.getRectangle();
		if(boatShape.intersects(objectRectangle)) 
			return true;
		else
			return false;
	}
	
	public boolean reachedEnd() {
		return reachedEnd;
	}
}

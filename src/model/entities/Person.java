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

public class Person extends Entity{
	
	private InputHandler input;
	private int animationCounter;
	private float alpha;
	private boolean reachedEnd;
	private Timer deadMessageTimer;
	private Timer endTimerCity;
	
	private boolean pressurePlate1; //Right foot
	private boolean pressurePlate2; //Left foot
	private boolean pressurePlate3; //Right foot
	private boolean pressurePlate4; //Left foot
		
		public Person(ControlManager cm) {
			super(cm,ImageHandler.getImage(ImageHandler.ImageType.player_run));
			input = cm.getInputHandler();
			animationCounter = 0;
			alpha = 1.0f;
			deadMessageTimer = new Timer(100,null);
			endTimerCity = new Timer(1000/60,new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if(positionY > 150)
						positionY -= 6;
						
					else {
						
							
							endTimerCity.stop();
					}
						
				}
			});
			Timer animationTimer = new Timer(150,new ActionListener() {
				
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
			if(!isDead()) {
				BufferedImage subImage = getSprite().getSubimage((animationCounter%3)*23,0,23,29);
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
		
		public void drawCenteredText(String text, Graphics2D g2, int y) {
			int x = (ControlManager.screenWidth-g2.getFontMetrics().stringWidth(text))/2;
			g2.drawString(text, x, y);
		}
		
		public void update() {
			//boolean pressurePlate1 = input.getPressurePlate1(); //Right foot
			//boolean pressurePlate2 = input.getPressurePlate2(); //Left foot
			//boolean pressurePlate3 = input.getPressurePlate3(); //Right foot
			//boolean pressurePlate4 = input.getPressurePlate4(); //Left foot
			if(pressurePlate1 && pressurePlate3 && !pressurePlate2 && !pressurePlate4) { // Go to the right
				if( positionY < 100) {
					if(positionX > ControlManager.screenWidth/2-(getSprite().getWidth()/2)-20)
						positionX += 13;
				}
				else if(positionX <= ControlManager.screenWidth/4*3-ControlManager.screenWidth/8 + 35) 
					positionX += 13;
					
			}
			else if(!pressurePlate1 && !pressurePlate3 && pressurePlate2 && pressurePlate4) {// Go to the left
				if( positionY < 100) {
					if(positionX < ControlManager.screenWidth/2+13)
						positionX -= 13;
				}
				else if(positionX > ControlManager.screenWidth/4+ControlManager.screenWidth/20+35)
					positionX -= 13;
			}
		}
		

		
		public Rectangle2D getRectangleBounds() {
			return new Rectangle2D.Double(positionX,positionY,23,29); //193
		}
		
		public void init() {
			positionX = ControlManager.screenWidth/2;
			positionY = ControlManager.screenHeight - 100;
			setTimer(false);
			//input.turnPressurePlates(true);
		}
		
		/**
		 * Get's called when the ship collides with a Rock object.
		 */
		public void collision() {
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
		
		public void reset() {
			positionY = ControlManager.screenHeight-100;
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
				endTimerCity.start();
			else
				endTimerCity.stop();
		}
		
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


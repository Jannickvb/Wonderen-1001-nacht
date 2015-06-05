package model.entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;
import javax.swing.Timer;

import control.ControlManager;
import control.ImageHandler;
import control.InputHandler;

public class Player extends Entity implements ActionListener {

	private int screenWidth;
	private InputHandler input;
	private int animationCounter;
	private BufferedImage liveHeart;
	private int lives;
	private float alpha;
	private Timer deadMessageTimer;
	
	private boolean pressurePlate1; //Right foot
	private boolean pressurePlate2; //Left foot
	private boolean pressurePlate3; //Right foot
	private boolean pressurePlate4; //Left foot
	
	public Player(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageHandler.ImageType.player_boat));
		input = cm.getInputHandler();
		liveHeart = ImageHandler.getImage(ImageHandler.ImageType.heart);
		animationCounter = 0;
		lives = 3;
		alpha = 1.0f;
		deadMessageTimer = new Timer(100,null);
		Timer animationTimer = new Timer(350,this);
		animationTimer.start();
	}
	
	public void draw(Graphics2D g2) {
		//Drawing ship:
		BufferedImage subImage = getSprite().getSubimage((animationCounter%3)*128,0,128,193);
		g2.drawImage(subImage,getPositionX(),getPositionY(),null);
		//Drawing lives:
		for(int x = 0; x < lives; x++) 
			g2.drawImage(liveHeart,50+150*x,5,null);
		if(deadMessageTimer.isRunning()) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Verdana",Font.BOLD,60));
			drawCenteredText("Try Again", g2, ControlManager.screenWidth/2);
		}
	}
	
	public void drawCenteredText(String text, Graphics2D g2, int y) {
		int x = (screenWidth-g2.getFontMetrics().stringWidth(text))/2;
		g2.drawString(text, x, y);
	}
	
	public void update() {
//		boolean pressurePlate1 = input.getPressurePlate1(); //Right foot
//		boolean pressurePlate2 = input.getPressurePlate2(); //Left foot
//		boolean pressurePlate3 = input.getPressurePlate3(); //Right foot
//		boolean pressurePlate4 = input.getPressurePlate4(); //Left foot
		if(pressurePlate1 && pressurePlate3 && !pressurePlate2 && !pressurePlate4) { // Go to the right
			if(positionX <= screenWidth/4*3-240) {
				positionX += 13;
			}	
		}
		else if(!pressurePlate1 && !pressurePlate3 && pressurePlate2 && pressurePlate4) {// Go to the left
			if(positionX > screenWidth/4+100)
				positionX -= 13;
		}
	}
	
	public void init() {
		positionX = ControlManager.screenWidth/2;
		positionY = ControlManager.screenHeight - 250;
		input.turnPressurePlates(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		animationCounter++;
	}
	
	public Rectangle2D getRectangleBounds() {
		return new Rectangle2D.Double(positionX+40,positionY+30,57,173-40); //193
	}
	
	
	public void collision() {
		if(lives > 1) {
			lives--;
			positionY = ControlManager.screenHeight-250;
			setDead(true);
			deadMessageTimer = new Timer(100,new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(alpha > 0.025)
						alpha -= 0.025f;
					else {
						deadMessageTimer.stop();
						alpha = 1.0f;
					}
				}
			});
			deadMessageTimer.start();
		}
		else {
			lives--; 
			dead();
		}
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
	
	public void dead() {
		
	}
}

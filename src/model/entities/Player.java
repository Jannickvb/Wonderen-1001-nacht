package model.entities;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

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
	
	private boolean pressurePlate1; //Right foot
	private boolean pressurePlate2; //Left foot
	private boolean pressurePlate3; //Right foot
	private boolean pressurePlate4; //Left foot
	
	public Player(ControlManager cm) {
		super(cm,ImageHandler.getImage(ImageHandler.ImageType.boat));
		input = cm.getInputHandler();
		liveHeart = ImageHandler.getImage(ImageHandler.ImageType.heart);
		animationCounter = 0;
		lives = 3;
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
	}
	
	public void update() {
//		boolean pressurePlate1 = input.getPressurePlate1(); //Right foot
//		boolean pressurePlate2 = input.getPressurePlate2(); //Left foot
//		boolean pressurePlate3 = input.getPressurePlate3(); //Right foot
//		boolean pressurePlate4 = input.getPressurePlate4(); //Left foot
		if(pressurePlate1 && pressurePlate3 && !pressurePlate2 && !pressurePlate4) { // Go to the right
			if(positionX <= screenWidth/4*3-250) {
				positionX += 13;
			}	
		}
		else if(!pressurePlate1 && !pressurePlate3 && pressurePlate2 && pressurePlate4) {// Go to the left
			if(positionX > screenWidth/4+100)
				positionX -= 13;
		}
	}
	
	public void init() {
		screenWidth = cm.getWidth();
		positionX = cm.getWidth()/2;
		positionY = cm.getHeight()-250;
		input.turnPressurePlates(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		animationCounter++;
	}
	
	public Rectangle2D getRectangleBounds() {
		return new Rectangle2D.Double(positionX,positionY,128,193);
	}
	
	
	public void collision() {
		if(lives > 1) {
			lives--;
			positionY = cm.getHeight()-250;
		}
		else {
			lives--; 
			dead();
		}
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

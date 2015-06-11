package model.gamestates.story;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;
import control.InputHandler;

public class DoorChoiceState extends GameState{
	
	private Image image;
	private int midX,midY,bgWidth,bgHeight,counter;
	private InputHandler input;
	
	private boolean pressurePlate1; //Right foot
	private boolean pressurePlate2; //Left foot
	private boolean pressurePlate3; //Right foot
	private boolean pressurePlate4; //Left foot
	
	public DoorChoiceState(ControlManager cm)
	{
		super(cm);
		input = cm.getInputHandler();
		this.counter = 0;
		image = ImageHandler.getImage(ImageHandler.ImageType.choice1);
		bgWidth = image.getWidth(null);
		bgHeight = image.getHeight(null);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
	}
 
	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(image, -bgWidth/2,-bgHeight/2,null);
	}

	@Override
	public void update() {
		boolean pressurePlate1 = input.getPressurePlate1(); //Right foot
		boolean pressurePlate2 = input.getPressurePlate2(); //Left foot
		boolean pressurePlate3 = input.getPressurePlate3(); //Right foot
		boolean pressurePlate4 = input.getPressurePlate4(); //Left foot
		if(pressurePlate1 && pressurePlate3 && !pressurePlate2 && !pressurePlate4) { // Go to the right
			cm.getGameStateManager().skipNext();
		}
		else if(!pressurePlate1 && !pressurePlate3 && pressurePlate2 && pressurePlate4) {// Go to the left
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void init() {
		if(image.equals(ImageHandler.getImage(ImageHandler.ImageType.choice1))){
			try {
				cm.travelChoiceVoice();
			} catch (LineUnavailableException | IOException e) {
				e.printStackTrace();
			}
		}
		//input.turnPressurePlates(true);
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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			cm.getGameStateManager().next();
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			cm.getGameStateManager().skipNext();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}


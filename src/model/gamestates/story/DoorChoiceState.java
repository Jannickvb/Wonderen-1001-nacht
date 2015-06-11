package model.gamestates.story;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;
import control.InputHandler;

public class DoorChoiceState extends GameState{
	
	private BufferedImage vignette,door,doorLeft,doorRight,djinn,wizard,cloudLeft,cloudRight;
	private int midX,midY,bgWidth,bgHeight,keyFrames;
	private InputHandler input;
	
	private boolean pressurePlate1; //Right foot
	private boolean pressurePlate2; //Left foot
	private boolean pressurePlate3; //Right foot
	private boolean pressurePlate4; //Left foot
	
	public DoorChoiceState(ControlManager cm)
	{
		super(cm);
		input = cm.getInputHandler();
		this.keyFrames = 0;
		
		vignette = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_vignette));
		door = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_door));
		doorLeft = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_door_left));
		doorRight = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_door_right));
		djinn = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_djinn));
		wizard = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_wizard));
		cloudLeft = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_cloud_left));
		cloudRight = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_cloud_right));
		
		bgWidth = door.getWidth(null);
		bgHeight = door.getHeight(null);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
	}
 
	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(doorLeft, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(doorRight, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(door, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(djinn, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(wizard, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(cloudRight, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(cloudLeft, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(vignette, -bgWidth/2,-bgHeight/2,null);
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
//			try {
//				cm.travelChoiceVoice();
//			} catch (LineUnavailableException | IOException e) {
//				e.printStackTrace();
//			}
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
			cm.getGameStateManager().next();
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}


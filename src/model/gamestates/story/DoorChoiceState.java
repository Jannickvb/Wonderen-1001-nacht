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
	
	private BufferedImage vignette,door,doorLeft,doorRight,djinn,wizard,cloudLeft,cloudRight,poor,rich;
	private int midX,midY,bgWidth,bgHeight,keyFrame;
	private int cloudX, doorX;
	private double scale = 1;
	private InputHandler input;
	private boolean animationLeft = false, animationRight = false,play = false,choiceMade = false,choiceR = false,choiceL = false;
	private boolean p1; //Right foot
	private boolean p2; //Left foot
	private boolean p3; //Right foot
	private boolean p4; //Left foot
	public DoorChoiceState(ControlManager cm)
	{
		super(cm);
		input = cm.getInputHandler();
		this.keyFrame = 0;
		
		poor = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_poor));
		rich = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_rich));
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
		tx.scale(scale, scale);
		g2.setTransform(tx);
		if(animationLeft)
			g2.drawImage(poor, -bgWidth/2,-bgHeight/2,null);
		if(animationRight)
			g2.drawImage(rich,-bgWidth/2,-bgHeight/2,null);
		g2.drawImage(doorLeft, -bgWidth/2 - doorX,-bgHeight/2,null);
		g2.drawImage(doorRight, -bgWidth/2 + doorX,-bgHeight/2,null);
		g2.drawImage(door, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(djinn, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(wizard, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(cloudRight, -bgWidth/2 + cloudX,-bgHeight/2,null);
		g2.drawImage(cloudLeft, -bgWidth/2 - cloudX,-bgHeight/2,null);
		g2.drawImage(vignette, -bgWidth/2,-bgHeight/2,null);
		
	}

	@Override
	public void update() {
//		boolean pressurePlate1 = input.getPressurePlate1(); //Right foot
//		boolean pressurePlate2 = input.getPressurePlate2(); //Left foot
//		boolean pressurePlate3 = input.getPressurePlate3(); //Right foot
//		boolean pressurePlate4 = input.getPressurePlate4(); //Left foot
//		
		if(play && platePressed()){
			keyFrame++;
			doorX+=5;
		}
		
		if(!platePressed()){
			if(!isIdle()){
				doorX-=5;
			}
		}
		
		if(choiceMade){
			keyFrame++;
			doorX = keyFrame;
			cloudX += 5;
			scale += 0.001;
			if(keyFrame >= 35){
				if(choiceR){
					cm.getGameStateManager().next();
				}else if(choiceL){
					cm.getGameStateManager().next();
				}
			}
		}
		if(platePressed() && !animationLeft && !animationRight && !play && isIdle()){
			if(p1 && p3 && !p2 && !p4) { // Go to the right
				animationLeft = true;
				animationRight = false;
				choiceR = false;
				choiceL = true;
				choiceMade = true;
				keyFrame = 0;
				System.out.println("go to right");
			}
			if(!p1 && !p3 && p2 && p4) {// Go to the left
				animationLeft = false;
				animationRight = true;
				choiceR = true;
				choiceL = false;
				choiceMade = true;
				keyFrame = 0;
			}
			if(!p1 && !p3 && p4){//single press right
				animationLeft = false;
				animationRight = true;
				choiceR = true;
				choiceL = false;
			}
			if(p1 && !p2 && !p4){//single press left
				animationLeft = true;
				animationRight = false;
				choiceR = false;
				choiceL = true;
				System.out.println("left");
			}
		}
		if(doorX>250){
			doorX = 250;
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

	public boolean platePressed(){
		if(!p1 && !p2 && !p3 && !p4 && !choiceMade){
			choiceR = false;
			choiceL = false;
			return false;
		}else if(!choiceMade){
			return true;
		}
		return false;
	}
	
	public boolean isIdle(){
		if(doorX == 0){
			keyFrame = 0;
			animationLeft = false;
			animationRight = false;
			play = false;
			return true;
		}
		else
			return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1)
			p1 = true;
		if(e.getKeyCode() == KeyEvent.VK_2)
			p2 = true;
		if(e.getKeyCode() == KeyEvent.VK_3)
			p3 = true;
		if(e.getKeyCode() == KeyEvent.VK_4)
			p4 = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1)
			p1 = false;
		if(e.getKeyCode() == KeyEvent.VK_2)
			p2 = false;
		if(e.getKeyCode() == KeyEvent.VK_3)
			p3 = false;
		if(e.getKeyCode() == KeyEvent.VK_4)
			p4 = false;		
	}
}


package model.gamestates.story;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import model.entities.Coin;
import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;
import control.InputHandler;

public class DoorChoiceState extends GameState{
	

	private BufferedImage foreground,background,cloudTroll,cloudL,cloudR,cloudWiz,overlay;
	private boolean fadeIn= true,fadeOut = false,choiceMade = false,choicePoor = false;
	private int bgPosY,frame = 0;
	private float opacity = 1f;
	private Rectangle2D tpRect,tpRect2,fade;
	
	private InputHandler input;
	public DoorChoiceState(ControlManager cm)
	{
		super(cm);
		input = cm.getInputHandler();
		this.frame = 0;
		foreground = ImageHandler.getImage(ImageHandler.ImageType.pr_wiz_troll);
		cloudTroll =  ImageHandler.getImage(ImageHandler.ImageType.pr_cloud_troll);
		cloudWiz =  ImageHandler.getImage(ImageHandler.ImageType.pr_cloud_wiz);
		background = ImageHandler.getImage(ImageHandler.ImageType.pr_bg);
		overlay = ImageHandler.getImage(ImageHandler.ImageType.pr_overlay);
		cloudL = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_cloud_left));
		cloudR = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.pr_cloud_right));
		
		
		tpRect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		tpRect2 = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		fade = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
	
	}
 
	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(0, 0);
		g2.setTransform(tx);
		g2.drawImage(background,0,0,null);
		 //Drawing background: 
	    TexturePaint tp = new TexturePaint(cloudTroll,new Rectangle2D.Double(0,-bgPosY,ControlManager.screenWidth,ControlManager.screenHeight));
	    g2.setPaint(tp);
	    g2.fill(tpRect);
	    //Drawing background: 
	    TexturePaint tp1 = new TexturePaint(cloudWiz,new Rectangle2D.Double(0,-bgPosY,ControlManager.screenWidth,ControlManager.screenHeight));
	    g2.setPaint(tp1);
	    g2.fill(tpRect2);
	    
	    g2.drawImage(foreground,0,-50,null);	
	    g2.drawImage(cloudL,0,0,null);	
	    g2.drawImage(cloudR,0,0,null);	

	    
	    if(fadeIn || fadeOut)
	    {
	    	g2.setColor(new Color(0,0,0,opacity));
	    	g2.fill(fade);
	    	g2.draw(fade);
	    }
		
	}

	@Override
	public void update() {

		frame++;
		bgPosY += 2;
		if(frame<120 && opacity>0.05f){
			opacity-=(0.1/4);
		}else if(!fadeOut){
			fadeIn = false;
			opacity = 0;
		}
		if(choiceMade && opacity < 0.95f){
			opacity += (0.1/4);
			fadeOut = true;
		}else if(choiceMade && opacity > 0.93f){
			if(choicePoor){
				cm.getGameStateManager().next();
			}else{
				cm.getGameStateManager().next();
				cm.getGameStateManager().next();
			}
		}
		if((input.getPressurePlate1()&&input.getPressurePlate2())&&frame>1080){
			choiceMade = true;
			choicePoor = true;
		}
		if((input.getPressurePlate3()&&input.getPressurePlate4())&&frame>1080){
			choiceMade = true;
			choicePoor = false;
		}
	}

	@Override
	public void init() {
			try {
				cm.travelChoiceVoice();
			} catch (LineUnavailableException | IOException e) {
				e.printStackTrace();
			}
			 cm.getInputHandler().resetLedStrip();
		input.turnPressurePlates(true);
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			choiceMade = true;
			choicePoor = true;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			choiceMade = true;
			choicePoor = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
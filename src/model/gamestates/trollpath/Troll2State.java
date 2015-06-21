package model.gamestates.trollpath;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;

public class Troll2State extends GameState{
	private BufferedImage foreground,background,vignette;
	private boolean fadeIn= true,fadeOut = false;
	private int bgPosY,frame = 0;
	private float opacity = 1f;
	private Rectangle2D tpRect,fade;
	
	public Troll2State(ControlManager cm)
	{
		super(cm);
		vignette = ImageHandler.getImage(ImageHandler.ImageType.troll_darken);
		foreground = ImageHandler.getImage(ImageHandler.ImageType.troll_fg);
		background = ImageHandler.getImage(ImageHandler.ImageType.troll_bg);
		tpRect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		fade = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);

	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(0, 0);
		g2.setTransform(tx);
		 //Drawing background: 
	    TexturePaint tp = new TexturePaint(background,new Rectangle2D.Double(0,-bgPosY,ControlManager.screenWidth,ControlManager.screenHeight));
	    g2.setPaint(tp);
	    g2.fill(tpRect);
	    
	    g2.drawImage(foreground,0,0,null);
	    
	    g2.drawImage(vignette,0,0,null);
	    
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
		if(frame > 690 && opacity < 0.95f){
			opacity += (0.1/4);
			fadeOut = true;
		}
	}

	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(background.equals(ImageHandler.getImage(ImageHandler.ImageType.troll_bg))){
		try {
			cm.playTrollTalk2();
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	new java.util.Timer().schedule( 
			    			        new java.util.TimerTask() {
			    			            @Override
			    			            public void run() {
			    			                cm.getGameStateManager().next();
			    			            }
			    			        }, 
			    			        2000
			    			);
			            }
			        }, 
			        9500
			);
			
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

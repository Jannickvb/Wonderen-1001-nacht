package model.gamestates.story;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import model.gamestates.GameState;
import control.ControlManager;
import control.DrawFuctions;
import control.ImageHandler;
import control.MapReader;

public class MageTalkState extends GameState{
	
	private BufferedImage foreground,background;
	private boolean fadeIn= true,fadeOut = false;
	private int bgPosY,frame = 0;
	private float opacity = 1f;
	private Rectangle2D tpRect,fade;
	
	private ArrayList<String> introText;
	private int lineCounter = 0;
	
	public MageTalkState(ControlManager cm)
	{
		super(cm);
		foreground = ImageHandler.getImage(ImageHandler.ImageType.magestate);
		background = ImageHandler.getImage(ImageHandler.ImageType.mirror_bg);
		tpRect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		fade = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		try {
			introText = MapReader.readTextLines("resources/texts/introTovenaar.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	    
	    if(fadeIn || fadeOut)
	    {
	    	g2.setColor(new Color(0,0,0,opacity));
	    	g2.fill(fade);
	    	g2.draw(fade);
	    }
	    	
//		g2.translate(-midX,-midY);
//		DrawFuctions.drawString(g2, introText.get(lineCounter), ControlManager.screenHeight - 300);
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
		if(frame > 720 && opacity < 0.95f){
			opacity += (0.1/4);
			fadeOut = true;
		}
		if(cm.getInputHandler().isA1Pressed() || cm.getInputHandler().isA2Pressed())
		{
			if(lineCounter<introText.size()-1)
			{
				lineCounter++;
			}
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(foreground.equals(ImageHandler.getImage(ImageHandler.ImageType.magestate))){
		try {
			cm.getInputHandler().enableWizardTalk();
				cm.playMusic1();
				cm.playIntroWizard();
				
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				                cm.getGameStateManager().next();
				            }
				        }, 
				        12000 
				);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(lineCounter<introText.size()-1 && e.getKeyCode() == KeyEvent.VK_K)
		{
			lineCounter++;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
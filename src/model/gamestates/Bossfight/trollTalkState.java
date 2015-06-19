package model.gamestates.Bossfight;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import model.gamestates.GameState;
import control.ControlManager;
import control.DrawFuctions;
import control.ImageHandler;
import control.MapReader;
import control.ImageHandler.ImageType;

public class trollTalkState extends GameState{
	
	private Image tutorial;
	private int midX,midY,bgWidth,bgHeight,counter;
	private ArrayList<String> introText;
	private int lineCounter = 0;
	
	public trollTalkState(ControlManager cm)
	{
		super(cm);
		this.counter = 0;
		tutorial = ImageHandler.getScaledImage(ImageHandler.getImage(ImageType.troll));
		bgWidth = tutorial.getWidth(null);
		bgHeight = tutorial.getHeight(null);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
		try {
			introText = MapReader.readTextLines("resources/texts/introTroll.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(tutorial, -bgWidth/2,-bgHeight/2,null);
		g2.translate(-midX,-midY);
		DrawFuctions.drawString(g2, introText.get(lineCounter), ControlManager.screenHeight - 300);
	}

	@Override
	public void update() {
		counter++;
		if(counter > 300)
		{
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void init() {
		try {
			cm.playTrollIntro();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

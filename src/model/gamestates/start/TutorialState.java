package model.gamestates.start;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import model.gamestates.GameState;
import control.ControlManager;
import control.DrawFuctions;
import control.ImageHandler;
import control.MapReader;

public class TutorialState extends GameState{
	
	private BufferedImage tutorial;
	private int midX,midY,bgWidth,bgHeight;
	private ArrayList<String> introText;
	private int lineCounter = 0;
	private int tutorialCounter;
	
	public TutorialState(ControlManager cm, BufferedImage image,int tutorialCounter)
	{
		super(cm);
		this.tutorialCounter = tutorialCounter;
		this.tutorial = ImageHandler.getScaledImage(image);
		bgWidth = tutorial.getWidth(null);
		bgHeight = tutorial.getHeight(null);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
		try {
			introText = MapReader.readTextLines("resources/texts/introBoot.txt");
		} catch (IOException e) {
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
//		if(tutorialCounter ==0)
//		DrawFuctions.drawString(g2, introText.get(lineCounter), ControlManager.screenHeight - 300);
	}

	@Override
	public void update() {
		if(cm.getInputHandler().isA1Pressed() || cm.getInputHandler().isA2Pressed())
		{
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void init() {
		if(tutorial.equals(ImageHandler.getImage(ImageHandler.ImageType.tutorial_plate))){
		try {
			cm.playBoatTutorialVoice();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(lineCounter<introText.size()-1 && e.getKeyCode() == KeyEvent.VK_K)
		{
			lineCounter++;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

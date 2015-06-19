package model.gamestates.story;

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

public class ArrivalState extends GameState{
	
	private Image image;
	private int midX,midY,bgWidth,bgHeight,counter;
	private ArrayList<String> introText;
	private int lineCounter = 0;
	
	public ArrivalState(ControlManager cm)
	{
		super(cm);
		this.counter = 0;
		image = ImageHandler.getImage(ImageHandler.ImageType.arrival);
		bgWidth = image.getWidth(null);
		bgHeight = image.getHeight(null);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
		try {
			introText = MapReader.readTextLines("resources/texts/introStad.txt");
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
		g2.drawImage(image, -bgWidth/2,-bgHeight/2,null);
//		g2.translate(-midX,-midY);
//		DrawFuctions.drawString(g2, introText.get(lineCounter), ControlManager.screenHeight - 300);
	}

	@Override
	public void update() {
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
		if(image.equals(ImageHandler.getImage(ImageHandler.ImageType.arrival))){
			try {
				cm.arrivalVoice();
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				                cm.getGameStateManager().next();
				            }
				        }, 
				        14000 
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


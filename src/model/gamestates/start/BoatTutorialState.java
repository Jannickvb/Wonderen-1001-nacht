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
import control.ImageHandler.ImageType;
import control.MapReader;

public class BoatTutorialState extends GameState{
	
	private BufferedImage background,help,screen,ppLeft,ppRight,ppOff;
	private int frame,screenY=-1100,helpY=-900;
	private ArrayList<String> introText;
	private int lineCounter = 0;
	private boolean isPPLeft = false,isPPRight = false;
	public BoatTutorialState(ControlManager cm)
	{
		super(cm);
		background = ImageHandler.getScaledImage(ImageHandler.getImage(ImageType.boat_tut_bg));
		screen = ImageHandler.getScaledImage(ImageHandler.getImage(ImageType.boat_tut_screen));
		help = ImageHandler.getScaledImage(ImageHandler.getImage(ImageType.boat_tut_help));
		ppLeft= ImageHandler.getScaledImage(ImageHandler.getImage(ImageType.boat_tut_pp_left));
		ppRight= ImageHandler.getScaledImage(ImageHandler.getImage(ImageType.boat_tut_pp_right));
		ppOff = ImageHandler.getScaledImage(ImageHandler.getImage(ImageType.boat_tut_pp_off));
		
		try {
			introText = MapReader.readTextLines("resources/texts/introBoot.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(0, 0);
		g2.setTransform(tx);
		
		g2.drawImage(background,0,0,null);
		if(!isPPLeft && !isPPRight)
			g2.drawImage(ppOff,0,0,null);
		if(isPPLeft)
			g2.drawImage(ppLeft, 0, 0, null);
		if(isPPRight)
			g2.drawImage(ppRight, 0, 0, null);
		
		g2.drawImage(screen, 0, screenY, null);
		g2.drawImage(help, 0, helpY, null);
//		if(tutorialCounter ==0)
//		DrawFuctions.drawString(g2, introText.get(lineCounter), ControlManager.screenHeight - 300);
	}

	@Override
	public void update() {
		frame++;
		if(helpY < 0){
			helpY+=8;
		}else{
			helpY = 0;
		}
		if(screenY < 0){
			screenY+=12;
		}else{
			screenY = 0;
		}
		if(frame == 500)
		{
			isPPLeft = true;
			isPPRight = false;
		}
		if(frame == 780)
		{
			isPPLeft = false;
			isPPRight = true;
		}
		if(frame == 1000){
			isPPRight = false;
			isPPLeft = false;
		}
		if((cm.getInputHandler().isA1Pressed() || cm.getInputHandler().isA2Pressed()) && frame > 1620)
		{
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void init() {
		if(background.equals(ImageHandler.getImage(ImageHandler.ImageType.boat_tut_bg))){
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

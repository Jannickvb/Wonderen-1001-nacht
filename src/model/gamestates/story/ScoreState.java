package model.gamestates.story;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;

public class ScoreState extends GameState{
	
	private String tier;
	private int score, scoreDisplay, highScore, time = 0;
	private int midX, midY;
	private BufferedImage background;
	private boolean started = false;

	public ScoreState(ControlManager cm) {
		super(cm);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
		background = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menubg));
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(background, 0,0,null);
		g2.setColor(new Color(0,0,0,120));
		g2.fillRect(0, 0, ControlManager.screenWidth,ControlManager.screenHeight);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Verdana", Font.BOLD, 70));
		ImageHandler.drawCenteredText("Jouw score: " + scoreDisplay, g2, midY-80);
		g2.setFont(new Font("Verdana", Font.BOLD, 50));
		if(time > 20) {
			String keuze;
			if(cm.getScoreHandler().isArmGekozen())
				keuze = "Arm  +250 punten";
			else
				keuze = "Rijk  -250 punten";
			ImageHandler.drawCenteredText("Keuze arm/rijk: " + keuze , g2, midY-20);
		}
		if(time > 40) {
			String keuze;
			if(cm.getScoreHandler().isKeptMoney())
				keuze = "Geld gehouden  -500 punten";
			else
				keuze = "Geld weg gegeven  +500 punten";
			ImageHandler.drawCenteredText("Keuze geld: " + keuze , g2, midY+40);
		}	
		if(time > 80) {
			ImageHandler.drawCenteredText("Je bent een: " + tier, g2, midY+100);
		}
		
		if(time > 100)
		{
			g2.setFont(new Font("Verdana", Font.BOLD, 40));
			g2.drawString("Hoogste score van vandaag: " + highScore, midX-350, midY+150);
		} 
		if(time > 140)
		{
			g2.drawString("Druk op A om opnieuw te beginnen", midX-400, midY+240);
		}
	}

	@Override
	public void update() {
		if(!started)
		{
			score = cm.getScoreHandler().getScore();
			
			if(cm.getScoreHandler().isArmGekozen())
			{
				score += 250;
			}
			else
			{
				//score = 0;
			}
			if(score == 0)
			{
				tier = "Trol";
			}
			if(score > 0 && score < 1000)
			{
				tier = "Amateur";
			}
			if(score >= 1000 && score < 2000)
			{
				tier = "Student";
			}
			if(score >= 2000 && score < 3000)
			{
				tier = "Tovenaar";
			}
			if(score >= 3000 && score < 4000)
			{
				tier = "Held";
			}
			if(score >= 4000 && score < 5000)
			{
				tier = "Kampioen";
			}
			started = true;
		}
		if(time > 60 && (cm.getInputHandler().isA1Pressed() || cm.getInputHandler().isA2Pressed()))
		{
			started = false;
			time = 0;
			scoreDisplay = 0;
			cm.getGameStateManager().reset();
		}
			
		if(scoreDisplay < score - 6 && score > 0) 
			scoreDisplay += 5;
		else if(scoreDisplay > score + 6)
			scoreDisplay -=5;
		else {
			scoreDisplay = score;
			if(score > highScore) {
				highScore = score;
			}
			time++;
		}
		if(time == 20) {
			if(cm.getScoreHandler().isArmGekozen()) 
				score += 250;
			else
				score -= 250;
			time++;
		}
		if(time == 40) {
			if(cm.getScoreHandler().isKeptMoney()) 
				score -= 500;
			else
				score += 500;
			time++;
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			cm.getGameStateManager().reset();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
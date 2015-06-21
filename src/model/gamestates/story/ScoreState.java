package model.gamestates.story;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import control.ControlManager;
import model.gamestates.GameState;

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
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(new Font("Verdana", Font.BOLD, 80));
		g2.drawString("Jouw score: " + scoreDisplay, midX-400, midY-80);
		if(time > 20)
		{
			g2.drawString("Je bent een: " + tier, midX-500, midY);
		}
		if(time > 40)
		{
			g2.setFont(new Font("Verdana", Font.BOLD, 40));
			g2.drawString("Hoogste score van vandaag: " + highScore, midX-400, midY+50);
		} 
		if(time > 80)
		{
			g2.drawString("Druk op A om opnieuw te beginnen", midX-400, midY+240);
		}
	}

	@Override
	public void update() {
		if(!started)
		{
			score = cm.getScoreHandler().bootScore + cm.getScoreHandler().bossScore + cm.getScoreHandler().cityScore;
			if(cm.getScoreHandler().armGekozen)
			{
				score += 250;
			}
			else
			{
				score = 0;
			}
			score = 2000;
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
			if(score > highScore)
			{
				highScore = score;
			}
			started = true;
		}
		if(time > 60 && (cm.getInputHandler().isA1Pressed() || cm.getInputHandler().isA2Pressed()))
		{
			started = false;
			cm.getGameStateManager().next();
		}
		else
		{
			if(scoreDisplay < score - 5)
			{
				scoreDisplay += 5;
			}
			else if(scoreDisplay < score)
			{
				scoreDisplay = score;
			}
			else
			{
				time++;
			}
		}
	}

	@Override
	public void init() {
		
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

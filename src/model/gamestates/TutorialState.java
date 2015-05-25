package model.gamestates;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import control.ControlManager;
import control.ImageHandler;

public class TutorialState extends GameState{
	
	private Image tutorial;
	
	public TutorialState(ControlManager cm)
	{
		super(cm);
		this.tutorial = ImageHandler.getImage(ImageHandler.ImageType.menubg);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(tutorial, 10, 10, null);
	}

	@Override
	public void update() {
		// Updaten van eventuele animaties en timer bijhouden
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
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

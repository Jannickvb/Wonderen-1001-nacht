package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import control.ControlManager;

public abstract class GameState {
	
	protected ControlManager cm;
	
	public GameState(ControlManager cm) {
		this.cm = cm;
	}
	public abstract void draw(Graphics2D g2);
	public abstract void update();
	public abstract void init();
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	public String toString(){
		return this.getClass().getSimpleName();
	}
}

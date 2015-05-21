package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import control.ControlManager;
import control.GameStateManager;

public class GamePanel extends JPanel{
	private ControlManager cm;
	private GameStateManager gsm;
	public GamePanel(ControlManager cm) {
		setFocusable(true);
		requestFocus(true);
		this.cm = cm;
		this.gsm = cm.getGameStateManager();
		Timer paint = new Timer(1000/60, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		paint.start();
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				//
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				gsm.currentstate.keyReleased(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				gsm.currentstate.keyPressed(e);
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		gsm.currentstate.draw(g2);
	}
	
	
}

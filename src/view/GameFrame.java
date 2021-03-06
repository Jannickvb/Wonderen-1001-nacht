package view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

import control.ControlManager;

public class GameFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	boolean fullscreenEnabled = false;
	int windowMode = 0;
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	public static int screenWidth;
	public GameFrame() throws LineUnavailableException, IOException{
		super("Wonderen van 1001-nacht");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel(new ControlManager(this)));
	}
	
	private void setWindowMode(){
		WindowMode wm = WindowMode.values()[windowMode];
		switch(wm){
		case window:
			System.out.println("No Fullscreen");
			this.setUndecorated(false);
			break;
		case fullscreenWindow:
			this.setUndecorated(true);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			break;
		case fullscreen:
			this.setUndecorated(true);
			device.setFullScreenWindow(this);
			break;
		}
	}
	
	public void setFullScreenEnabled(int newWindowMode){
		fullscreenEnabled = true;
		if(windowMode <= 2){
			this.windowMode = newWindowMode;
			this.setWindowMode();
		}else{
			System.out.println("error");
		}
	}
	
	static enum WindowMode{
		window,fullscreenWindow,fullscreen;
	}
}

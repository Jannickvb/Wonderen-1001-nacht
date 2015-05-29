package model.gamestates;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import control.ControlManager;
import control.ImageHandler;
import control.MusicHandler.AudioType;

public class MenuState extends GameState{

	private boolean pl1Ready,pl2Ready;
<<<<<<< HEAD
	private int width,height,midX,midY,bgWidth,bgHeight, mlWidth, mlHeight, mrWidth, mrHeight;
	private Image background, menuleft, menuright;

	public MenuState(ControlManager cm) {
=======
	private int width,height,midX,midY,bgWidth,bgHeight;
	private Image background;
	private Image readyCheck;
	private ControlManager cm;
	
	public MenuState(ControlManager cm){
>>>>>>> 2a552815bb0830ae703168ecf91700948dea89d7
		super(cm);
		this.cm = cm;
		background = ImageHandler.getImage(ImageHandler.ImageType.menubg);
<<<<<<< HEAD
		menuleft = ImageHandler.getImage(ImageHandler.ImageType.menu_left);
		menuright = ImageHandler.getImage(ImageHandler.ImageType.menu_right);
=======
		
>>>>>>> 2a552815bb0830ae703168ecf91700948dea89d7
	}

	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(background, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(menuleft, -mlWidth/2, -mlHeight/2, null);
		g2.drawImage(menuright, -mrWidth/2, -mrHeight/2, null);
		if(pl1Ready)
			g2.drawImage(background, 20, 20,null);
		if(pl2Ready)
			g2.drawImage(background, 40,40, null);
		
	}

	@Override
	public void update() {
		width = cm.getWidth();
		height = cm.getHeight();
		bgWidth = background.getWidth(null);
		bgHeight = background.getHeight(null);
		mlWidth = menuleft.getWidth(null);
		mlHeight = menuleft.getHeight(null);
		mrWidth = menuright.getWidth(null);
		mrHeight = menuright.getHeight(null);
		System.out.println("mr height: " + mrHeight);
		midX = width/2;
		midY = height/2;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	public void setPlayerStatus(boolean p1,boolean p2){
		pl1Ready = p1;
		pl2Ready = p2;
		if(pl1Ready && pl2Ready)
			cm.getGameStateManager().next();
	}
}

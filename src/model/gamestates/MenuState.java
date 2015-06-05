package model.gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import control.ControlManager;
import control.ImageHandler;

public class MenuState extends GameState{

	private boolean pl1Ready = false,pl2Ready = false;
	private int midX,midY,bgWidth,bgHeight,keyFrame;
	private Image background;
	private ControlManager cm;
	public MenuState(ControlManager cm){
		super(cm);
		this.cm = cm;
		background = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menubg));
		bgWidth = background.getWidth(null);
		bgHeight = background.getHeight(null);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
	}

	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(background, -bgWidth/2,-bgHeight/2,null);
		if(pl1Ready)
			g2.drawImage(background, 20, 20,null);
		if(pl2Ready)
			g2.drawImage(background, 40,40, null);
		
	}

	@Override
	public void update() {

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

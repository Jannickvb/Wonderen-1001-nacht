package model.gamestates;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;

import control.ControlManager;
import control.ImageHandler;

public class MenuState extends GameState{

	private boolean pl1Ready,pl2Ready,hasScaled;
	private int width,height,midX,midY,bgWidth,bgHeight, mlWidth, mlHeight, mrWidth, mrHeight;
	private Image background, menuleft, menuright,scaledBg,scaledLt,scaledRt;
	private ControlManager cm;
	Scalr.Mode mode1,mode2,mode3;
	public MenuState(ControlManager cm){
		super(cm);
		this.cm = cm;
		this.hasScaled = false;
	}

	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(scaledBg, -bgWidth/2,-bgHeight/2,null);
		g2.drawImage(scaledRt, -mlWidth/2, -mlHeight/2, null);
		g2.drawImage(scaledLt, -mrWidth/2, -mrHeight/2, null);
	}

	@Override
	public void update() {
		width = cm.getWidth();
		height = cm.getHeight();
		if(width != 0 && !hasScaled)
		{
			background = ImageHandler.getImage(ImageHandler.ImageType.menubg);
			mode1 = ImageHandler.getScale((BufferedImage)background);
			scaledBg = Scalr.resize((BufferedImage)background, mode1, width, Scalr.OP_ANTIALIAS);
			menuleft = ImageHandler.getImage(ImageHandler.ImageType.menu_left);
			mode2 = ImageHandler.getScale((BufferedImage)menuleft);
			scaledLt = Scalr.resize((BufferedImage)menuleft, mode2, width, Scalr.OP_ANTIALIAS);
			menuright = ImageHandler.getImage(ImageHandler.ImageType.menu_right);
			mode3 = ImageHandler.getScale((BufferedImage)menuright);
			scaledRt = Scalr.resize((BufferedImage)menuright, mode3, width, Scalr.OP_ANTIALIAS);
			hasScaled = true;
		}
		bgWidth = scaledBg.getWidth(null);
		bgHeight = scaledBg.getHeight(null);
		mlWidth = scaledLt.getWidth(null);
		mlHeight = scaledLt.getHeight(null);
		mrWidth = scaledRt.getWidth(null);
		mrHeight = scaledRt.getHeight(null);
		
		midX = width/2;
		midY = height/2;
		
	}

	@Override
	public void init() {
		
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

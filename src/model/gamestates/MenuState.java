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

	private boolean pl1Ready = false,pl2Ready = false,play = false,animation = false;
	private int midX,midY,bgWidth,bgHeight,infoWidth,iY,rX,lX,keyFrame = 0;
	private double lScale = 0,scale = 1;
	private float fade = 0;
	private AffineTransform r,l;
	private Image background,rImg,lImg,statueL,statueR,gradient,buttons,header,instr;
	private ControlManager cm;
	private Rectangle2D rect;
	public MenuState(ControlManager cm){
		super(cm);
		this.cm = cm;
		background = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menubg));
		rImg = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menu_right));
		lImg = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menu_left));
		statueL = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menu_statue_l));
		statueR = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menu_statue_r));
		gradient = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menu_grad));
		buttons = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menu_buttons));
		header = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.menu_header));
		instr = ImageHandler.getImage(ImageHandler.ImageType.menu_instr);
		
		bgWidth = background.getWidth(null);
		bgHeight = background.getHeight(null);
		infoWidth = instr.getWidth(null);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
	}

	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		AffineTransform bg = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		bg = tx;
		bg.scale(scale, scale);
		g2.setTransform(bg);
		g2.drawImage(background, -bgWidth/2,-bgHeight/2,null);
		g2.setTransform(tx);
		g2.drawImage(lImg, -bgWidth/2 - lX,-bgHeight/2, null);
		g2.drawImage(rImg, -bgWidth/2 - rX, -bgHeight/2, null);
		g2.drawImage(gradient,-bgWidth/2,-bgHeight/2,null);
		g2.drawImage(statueL, -bgWidth/2 +(lX/4), -bgHeight/2, null);
		g2.drawImage(statueR, -bgWidth/2 +(rX/4), -bgHeight/2, null);
		g2.drawImage(instr, -infoWidth/2, bgHeight/2 -200 - iY, null);
		if(!animation && !play) {
			g2.drawImage(buttons,-bgWidth/2,-bgHeight/2,null);
		}
		AffineTransform logo = new AffineTransform();
		logo = tx;
		logo.scale(lScale,lScale);
		g2.setTransform(logo);
		if(play) {
			g2.drawImage(header, -bgWidth/2, -bgHeight/2, null);
		}
		rect = new Rectangle2D.Double(-bgWidth/2, -bgHeight/2, bgWidth, bgHeight);
		g2.setColor(new Color(0,0,0,fade));
		g2.fill(rect);
		g2.draw(rect);
	}
	
	@Override
	public void update() {
		if(cm.getInputHandler().isA1Pressed() && cm.getInputHandler().isA2Pressed())
			animation = true;
		if(animation){
			keyFrame++;
			if(keyFrame<5){
				iY+=keyFrame%5;
			}else{
				iY-=keyFrame-4;
			}
			if(keyFrame>20){
				play = true;
				animation = false;
			}
		}
		if(play) {
			keyFrame++;
			rX+=5;
			lX-=5;
			scale+=0.01;
			lScale+=0.005;
			if(keyFrame >= 120&& fade < 1)
				fade+=0.1;
			if(keyFrame == 140)
				cm.getGameStateManager().next();
		}
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			animation = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}

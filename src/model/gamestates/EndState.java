package model.gamestates;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import control.ControlManager;
import control.ImageHandler;

public class EndState extends GameState{


	private Image bgImage, poorKid, richKid;
	private int midX,midY,bgWidth,bgHeight;
	private Boolean isRich;
	
	public EndState (ControlManager cm)
	{
		super(cm);
		bgImage = ImageHandler.getImage(ImageHandler.ImageType.treasure_room);
		poorKid = ImageHandler.getImage(ImageHandler.ImageType.poorKid);
		richKid = ImageHandler.getImage(ImageHandler.ImageType.richKid);
		bgWidth = bgImage.getWidth(null);
		bgHeight = bgImage.getHeight(null);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
		isRich = false;
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		
		AffineTransform tx2 = new AffineTransform();
		tx2.translate(midX, midY);
		tx2.scale(2, 2);
		
		g2.setTransform(tx2);
		g2.drawImage(bgImage, -bgWidth/2,-bgHeight/2,null);
		
		g2.setTransform(tx);
		g2.drawImage(richKid, -richKid.getWidth(null)/2 + 400,-richKid.getHeight(null)/2,null);
		g2.drawImage(poorKid, -poorKid.getWidth(null)/2 - 400,-poorKid.getHeight(null)/2,null);
		
		if(isRich)
		{
			 float alpha = 0.3f;
	         AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
	         Composite c = g2.getComposite();
	         g2.setComposite(alcom);
	         g2.fillRect(-richKid.getWidth(null)/2 + 400,-richKid.getHeight(null)/2,richKid.getWidth(null),richKid.getHeight(null));
	        
	         g2.setComposite(c);
	         g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
	         g2.drawRect(-richKid.getWidth(null)/2 + 400,-richKid.getHeight(null)/2,richKid.getWidth(null),richKid.getHeight(null));
		}
		else
		{
			 float alpha = 0.3f;
	         AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
	         Composite c = g2.getComposite();
	         g2.setComposite(alcom);
	         g2.fillRect(-poorKid.getWidth(null)/2 - 400,-poorKid.getHeight(null)/2,poorKid.getWidth(null),poorKid.getHeight(null));
	        
	         g2.setComposite(c);
	         g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
	         g2.drawRect(-poorKid.getWidth(null)/2 - 400,-poorKid.getHeight(null)/2,poorKid.getWidth(null),poorKid.getHeight(null));
		}
		
	}

	public void setSelected(Boolean select)
	{
		isRich = select;
	}
	
	public boolean getIsRich()
	{
		return isRich;
	}
	
	@Override
	public void update() {

	}

	@Override
	public void init() {
		try {
			cm.travelChoiceVoice();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			cm.getGameStateManager().next();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}


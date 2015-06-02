package model.gamestates;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

import model.entities.Player;
import model.entities.Rock;
import control.ControlManager;
import control.ImageHandler;

public class BoatGameState extends GameState implements ActionListener {

	private Player boat;
	private BufferedImage water, leftSide, rightSide, grass;
	private int screenWidth, screenHeight, backgroundPositionY;
	private ArrayList<Rock> rocks;
	
	
	public BoatGameState(ControlManager cm) {
		super(cm);
		boat = new Player(cm);
		backgroundPositionY = 0;
		Timer backgroundTimer = new Timer(1000/60,this);
		backgroundTimer.start();
		rocks = new ArrayList<>();
		
	}

	@Override
	public void draw(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING	,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
		Shape oldClip = g2.getClip();
	    //Draw the background grass:
	    TexturePaint p = new TexturePaint(grass, new Rectangle2D.Double(0, backgroundPositionY, 500, 500));
		g2.setPaint(p);
		g2.fill(new Rectangle2D.Double(0,0, screenWidth, screenHeight));
	    //Drawing main river:
		g2.setClip(new Rectangle2D.Double(screenWidth/4+10,0,screenWidth/2+110,screenHeight));
		p = new TexturePaint(water, new Rectangle2D.Double(0, backgroundPositionY, 500, 500));
		g2.setPaint(p);
		g2.fill(new Rectangle2D.Double(0,0, screenWidth, screenHeight));
		//Drawing left side of the river:
		p = new TexturePaint(leftSide, new Rectangle2D.Double(0, backgroundPositionY, 130, 500));
		g2.setPaint(p);
		AffineTransform old = g2.getTransform();
		AffineTransform tx = new AffineTransform();
		tx.translate(screenWidth/4,0);
		g2.setTransform(tx);
		g2.fill(new Rectangle2D.Double(0,0,130, screenHeight));
		//Drawing right side of the river:
		p = new TexturePaint(rightSide, new Rectangle2D.Double(0, backgroundPositionY+10, 130, 500));
		g2.setPaint(p);
		tx = new AffineTransform();
		tx.translate((screenWidth/4)*3,0);
		g2.setTransform(tx);
		g2.fill(new Rectangle2D.Double(0,0,130, screenHeight));
		//Drawing objects:
	    g2.setTransform(old);
	    for(Rock rock : rocks) 
			rock.draw(g2);
		boat.draw(g2);
		
	}

	@Override
	public void update() {
		boat.update();
		for(Rock rock : rocks)
			rock.update();
		//Randomly spawning rocks: 
		if(Math.floor((Math.random()*30)) == 3) {
			//System.out.println("spawn");
			Rock rock = new Rock(cm);
			rocks.add(rock);
			rock.init();
		}
			
		
	}

	@Override
	public void init() {
		water = ImageHandler.getImage(ImageHandler.ImageType.water);
		leftSide = ImageHandler.getImage(ImageHandler.ImageType.leftSideRiver);
		rightSide = ImageHandler.getImage(ImageHandler.ImageType.rightSideRiver);
		grass = ImageHandler.getImage(ImageHandler.ImageType.grass);
		screenWidth = cm.getWidth();
		screenHeight = cm.getHeight();
		boat.init();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		backgroundPositionY+=6;
	}

}

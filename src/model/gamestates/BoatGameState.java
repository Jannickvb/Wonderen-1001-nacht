package model.gamestates;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import model.entities.BoatCrash;
import model.entities.Foilage;
import model.entities.Pier;
import model.entities.Player;
import model.entities.Rock;
import control.ControlManager;
import control.ImageHandler;

public class BoatGameState extends GameState implements ActionListener {

	private Player boat;
	private Pier pier;
	private BoatCrash boatCrash;
	private BufferedImage grass;
	private int screenWidth, screenHeight, backgroundPositionY;
	private int width,height,midX,midY,bgWidth,bgHeight;
	private ArrayList<Rock> rocks;
	private ArrayList<Foilage> plants;
	private boolean scaledOnce = false;
	private boolean pressurePlate1; //Right foot
	private boolean pressurePlate2; //Left foot
	private boolean pressurePlate3; //Right foot
	private boolean pressurePlate4; //Left foot
	
	public BoatGameState(ControlManager cm) {
		super(cm);
		boat = new Player(cm);
		backgroundPositionY = 0;
		Timer backgroundTimer = new Timer(1000/60,this);
		backgroundTimer.start();
		rocks = new ArrayList<>(1000);
		plants = new ArrayList<>(1000);
	}

	@Override
	public void draw(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING	,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
	    TexturePaint tp = new TexturePaint(grass,new Rectangle2D.Double(0,backgroundPositionY,screenWidth,screenHeight));
	    g2.setPaint(tp);
	    g2.fill(new Rectangle2D.Double(0,0,screenWidth,screenHeight));
		//Drawing objects:   
	    for(Rock rock : rocks) 
			rock.draw(g2);
	    for(Foilage plant : plants) 
			plant.draw(g2);
	    pier.draw(g2);
	    if(!boat.isDead())
	    	boat.draw(g2);	
	    else 
	    	boatCrash.draw(g2);
	}

	@Override
	public void update() {
		width = cm.getWidth();
		height = cm.getHeight();
		if(width != 0 && !scaledOnce){
			grass = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.grass),width);
			scaledOnce = true;
		}
		bgWidth = grass.getWidth(null);
		bgHeight = grass.getHeight(null);
		midX = width/2;
		midY = height/2;
		boat.update();
		for(Rock rock : rocks) {
			if(rock.containsPoint(boat)) {
				collision();
			}
		}
		//Randomly spawning rocks: 
		if(Math.floor(Math.random()*25) == 3) {
			Rock rock = null;
			switch((int) Math.floor(Math.random()*4)) {
				case 0:
					rock = new Rock(cm,ImageHandler.getImage(ImageHandler.ImageType.rock1));
					break;
				case 1:
					rock = new Rock(cm,ImageHandler.getImage(ImageHandler.ImageType.rock2));
					break;
				case 2:
					rock = new Rock(cm,ImageHandler.getImage(ImageHandler.ImageType.rock3));
					break;
				case 3:
					rock = new Rock(cm,ImageHandler.getImage(ImageHandler.ImageType.rock4));
					break;		
			}
			rocks.add(rock);
			rock.init();
		}
		//Randomly spawning trees:
		if(Math.floor(Math.random()*6) == 3) {
			Foilage plant = null;
			switch((int) Math.floor(Math.random()*4)) {
				case 0:
					plant = new Foilage(cm,ImageHandler.getImage(ImageHandler.ImageType.tree1));
					break;
				case 1:
					plant = new Foilage(cm,ImageHandler.getImage(ImageHandler.ImageType.tree2));
					break;
				case 2:
					plant = new Foilage(cm,ImageHandler.getImage(ImageHandler.ImageType.tree3));
					break;
				case 3:
					plant = new Foilage(cm,ImageHandler.getImage(ImageHandler.ImageType.tree4));
					break;		
			}
			plants.add(plant);
			plant.init();
		}
		
		//Checking if trees or rocks are out of the screen:
		Iterator it = rocks.iterator();
		while(it.hasNext()) {
			Rock rock = (Rock) it.next();
			if(rock.isDead())
				it.remove();
		}
		
		it = plants.iterator();
		while(it.hasNext()) {
			Foilage rock = (Foilage) it.next();
			if(rock.isDead())
				it.remove();
		}
		
		if(boatCrash != null) {
			if(boatCrash.isDead()) {
				boatCrash = null;
				reset();
			}
		}
	}

	@Override
	public void init() {
		grass = ImageHandler.getImage(ImageHandler.ImageType.grass);
		screenWidth = cm.getWidth();
		screenHeight = cm.getHeight();
		boat.init();
		pier = new Pier(cm,screenHeight);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		backgroundPositionY+=6;
	}
	
	public void collision() {
		if(!boat.isDead()) {
			boatCrash = new BoatCrash(cm,boat.getPositionX(),boat.getPositionY());
			boat.collision();
		}
	}
	
	public void reset() {
		backgroundPositionY = 0;
		rocks = new ArrayList<>();
		boat.reset();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        	boat.setPressurePlates(2);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        	boat.setPressurePlates(1);
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			boat.setPressurePlates(4);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        	boat.setPressurePlates(3);
        }
	}

}

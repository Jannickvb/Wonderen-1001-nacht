package model.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
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
	private int backgroundPositionY;
	private ArrayList<Rock> rocks;
	private ArrayList<Foilage> plants;
	private int counter;
	private float fade;
	private boolean dead;
	private Timer backgroundTimer;
	
	public BoatGameState(ControlManager cm) {
		super(cm);
		counter = 0;
		boat = new Player(cm);
		backgroundPositionY = 0;
		fade = 0;
		backgroundTimer = new Timer(1000/60,this);
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
	    TexturePaint tp = new TexturePaint(grass,new Rectangle2D.Double(0,backgroundPositionY,ControlManager.screenWidth,ControlManager.screenHeight));
	    g2.setPaint(tp);
	    g2.fill(new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight));
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
	    Shape rect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		g2.setColor(new Color(0,0,0,fade));
		g2.fill(rect);
	    
	    
	}

	@Override
		public void update() {
			boat.update();
			for(Rock rock : rocks) {
				if(boat.containsPoint(rock)) {
					collision();
				}
			}
			//Randomly spawning rocks: 
			if(!dead) {
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
					counter++;
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
			}
			
			//Checking if trees or rocks are out of the screen:
			Iterator it = rocks.iterator();
			while(it.hasNext()) {
				Rock rock = (Rock) it.next();
				if(rock.isDead())
					it.remove();
				if(pier.isDead())
					rock.setTimer(false);
			}
			
			it = plants.iterator();
			while(it.hasNext()) {
				Foilage foilage = (Foilage) it.next();
				if(foilage.isDead())
					it.remove();
				if(pier.isDead())
					foilage.setTimer(false);
			}
			
			if(boatCrash != null) {
				if(boatCrash.isDead()) {
					boatCrash = null;
					reset();
				}
				else
					boatCrash.update();
			}
			
			if(counter >= 10){
				if(!dead) {
					endGame();
					dead = true;
					counter = 0;
				}
			}
			
			pier.update();
			
			if(pier.isDead()) {
				backgroundTimer.stop();
				pier.setTimer(false);
				boat.endGame();
			}
			
			if(boat.reachedEnd()) {
				if(counter < 20 && fade < 1) {
					fade += 0.1;
					counter++;
				}
				else
					cm.getGameStateManager().next();
			}
		}

	@Override
	public void init() {
		grass = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.grass));
		boat.init();
		pier = new Pier(cm,ControlManager.screenHeight);
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
		counter = 0;
		pier = new Pier(cm,ControlManager.screenHeight);
		boat.reset();
	}
	
	public void endGame() {
		pier.setDead(false);
		pier.setPositionY(-178);
		
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

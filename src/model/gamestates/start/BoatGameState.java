package model.gamestates.start;

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

import model.entities.Boat;
import model.entities.BoatCrash;
import model.entities.Pier;
import model.entities.Rock;
import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;

/**
 * The boat game state.
 * @author Wesley de Hek
 * @Version 1.8
 */
public class BoatGameState extends GameState{

	private Boat boat;
	private Pier pier;
	private BoatCrash boatCrash;
	private BufferedImage background, liveHeart;
	private int backgroundPositionY;
	private ArrayList<Rock> rocks;
	private int counter;
	private int lives;
	private float alpha;
	private Timer backgroundTimer;
	
	/**
	 * Constructor of the Boat game state.
	 * @param cm - The control manager of the game.
	 */
	public BoatGameState(ControlManager cm) {
		super(cm);
		backgroundPositionY = 0;
		counter = 0;
		alpha = 0;
		lives = 3;
		boat = new Boat(cm);
		rocks = new ArrayList<>(100);
		backgroundTimer = new Timer(1000/60,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundPositionY += 6;
			}
		});
		backgroundTimer.start();
	}

	/**
	 * Drawing the boat game state:
	 * @param g2 - The Graphics2D object.
	 */
	@Override
	public void draw(Graphics2D g2) {
		//Enabling AntiAliasing. 
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING	,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
	    //Drawing background: 
	    TexturePaint tp = new TexturePaint(background,new Rectangle2D.Double(0,backgroundPositionY,ControlManager.screenWidth,ControlManager.screenHeight));
	    g2.setPaint(tp);
	    g2.fill(new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight));
		//Drawing Rocks:   
	    for(Rock rock : rocks) 
			rock.draw(g2);
	    //Drawing the Pier:
	    pier.draw(g2);
	    //Drawing Lives:
	    for(int x = 0; x < lives; x++) 
			g2.drawImage(liveHeart,50+150*x,5,null);
	    //Drawing Boat or BoatCrash:
	    if(boatCrash == null)
	    	boat.draw(g2);	
	    else 
	    	boatCrash.draw(g2);
	    //Fade out effect:
	    Shape rect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		g2.setColor(new Color(0,0,0,alpha));
		g2.fill(rect); 
	}

	/**
	 * Updating the boat game state.
	 */
	@Override
	public void update() {
		//Updating the boat:
		boat.update();
		
		//Updating the Pier:
		pier.update();
		
		//Checking for collision:
		for(Rock rock : rocks) {
			if(boat.containsPoint(rock)) {
				collision();
			}
		}
		
		//Randomly spawning rocks: 
		if(!pier.isDead()) {
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
		}
		
		//Checking if rocks are out of the screen:
		Iterator it = rocks.iterator();
		while(it.hasNext()) {
			Rock rock = (Rock) it.next();
			if(rock.isDead())
				it.remove();
			if(pier.isDead())
				rock.setTimer(false);
		}
		
		//Checking if crash animation is over:
		if(boatCrash != null) 
			if(boatCrash.isDead()) 
				reset();
		
		//Reaching the end of the game:
		if(counter == 10) {
			counter++;
			pier.setDead(false);
			pier.setPositionY(-178);
		}
				
		//Pier fully popped out of the top of the screen & also checking if boat collides with the pier:
		if(pier.isDead()) {
			backgroundTimer.stop();
			boat.setEndTimer(true);
			if(boat.containsPoint(pier))
				boat.setEndTimer(false);
			else
				boat.setEndTimer(true);
		}
		
		//Boat reached top of the screen:
		if(boat.reachedEnd()) {
			if(alpha < 1) 
				alpha += 0.1;
			else
				cm.getGameStateManager().next();
		}		
	}

	/**
	 * Initializes the boat game state.
	 * Loads background and live images.
	 * Makes a pier and initializes boat object.
	 */
	@Override
	public void init() {
		background = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.grass));
		liveHeart = ImageHandler.getImage(ImageHandler.ImageType.heart);
		pier = new Pier(cm,ControlManager.screenHeight);
		boat.init();
	}
	
	/**
	 * Called on collision, will subtract a live and reset game.
	 */
	public void collision() {
		if(boatCrash == null) {
			if(lives > 0) {
				lives--;
				boatCrash = new BoatCrash(cm,boat.getPositionX(),boat.getPositionY());
				boat.collision();
			}
			else {
				boat.setReachedEnd(true); //Alternate ending when dead <- here
			}
		}
	}
	
	/**
	 * Resets the game.
	 */
	public void reset() {
		pier = new Pier(cm,ControlManager.screenHeight);
		rocks = new ArrayList<>();
		backgroundPositionY = 0;
		backgroundTimer.start();
		boatCrash = null;
		alpha = 0f;
		counter = 0;
		boat.reset();
	}
	
	//Just for testing:
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        	boat.setPressurePlates(2);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        	boat.setPressurePlates(1);
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			boat.setPressurePlates(4);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        	boat.setPressurePlates(3);
        }
	}

}

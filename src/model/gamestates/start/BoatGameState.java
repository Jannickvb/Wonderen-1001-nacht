package model.gamestates.start;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import model.entities.Boat;
import model.entities.BoatCrash;
import model.entities.Coin;
import model.entities.Entity;
import model.entities.Pier;
import model.entities.Rock;
import model.entities.Upgrade;
import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;

/**
 * The boat game state.
 * @author Wesley de Hek
 * @Version 1.9
 */
public class BoatGameState extends GameState{

	private Boat boat;
	private Pier pier;
	private BoatCrash boatCrash;
	private BufferedImage background, liveHeart;
	private int backgroundPositionY;
	private ArrayList<Rock> rocks;
	private ArrayList<Upgrade> upgrades;
	private ArrayList<Coin> coins;
	private int counter;
	private int lives;
	private int points;
	private int pointCounter;
	private String endText;
	private float alpha;
	
	/**
	 * Constructor of the Boat game state.
	 * @param cm - The control manager of the game.
	 */
	public BoatGameState(ControlManager cm) {
		super(cm);
		backgroundPositionY = 0;
		counter = 0;
		points = 0;
		lives = 3;
		alpha = 0;
		boat = new Boat(cm);
		rocks = new ArrayList<>(100);
		upgrades = new ArrayList<>(100);
		coins = new ArrayList<>(100);
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
	    //Drawing upgrades:
	    for(Upgrade upgrade : upgrades)
	    	upgrade.draw(g2);
	    //Drawing coins: 
	    for(Coin coin : coins)
	    	coin.draw(g2);
	    //Drawing the Pier:
	    pier.draw(g2);
	    g2.setColor(Color.WHITE);
		g2.setFont(new Font("Verdana",Font.BOLD,50)); 
	    if(!boat.reachedEnd()) {
	    	//Drawing Lives:
		    for(int x = 0; x < lives; x++) 
				g2.drawImage(liveHeart,50+150*x,5,null);
		    //Drawing points: 
			g2.drawString("Punten: " + points,50,170);
	    }
	    //Drawing Boat or BoatCrash:
	    if(boatCrash == null)
	    	boat.draw(g2);	
	    else 
	    	boatCrash.draw(g2);
	    //Drawing end screen: 
	    if(boat.reachedEnd()) {
	    	drawCenteredText(endText, g2, ControlManager.screenHeight/2-200);
	    	drawCenteredText("Behaalde punten: " + pointCounter, g2, ControlManager.screenHeight/2-100);
	    	if(pointCounter == points)
	    		drawCenteredText("Druk op A om verder te gaan", g2, ControlManager.screenHeight/2);
	    }
	    else {
	    	//Drawing upgrade thing:
	    	
	    }
	    //Fade out effect:
	    Shape rect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		g2.setColor(new Color(0,0,0,alpha));
		g2.fill(rect); 
		
	}
	
	/**
	 * Method that draws given text in the center of the screen.
	 * @param text - The text you want to display.
	 * @param g2 - The graphics2D object.
	 * @param y - The y position of the text.
	 */
	public void drawCenteredText(String text, Graphics2D g2, int y) {
		int x = (ControlManager.screenWidth-g2.getFontMetrics().stringWidth(text))/2;
		g2.drawString(text, x, y);
	}

	/**
	 * Updating the boat game state.
	 */
	@Override
	public void update() {
		//Updating the boat:
		boat.update();
		
		//Updating background: 
		if(!pier.isDead())
			backgroundPositionY += 6;
		
		//Updating the Pier:
		pier.update();
		
		//Checking for collision with rocks:
		for(Rock rock : rocks) {
			if(boat.containsPoint(rock)) {
				collision();
			}
		}
		
		//Checking for collision with upgrades:
		for(Upgrade upgrade : upgrades) {
			if(boat.containsPoint(upgrade)) {
				collisionUpgrade(upgrade);
			}
		}
		
		//Checking for collision with coins:
		for(Coin coin : coins) {
			if(boat.containsPoint(coin)) {
				coin.playSound();
				coin.setDead(true);
				points += 20;
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
				//Checking if rock isnt overlapping
				if(checkCollision(rock)) 
					rock.setDead(true);
			}
		}
		
		//Randomly spawning upgrades: 
		if(!pier.isDead()) {
			if(Math.floor(Math.random()*95) == 3) {
				Upgrade upgrade = new Upgrade(cm);
				upgrade.init();
				if(checkCollision(upgrade)) 
					upgrade.setDead(true);
				upgrades.add(upgrade);
					
			}
		}
		
		//Randomly spawning coins: 
		if(!pier.isDead()) {
			if(Math.floor(Math.random()*25) == 3) {
				Coin coin = new Coin(cm);
				coins.add(coin);
				coin.init();
				if(checkCollision(coin))
					coin.setDead(true);
			}
		}
		
		//Checking if rocks are out of the screen & if rocks are dead & if rocks are overlapping:
		Iterator<Rock> it = rocks.iterator();
		while(it.hasNext()) {
			Rock rock = (Rock) it.next();
			if(rock.isDead())
				it.remove();
			if(!pier.isDead())
				rock.update();
		}
		
		//Checking if upgrades are out of the screen & if upgrades are dead & if upgrades are overlapping:
		Iterator<Upgrade> itU = upgrades.iterator();
		while(itU.hasNext()) {
			Upgrade upgrade = (Upgrade) itU.next();
			if(upgrade.isDead())
				itU.remove();
			if(!pier.isDead())
				upgrade.setMove(true);
			else
				upgrade.setMove(false);
			upgrade.update();
		}
		
		//Checking if rocks are out of the screen & if rocks are dead & if rocks are overlapping:
		Iterator<Coin> itC = coins.iterator();
		while(itC.hasNext()) {
			Coin coin = (Coin) itC.next();
			if(coin.isDead())
				itC.remove();
			if(!pier.isDead())
				coin.update();
		}
		
		//Checking if crash animation is over:
		if(boatCrash != null) {
			boatCrash.update();
			if(boatCrash.isDead()) 
				reset();
		}
		//Reaching the end of the game:
		if(counter == 50) {
			counter++;
			pier.setDead(false);
			pier.setPositionY(-178);
		}
				
		//Pier fully popped out of the top of the screen & also checking if boat collides with the pier:
		if(pier.isDead()) {
			boat.setCollisionPier(false);
			if(boat.containsPoint(pier))
				boat.setCollisionPier(true);
			else
				boat.setCollisionPier(false);
		}
		
		if(boat.reachedEnd()) {
			if(pointCounter == 0)
				endText = "Gefeliciteerd!";
			if(pointCounter < points) {
				if(alpha < 0.2)
					alpha += 0.0033;
				pointCounter+=3;
			}
			else {
				pointCounter = points;
			}
		}
		//Boat reached top of the screen: <- when player presses A to continue;
		if(boat.reachedEnd()) {
			if(pointCounter == points)
			{
				if(cm.getInputHandler().isA1Pressed() && cm.getInputHandler().isA2Pressed())
					cm.getGameStateManager().next();	
				
			}
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
			if(lives > 1) {
				lives--;
				if(!pier.isDead())
					boatCrash = new BoatCrash(cm,boat.getPositionX(),boat.getPositionY(),true);
				else
					boatCrash = new BoatCrash(cm,boat.getPositionX(),boat.getPositionY(),false);
				boat.collision();
			}
			else {
				endText = "Helaas! U heeft het eind niet bereikt";
				boat.setReachedEnd(true); //Alternate ending when dead <- here
				pier.setDead(true);
			}
		}
	}
	
	/**
	 * Called on collision with a upgrade.
	 * @param upgrade - The upgrade that collided.
	 */
	public void collisionUpgrade(Upgrade upgrade) {
		upgrade.playSound();
		upgrade.setDead(true);
		points += 100;
	}
	
	
	/**
	 * Resets the game.
	 */
	public void reset() {
		pier = new Pier(cm,ControlManager.screenHeight);
		rocks = new ArrayList<Rock>();
		coins = new ArrayList<Coin>();
		upgrades = new ArrayList<Upgrade>();
		backgroundPositionY = 0;
		boatCrash = null;
		alpha = 0f;
		counter = 0;
		if(points > 200)
			points -= 200;
		else 
			points = 0;
		pointCounter = 0;
		boat.reset();
	}
	
	public boolean checkCollision(Entity object) {
		//Overlap with rock:
		for(Rock rock2 : rocks) 
			if(object.containsPoint(rock2)) 
				return true;
		//Overlap met coin:
		for(Coin coin : coins)
			if(object.containsPoint(coin))
				return true;
		//Overlap met upgrade:
		for(Upgrade upgrade : upgrades)
			if(object.containsPoint(upgrade))
				return true;
		//Overlap met pier:
		if(pier != null)
			if(object.containsPoint(pier))
				return true;
		return false;
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
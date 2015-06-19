package model.gamestates.trollpath;

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

import model.entities.Box;
import model.entities.Coin;
import model.entities.Entity;
import model.entities.Palace;
import model.entities.Person;
import model.entities.PlayerHit;
import model.entities.Box;
import model.entities.Upgrade;
import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;

public class RichGameState extends GameState{

		private Person guy;
		private Palace palace;
		private PlayerHit playerHit;
		private BufferedImage background, liveHeart;
		private int backgroundPositionY;
		private ArrayList<Box> boxes;
		private ArrayList<Upgrade> upgrades;
		private ArrayList<Coin> coins;
		private int counter;
		private int lives;
		private int points;
		private int pointCounter;
		private String endText;
		private float alpha;
		
		/**
		 * Constructor of the Rich game state.
		 * @param cm - The control manager of the game.
		 */
		public RichGameState(ControlManager cm) {
			super(cm);
			counter = 0;
			guy = new Person(cm);
			backgroundPositionY = 0;
			points = 0;
			alpha = 0;
			lives = 3;
			boxes = new ArrayList<>(100);
			upgrades = new ArrayList<>(100);
			coins = new ArrayList<>(100);
		}

		/**
		 * Drawing the rich game state:
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
		    for(Box box : boxes) 
				box.draw(g2);
		    //Drawing upgrades:
		    for(Upgrade upgrade : upgrades)
		    	upgrade.draw(g2);
		    //Drawing coins: 
		    for(Coin coin : coins)
		    	coin.draw(g2);
		    //Drawing the Pier:
		    palace.draw(g2);
		    g2.setColor(Color.WHITE);
			g2.setFont(new Font("Verdana",Font.BOLD,50)); 
		    if(!guy.reachedEnd()) {
		    	//Drawing Lives:
			    for(int x = 0; x < lives; x++) 
					g2.drawImage(liveHeart,50+150*x,5,null);
			    //Drawing points: 
				g2.drawString("Punten: " + points,50,170);
		    }
		    //Drawing Boat or BoatCrash:
		    if(playerHit == null)
		    	guy.draw(g2);	
		    else 
		    	playerHit.draw(g2);
		    //Drawing end screen: 
		    if(guy.reachedEnd()) {
		    	ImageHandler.drawCenteredText(endText, g2, ControlManager.screenHeight/2-200);
		    	ImageHandler.drawCenteredText("Behaalde punten: " + pointCounter, g2, ControlManager.screenHeight/2-100);
		    	if(pointCounter == points)
		    		ImageHandler.drawCenteredText("Druk op A om verder te gaan", g2, ControlManager.screenHeight/2);
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
		 * Updating the rich game state.
		 */
		@Override
		public void update() {
			//Updating the boat:
			guy.update();
			
			//Updating background: 
			if(!palace.isDead())
				backgroundPositionY += 6;
			
			//Updating the Pier:
			palace.update();
			
			//Checking for collision with rocks:
			for(Box box : boxes) {
				if(guy.containsPoint(box)) {
					collision();
				}
			}
			
			//Checking for collision with upgrades:
			for(Upgrade upgrade : upgrades) {
				if(guy.containsPoint(upgrade)) {
					collisionUpgrade(upgrade);
				}
			}
			
			//Checking for collision with coins:
			for(Coin coin : coins) {
				if(guy.containsPoint(coin)) {
					coin.playSound();
					coin.setDead(true);
					points += 20;
				}
			}
					
			//Randomly spawning rocks: 
			if(!palace.isDead()) {
				if(Math.floor(Math.random()*25) == 3) {
					Box box = null;
					switch((int) Math.floor(Math.random()*4)) {
						case 0:
							box = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box1));
							break;
						case 1:
							box = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box2));
							break;
						case 2:
							box = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box3));
							break;
						case 3:
							box = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box4));
							break;	
						case 4:
							box = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box5));
							break;
						case 5:
							box = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box6));
							break;
						case 6:
							box = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box7));
							break;
					}
					counter++;
					boxes.add(box);
					box.init();
					//Checking if rock isnt overlapping
					if(checkCollision(box)) 
						box.setDead(true);
				}
			}
			
			//Randomly spawning upgrades: 
			if(!palace.isDead()) {
				if(Math.floor(Math.random()*95) == 3) {
					Upgrade upgrade = new Upgrade(cm);
					upgrade.init();
					if(checkCollision(upgrade)) 
						upgrade.setDead(true);
					upgrades.add(upgrade);
						
				}
			}
			
			//Randomly spawning coins: 
			if(!palace.isDead()) {
				if(Math.floor(Math.random()*25) == 3) {
					Coin coin = new Coin(cm);
					coins.add(coin);
					coin.init();
					if(checkCollision(coin))
						coin.setDead(true);
				}
			}
			
			//Checking if rocks are out of the screen & if rocks are dead & if rocks are overlapping:
			Iterator<Box> it = boxes.iterator();
			while(it.hasNext()) {
				Box rock = (Box) it.next();
				if(rock.isDead())
					it.remove();
				if(!palace.isDead())
					rock.update();
			}
			
			//Checking if upgrades are out of the screen & if upgrades are dead & if upgrades are overlapping:
			Iterator<Upgrade> itU = upgrades.iterator();
			while(itU.hasNext()) {
				Upgrade upgrade = (Upgrade) itU.next();
				if(upgrade.isDead())
					itU.remove();
				if(!palace.isDead())
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
				if(!palace.isDead())
					coin.update();
			}
			
			//Checking if crash animation is over:
			if(playerHit != null) {
				playerHit.update();
				if(playerHit.isDead()) 
					reset();
			}
			//Reaching the end of the game:
			if(counter == 50) {
				counter++;
				palace.setDead(false);
				palace.setPositionY(-178);
			}
					
			//Palace fully popped out of the top of the screen & also checking if person collides with the palace:
			if(palace.isDead()) {
				guy.setCollisionPalace(false);
				if(guy.containsPoint(palace)) {
					guy.setReachedEnd(true);
					guy.setCollisionPalace(true);
				}
			}
			
			if(guy.reachedEnd()) {
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
//			if(boat.reachedEnd()) {
//				if(alpha < 0.95) 
//					alpha += 0.033;
//				else
//					cm.getGameStateManager().next();
//			}	
		}
		
		/**
		 * Initializes the rich game state.
		 * Loads background and live images.
		 * Makes a palace and initializes person object.
		 */
		@Override
		public void init() {
			background = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.richcity));
			liveHeart = ImageHandler.getImage(ImageHandler.ImageType.heart);
			palace = new Palace(cm,ControlManager.screenHeight+400);
			guy.init();
		}

		/**
		 * Called on collision, will subtract a live and reset game.
		 */
		public void collision() {
			if(playerHit == null) {
				if(lives > 1) {
					lives--;
					if(!palace.isDead())
						playerHit = new PlayerHit(cm,guy.getPositionX(),guy.getPositionY(),true);
					else
						playerHit = new PlayerHit(cm,guy.getPositionX(),guy.getPositionY(),false);
					guy.collision();
				}
				else {
					endText = "Helaas! U heeft het eind niet bereikt";
					guy.setReachedEnd(true); //Alternate ending when dead <- here
					palace.setDead(true);
				}
			}
		}
		
		/**
		 * Resets the game.
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
			palace = new Palace(cm,ControlManager.screenHeight);
			boxes = new ArrayList<Box>();
			coins = new ArrayList<Coin>();
			upgrades = new ArrayList<Upgrade>();
			backgroundPositionY = 0;
			playerHit = null;
			alpha = 0f;
			counter = 0;
			if(points > 200)
				points -= 200;
			else 
				points = 0;
			pointCounter = 0;
			guy.reset();
		}
		
		public boolean checkCollision(Entity object) {
			//Overlap with rock:
			for(Box box : boxes) 
				if(object.containsPoint(box)) 
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
			if(palace != null)
				if(object.containsPoint(palace))
					return true;
			return false;
		}
		
		//Just for testing:
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        	guy.setPressurePlates(2);
	        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
	        	guy.setPressurePlates(1);
	        }
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				guy.setPressurePlates(4);
	        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
	        	guy.setPressurePlates(3);
	        }
		}
}
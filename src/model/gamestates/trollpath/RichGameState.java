package model.gamestates.trollpath;

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

import model.entities.Box;
import model.entities.Palace;
import model.entities.Person;
import model.entities.PlayerHit;
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
		private int counter;
		private int lives;
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
			alpha = 0;
			lives = 3;
			boxes = new ArrayList<>(1000);
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
		    //Drawing Boxes:    
		    for(Box b : boxes) 
				b.draw(g2);
		    //Drawing the Palace:
		    palace.draw(g2);
		    //Drawing Lives:
		    for(int x = 0; x < lives; x++) 
				g2.drawImage(liveHeart,50+150*x,5,null);
		    //Drawing Person or PlayerHit:
		    if(playerHit == null)
		    	guy.draw(g2);	
		    else 
		    	playerHit.draw(g2);
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
			//Updating the Person:
			guy.update();
			
			//Updating the Palace:
			palace.update();
			
			//Updating palace:
			if(!palace.isDead())
				backgroundPositionY += 6;
			
			//Checking for collision:
			for(Box b : boxes) {
				if(guy.containsPoint(b)) {
					collision();
				}
			}
			
			//Randomly spawning boxes: 
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
					//Checking if boxes don't overlap.
					for(Box box2 : boxes)
						if(box.containsPoint(box2))
							box.setDead(true);
						else if(palace != null)
							if(box.containsPoint(palace))
								box.setDead(true);
					box.init();
				}
			}
			
			//Checking if boxes are out of the screen:
			Iterator it = boxes.iterator();
			while(it.hasNext()) {
				Box b = (Box) it.next();
				if(b.isDead())
					it.remove();
				if(!palace.isDead())
					b.update();
			}

			//Checking if crash animation is over:
			if(playerHit != null) {
				playerHit.update();
				if(playerHit.isDead()) 
					reset();
			}
						
			//Reaching the end of the game:
			if(counter == 15) {
				counter++;
				palace.setDead(false);
				palace.setPositionY(-100);
			}
					
			//Palace fully popped out of the top of the screen & also checking if person collides with the palace:
			if(palace.isDead()) {
				guy.setCollisionPalace(false);
				if(guy.containsPoint(palace)) {
					guy.setReachedEnd(true);
					guy.setCollisionPalace(true);
				}
			}	
			
			//Boat reached top of the screen:
			if(guy.reachedEnd()) {
				if(alpha < 0.95) 
					alpha += 0.033;
				else
					cm.getGameStateManager().next();
			}
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
				if(lives > 0) {
					lives--;
					if(!palace.isDead())
						playerHit = new PlayerHit(cm,guy.getPositionX(),guy.getPositionY(),true);
					else
						playerHit = new PlayerHit(cm,guy.getPositionX(),guy.getPositionY(),false);
					guy.collision();
				}
				else {
					guy.setReachedEnd(true); //Alternate ending when dead <- here
				}
			}
		}
		
		/**
		 * Resets the game.
		 */
		public void reset() {
			palace = new Palace(cm,ControlManager.screenHeight+200);
			boxes = new ArrayList<>(200);
			backgroundPositionY = 0;
			playerHit = null;
			alpha = 0f;
			counter = 0;
			guy.reset();
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
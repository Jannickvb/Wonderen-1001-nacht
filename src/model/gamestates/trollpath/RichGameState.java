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
		private BufferedImage background, liveHeart;
		private PlayerHit hit;
		private int backgroundPositionY;
		private ArrayList<Box> boxes;
		private Palace palace;
		private int counter;
		private int lives;
		private float alpha;
		private Timer backgroundTimer, endFader;
		
		public RichGameState(ControlManager cm) {
			super(cm);
			counter = 0;
			guy = new Person(cm);
			backgroundPositionY = 0;
			alpha = 0;
			lives = 3;
			boxes = new ArrayList<>(1000);
			backgroundTimer = new Timer(1000/60,new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					backgroundPositionY += 6;
				}
			});
			backgroundTimer.start();
		}

		@Override
		public void draw(Graphics2D g2) {
			RenderingHints rh = new RenderingHints(
		             RenderingHints.KEY_ANTIALIASING	,
		             RenderingHints.VALUE_ANTIALIAS_ON);
		    g2.setRenderingHints(rh);
		    TexturePaint tp = new TexturePaint(background,new Rectangle2D.Double(0,backgroundPositionY,ControlManager.screenWidth,ControlManager.screenHeight));
		    g2.setPaint(tp);
		    g2.fill(new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight));
			  
		    for(Box b : boxes) 
				b.draw(g2);
		    palace.draw(g2);
		    for(int x = 0; x < lives; x++) 
				g2.drawImage(liveHeart,50+150*x,5,null);
		    //Drawing Boat or BoatCrash:
		    if(hit == null)
		    	guy.draw(g2);	
		    else 
		    	hit.draw(g2);
		    //Fade out effect:
		    Shape rect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
			try{
				g2.setColor(new Color(0,0,0,alpha));
			}catch(IllegalArgumentException e){
				cm.getGameStateManager().next();
				cm.getGameStateManager().next();
			}
			g2.fill(rect); 
		}
		
		
		@Override
		public void update() {
			//Updating the boat:
			guy.update();
			
			//Updating the Pier:
			palace.update();
			
			//Checking for collision:
			for(Box b : boxes) {
				if(guy.containsPoint(b)) {
					collision();
				}
			}
			
			//Randomly spawning rocks: 
			if(!palace.isDead()) {
				if(Math.floor(Math.random()*25) == 3) {
					Box rock = null;
					switch((int) Math.floor(Math.random()*4)) {
						case 0:
							rock = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box1));
							break;
						case 1:
							rock = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box2));
							break;
						case 2:
							rock = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box3));
							break;
						case 3:
							rock = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box4));
							break;		
						case 4:
							rock = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box5));
							break;
						case 5:
							rock = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box6));
							break;
						case 6:
							rock = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box7));
							break;
					}
				counter++;
				boxes.add(rock);
				rock.init();
			}
			
			//Checking if rocks are out of the screen:
			Iterator it = boxes.iterator();
			while(it.hasNext()) {
				Box b = (Box) it.next();
				if(b.isDead())
					it.remove();
				if(palace.isDead())
					b.setTimer(false);
			}

			//Checking if crash animation is over:
			if(hit != null) 
				if(hit.isDead()) 
					reset();
			
			//Reaching the end of the game:
			if(counter == 15) {
				counter++;
				palace.setDead(true);
				palace.setPositionY(-100);
			}
					
			//Pier fully popped out of the top of the screen & also checking if boat collides with the pier:
			if(palace.isDead()) {
				backgroundTimer.stop();
				guy.setEndTimer(true);
				if(guy.containsPoint(palace)){
					guy.setEndTimer(false);
					
					}else{
						for(Box b: boxes){
							b.setTimer(false);
						}
						
					endFader.start();
					guy.setEndTimer(true);
				}
			}
			}
		}
		
		public void setAlpha(float i){
			this.alpha = i;
		}
		
		
		@Override
		public void init() {
			background = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.richcity));
			liveHeart = ImageHandler.getImage(ImageHandler.ImageType.heart);
			endFader = new Timer(1000/60,new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if(guy.getPositionY() > 40 && alpha < 1){
						alpha += 0.01;
					}else{
						alpha = 0.99f;
					}
				}
			});
			palace = new Palace(cm,ControlManager.screenHeight+400);
			guy.init();
		}

		public void collision() {
			if(hit == null) {
				if(lives > 0) {
					lives--;
					hit = new PlayerHit(cm,guy.getPositionX(),guy.getPositionY());
					guy.collision();
				}
				else {
					guy.setReachedEnd(true); //Alternate ending when dead <- here
				}
			}
		}
		
		public void reset() {
			palace = new Palace(cm,ControlManager.screenHeight+200);
			boxes = new ArrayList<>(200);
			backgroundPositionY = 0;
			hit = null;
			alpha = 0f;
			counter = 0;
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



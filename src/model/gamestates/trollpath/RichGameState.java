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

public class RichGameState extends GameState implements ActionListener{

		private Person guy;
		private BufferedImage grass;
		private PlayerHit boatCrash;
		private int backgroundPositionY;
		private ArrayList<Box> boxes;
		private Palace palace;
		private int counter;
		private float fade;
		private boolean dead;
		private Timer backgroundTimer;
		
		public RichGameState(ControlManager cm) {
			super(cm);
			counter = 0;
			guy = new Person(cm);
			backgroundPositionY = 0;
			fade = 0;
			backgroundTimer = new Timer(1000/60,this);
			backgroundTimer.start();
			boxes = new ArrayList<>(1000);
			palace = new Palace(cm,ControlManager.screenHeight+200);
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
		    for(Box b : boxes) 
				b.draw(g2);
		    palace.draw(g2);
		    if(!guy.isDead())
		    	guy.draw(g2);
		    else
		    	boatCrash.draw(g2);
		    
		    Shape rect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
			g2.setColor(new Color(0,0,0,fade));
			g2.fill(rect);
		    
		    
		}

		@Override
			public void update() {
				guy.update();
				for(Box b : boxes) {
					if(guy.containsPoint(b)) {
						collision();
					}
				}
				//Randomly spawning rocks: 
				if(!dead) {
					if(Math.floor(Math.random()*25) == 3) {
						Box b = null;
						switch((int) Math.floor(Math.random()*6)) {
							case 0:
								b = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box1));
								break;
							case 1:
								b = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box2));
								break;
							case 2:
								b = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box3));
								break;
							case 3:
								b = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box4));
								break;		
							case 4:
								b = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box5));
								break;
							case 5:
								b = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box6));
								break;
							case 6:
								b = new Box(cm,ImageHandler.getImage(ImageHandler.ImageType.box7));
								break;	
						}
						counter++;
						boxes.add(b);
						b.init();
					}
				}
				
				//Checking if trees or rocks are out of the screen:
				Iterator it = boxes.iterator();
				while(it.hasNext()) {
					Box b= (Box) it.next();
					if(b.isDead())
						it.remove();
					if(palace.isDead()){
						b.setTimer(false);
					}
				}
				
				if(counter >= 10){
					if(!dead) {
						endGame();
						dead = true;
						counter = 0;
					}
				}
				
				if(palace.isDead()){
					backgroundTimer.stop();
					palace.setTimer(false);
					guy.endGame();
				}
				
				if(boatCrash != null) {
					if(boatCrash.isDead()) {
						boatCrash = null;
						reset();
					}
					else
						boatCrash.update();
				}
				
				if(guy.reachedEnd()) {
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
			grass = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.richcity));
			guy.init();
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			backgroundPositionY+=6;
		}
		
		public void collision() {
			if(!guy.isDead()) {
				boatCrash = new PlayerHit(cm,guy.getPositionX(),guy.getPositionY());
				guy.collision();
			}
		}
		
		public void reset() {
			backgroundPositionY = 0;
			boxes = new ArrayList<>();
			counter = 0;
			guy.reset();
		}
		
		public void endGame() {
			palace.setDead(false);
			palace.setPositionY(-178);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        	guy.setPressurePlates(2);
	        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
	        	guy.setPressurePlates(1);
	        }
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				guy.setPressurePlates(4);
	        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
	        	guy.setPressurePlates(3);
	        }
		}

	}



package model.gamestates.magepath;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.Timer;

import model.entities.Particle;
import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;

public class Mage2State extends GameState implements ActionListener{
	
	private Image image;
	private int midX,midY,bgWidth,bgHeight;
	ArrayList<Particle> particles = new ArrayList<Particle>(5000);
	private boolean anim;
	private Random rand;
	private int x1,y1;
	private double counter;
	private double reversecounter;
	private Timer timer;
	
	public Mage2State(ControlManager cm)
	{
		super(cm);
		image = ImageHandler.getImage(ImageHandler.ImageType.mage2);
		midX = cm.screenWidth/2;
		midY = cm.screenHeight/2;
		bgWidth = image.getWidth(null);
		bgHeight = image.getHeight(null);
		int x1 = this.midX;
		int y1 = this.midY;
		this.counter = 0;
		this.reversecounter = 20;
		rand = new Random();
		timer = new Timer(1000/60,this);
		timer.start();
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(image, -bgWidth/2,-bgHeight/2,null);
		for(Particle particle: particles){
	        particle.paintComponent(g2);
		}
		
	}

	@Override
	public void update() {
		if(anim){
		if(counter < 20){
		addParticle(x1,y1,0);addParticle(x1,y1,0);
		addParticle(x1,y1,0);addParticle(x1,y1,0);
		addParticle(x1,y1,0);addParticle(x1,y1,0);
		addParticle(x1,y1,0);addParticle(x1,y1,0);
		
		Iterator<Particle> i = particles.iterator();
			while (i.hasNext()) {
			   Particle p = i.next(); 
			   if(p.getLife() == 100){
			   i.remove();
			   }
		}
			
		int x2 = (int)(20*counter*(Math.cos(counter)));
		int y2= (int)(20*counter*(Math.sin(counter)));
				
		this.x1 = x2;
		this.y1 = y2;
		
		counter += 0.1;
		}else if(reversecounter ==0){
			int a1 = 0;
			int b1 = 0;
			
			addParticle(a1,b1,1);addParticle(a1,b1,1);
			addParticle(a1,b1,1);addParticle(a1,b1,1);
			addParticle(a1,b1,1);addParticle(a1,b1,1);
			addParticle(a1,b1,1);addParticle(a1,b1,1);
			
			Iterator<Particle> i = particles.iterator();
				while (i.hasNext()) {
				   Particle p = i.next(); 
				   if(p.getLife() == 300){
				   i.remove();
				   }
			}
		}else {
			
			addParticle(x1,y1,0);addParticle(x1,y1,0);
			addParticle(x1,y1,0);addParticle(x1,y1,0);
			addParticle(x1,y1,0);addParticle(x1,y1,0);
			addParticle(x1,y1,0);addParticle(x1,y1,0);
				
			int x2 = (int)(20*reversecounter*(Math.cos(reversecounter)));
			int y2= (int)(20*reversecounter*(Math.sin(reversecounter)));
					
			this.x1 = x2;
			this.y1 = y2;
						
			int a1 = 0;
			int b1 = 0;
			
			addParticle(a1,b1,1);addParticle(a1,b1,1);
			addParticle(a1,b1,1);addParticle(a1,b1,1);
			addParticle(a1,b1,1);addParticle(a1,b1,1);
			addParticle(a1,b1,1);addParticle(a1,b1,1);
			
			Iterator<Particle> i = particles.iterator();
				while (i.hasNext()) {
				   Particle p = i.next(); 
				   if(p.getLife() == 300){
				   i.remove();
				   }
			}
			
				reversecounter -= 0.1;
				
		}
		}
	}
	
	public void addParticle(int x, int y, int life){
        int size = (int)(rand.nextInt(20)1.1);
        particles.add(new Particle(x,y,size,life));
    }  
	
	private void setAnimation(boolean bool){
		this.anim = bool;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(image.equals(ImageHandler.getImage(ImageHandler.ImageType.mage2))){
		try {
			cm.playMageTalk2();
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	try {
			            		cm.getMagicSound();
							} catch (LineUnavailableException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            	setAnimation(true);
			            	
			            	new java.util.Timer().schedule( 
			    			        new java.util.TimerTask() {
			    			            @Override
			    			            public void run() {
			    			                cm.getGameStateManager().next();
			    			            }
			    			        }, 
			    			        9500
			    			);
			            }
			        }, 
			        9500
			);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		}
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	for(Particle particle: particles){
			particle.update(true); 
	}
	Iterator<Particle> i = particles.iterator();
	while (i.hasNext()) {
	   Particle p = i.next(); 
	   if(p.getLife() == 100){
	   i.remove();
	   }
	}
	}
}

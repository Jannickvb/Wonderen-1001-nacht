package model.gamestates.magepath;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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
	
	private BufferedImage foreground,background;
	private boolean fadeIn= true,fadeOut = false,anim;
	private int bgPosY,frame = 0;
	private float opacity = 1f;
	private Rectangle2D tpRect,fade;
	private int midX,midY;
	ArrayList<Particle> particles = new ArrayList<Particle>(5000);
	private Random rand;
	private int x1,y1;
	private int a1,b1;
	private double counter;
	private double reversecounter;
	private Ellipse2D ellipse;
	private Timer timer;
	private double ellipseWidth,ellipseHeight,ellipseX,ellipseY;
	
	public Mage2State(ControlManager cm)
	{
		super(cm);
		foreground = ImageHandler.getImage(ImageHandler.ImageType.magestate);
		background = ImageHandler.getImage(ImageHandler.ImageType.mirror_bg);
		tpRect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		fade = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
		this.x1 = this.midX;
		this.y1 = this.midY;
		this.a1 = this.midX;
		this.b1 = this.midY;
		this.counter = 0;
		this.reversecounter = 20;
		this.ellipseWidth = 0;
		this.ellipseHeight =0;
		this.ellipseX = 0;
		this.ellipseY = 0;
		this.ellipse = new Ellipse2D.Double(ellipseWidth, ellipseHeight, ellipseX, ellipseY);
		rand = new Random();
		timer = new Timer(1000/60,this);
		timer.start();
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tpt = new AffineTransform();
		tpt.translate(0, 0);
		g2.setTransform(tpt);
		 //Drawing background: 
	    TexturePaint tp = new TexturePaint(background,new Rectangle2D.Double(0,-bgPosY,ControlManager.screenWidth,ControlManager.screenHeight));
	    g2.setPaint(tp);
	    g2.fill(tpRect);

	    g2.drawImage(foreground,0,0,null);
	    
	    if(fadeIn || fadeOut)
	    {
	    	g2.setColor(new Color(0,0,0,opacity));
	    	g2.fill(fade);
	    	g2.draw(fade);
	    }
	    
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		this.ellipse = new Ellipse2D.Double(ellipseX, ellipseY, ellipseWidth, ellipseHeight);
		g2.setColor(Color.BLUE);
		g2.fill(ellipse);
		g2.draw(ellipse);
		for(Particle particle: particles){
	        particle.paintComponent(g2);
		}
		
	}

	@Override
	public void update() {
		frame++;
		bgPosY += 2;
		if(frame<90 && opacity>0.05f){
			opacity-=(0.1/4);
		}else if(!fadeOut){
			fadeIn = false;
			opacity = 0;
		}
		if(frame > 1320 && opacity < 0.95f){
			opacity += (0.1/4);
			fadeOut = true;
		}
		
		if(anim){

		addParticle(x1,y1,0);addParticle(x1,y1,0);
		addParticle(x1,y1,0);addParticle(x1,y1,0);
		addParticle(x1,y1,0);addParticle(x1,y1,0);
		addParticle(x1,y1,0);addParticle(x1,y1,0);
		
		int x2 = (int)(20*counter*(Math.cos(counter)));
		int y2= (int)(20*counter*(Math.sin(counter)));
				
		this.x1 = x2;
		this.y1 = y2;
		
		addParticle(a1,b1,0);addParticle(a1,b1,0);
		addParticle(a1,b1,0);addParticle(a1,b1,0);
		addParticle(a1,b1,0);addParticle(a1,b1,0);
		addParticle(a1,b1,0);addParticle(a1,b1,0);
		
			
		int a2 = (int)(20*reversecounter*(Math.cos(reversecounter)));
		int b2= (int)(20*reversecounter*(Math.sin(reversecounter)));
				
		this.a1 = a2;
		this.b1 = b2;
		
		reversecounter -= 0.05;
		counter += 0.05;
				
			Iterator<Particle> i = particles.iterator();
				while (i.hasNext()) {
				   Particle p = i.next(); 
				   if(p.getLife() == 100){
				   i.remove();
				   }
			}
				
			if(reversecounter < 10){
			this.ellipseWidth = ellipseWidth + 1.0 * 4;
			this.ellipseHeight = ellipseHeight + 1.0 * 4;
			this.ellipseX = 0-this.ellipseWidth/2;
			this.ellipseY = 0-this.ellipseHeight/2;
			}
		}
		
	}
	
	public void addParticle(int x, int y, int life){
        int size = (int)(rand.nextInt(20)*1.1);
        particles.add(new Particle(x,y,size,life));
    }  
	
	private void setAnimation(boolean bool){
		this.anim = bool;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(foreground.equals(ImageHandler.getImage(ImageHandler.ImageType.magestate))){
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
			    			        11000
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

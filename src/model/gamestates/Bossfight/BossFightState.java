package model.gamestates.Bossfight;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;

import model.entities.Particle;
import model.gamestates.GameState;
import control.ControlManager;
import control.ImageHandler;

public class BossFightState extends GameState{

	private BufferedImage currentImage,troll,trollHit,background,vignette,trollState;
	private int bgPosY;
	private float opacity = 1f; //kunnen deze weg?
	private Rectangle2D tpRect;
	private ArrayList<Particle> confusion = new ArrayList<>(5000);
	
	private List<BufferedImage> spells, particles;
	private AffineTransform tx;
	private int midX,midY, time, x1, y1, a1, b1;
	private Point2D position1, position2, positioni1, positioni2;
	
	private int outCounter,inCounter;
	private int outColor,inColor;
	private int wins, level = 0;
	private double spellScore, totalScore, counter, reversecounter = 0;
	private double ellipseWidth, ellipseHeight, ellipseX, ellipseY;
	private boolean drawing, started, finished, win;
	private Random rand;
	
	public BossFightState(ControlManager cm) {
		super(cm);

		//images
		rand = new Random();
		background = ImageHandler.getImage(ImageHandler.ImageType.troll_bg);
		vignette = ImageHandler.getImage(ImageHandler.ImageType.troll_darken);
		troll = ImageHandler.getImage(ImageHandler.ImageType.bf_troll_normal);
		trollHit = ImageHandler.getImage(ImageHandler.ImageType.bf_troll_hit);
		tpRect = new Rectangle2D.Double(0,0,ControlManager.screenWidth,ControlManager.screenHeight);
		trollState = troll;
		//spells
		spells = new ArrayList<BufferedImage>();
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell1));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell2));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell3));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell4));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell5));
		particles = new ArrayList<BufferedImage>();
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell1_particle1));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell1_particle2));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell1_particle3));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell2_particle1));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell2_particle2));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell2_particle3));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell3_particle1));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell3_particle2));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell3_particle3));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell4_particle1));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell4_particle2));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell4_particle3));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell5_particle1));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell5_particle2));
		particles.add(ImageHandler.getImage(ImageHandler.ImageType.spell5_particle3));
		currentImage = spells.get(level);
		position1 = new Point2D.Double(0,0);
		position2 = new Point2D.Double(0,0);
		positioni1 = new Point2D.Double(-100,-100);
		positioni2 = new Point2D.Double(-100,-100);
		this.x1 = this.midX;
		this.y1 = this.midY;
		this.a1 = this.midX;
		this.b1 = this.midY;
		this.ellipseWidth = 0;
		this.ellipseHeight = 0;
		this.ellipseX = 0;
		this.ellipseY = 0;
		drawing = true;
		finished = false;
		started = false;
		win = false;
		time = 1800;
	}
	
	private void initScanBMPImage(BufferedImage image) throws IOException {
		int temp = 0;
	    for (int xPixel = 0; xPixel < image.getWidth(); xPixel++)
	    {
	    	for (int yPixel = 0; yPixel < image.getHeight(); yPixel++)
	        {
	                temp = image.getRGB(xPixel, yPixel);
	                if(temp == outColor)
	                {
	                	outCounter++;
	                }
	                else if(temp == inColor)
	                {
	                	inCounter++;
	                }  
	        }
	     }
	  }
	
	private double scanBMPImage(BufferedImage image) throws IOException {
		int temp = 0;
		int inTemp = 0;
		int outTemp = 0;
		double toReturn = 0;
	    for (int xPixel = 0; xPixel < image.getWidth(); xPixel++)
	    {
	    	for (int yPixel = 0; yPixel < image.getHeight(); yPixel++)
	        {
	                temp = image.getRGB(xPixel, yPixel);
	                if(temp == inColor)
	                {
	                	inTemp++;
	                }
	                if(temp == outColor)
	                {
	                	outTemp++;
	                }
	        }
	    }
	    toReturn  = (inCounter - inTemp)/(inCounter/100)-((outCounter-outTemp)/(outCounter/100));
	    return toReturn;
	}
	
	@Override
	public void draw(Graphics2D g2) 
	{
		AffineTransform tx = new AffineTransform();
		tx.translate(0, 0);
		g2.setTransform(tx);
		
		 //Drawing background: 
	    TexturePaint tp = new TexturePaint(background,new Rectangle2D.Double(0,-bgPosY,ControlManager.screenWidth,ControlManager.screenHeight));
	    g2.setPaint(tp);
	    g2.fill(tpRect);
	    if((win && time < 0) || finished)
	    {
	    	g2.drawImage(trollHit, 0, 0, null);
	    }
	    else
	    {
	    	g2.drawImage(trollState,0,0,null);
	    }
	    g2.drawImage(vignette,0,0,null);
	    for(Particle particle: confusion){
	        particle.paintComponent(g2);
		}
		/////////////////////////
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform oldAF = new AffineTransform();
		oldAF.setTransform(g2.getTransform());
		oldAF.scale(1.8,1.8);
		this.tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		if(drawing)
		{
			g2.drawImage(currentImage,-currentImage.getWidth()/2, -currentImage.getHeight()/2, null);
		}
		else if(level < 5)
		{
			drawAttackAnimation(g2);
		}
		
		g2.setTransform(oldAF);	
		if(drawing)
		{
			g2.setColor(Color.BLUE);
			g2.setStroke(new BasicStroke(60f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2.drawRect((int)position1.getX(),(int)position1.getY(), 1, 1);
			g2.setColor(Color.RED);
			g2.drawRect((int)position2.getX(),(int)position2.getY(), 1, 1);
			if(time > 25*60 && level == 0)
			{
				g2.setFont(new Font("Verdana", Font.BOLD, 40));
				g2.setColor(Color.BLUE);
				g2.drawString("Speler 1", 190, 320);
				g2.setColor(Color.RED);
				g2.drawString("Speler 2", 690, 320);
			}
			g2.setColor(new Color(0,0,255,122)); // blue
			g2.drawRect((int)positioni1.getX(),(int)positioni1.getY(), 1, 1);
			g2.setColor(new Color(255,0,0,122)); // red
			g2.drawRect((int)positioni2.getX(),(int)positioni2.getY(), 1, 1);
			if(cm.getInputHandler().isA1Pressed())
			{
				drawPointsLeft(cm.getInputHandler().getX1(),cm.getInputHandler().getY1());
			}
			
			if(cm.getInputHandler().isA2Pressed())
			{
				drawPointsRight(cm.getInputHandler().getX2(),cm.getInputHandler().getY2());
			}
		}
		g2.setColor(Color.white);
		g2.setFont(new Font("Verdana", Font.BOLD, 20));
		g2.drawString("Aantal punten: " + (int) totalScore, 0, 40);
		if(drawing)
		{
			g2.drawString(time/60 + "", 0, 20);
			g2.drawString("Minimaal percentage: " + (60 + (level*5)) + "%", 0, 60);
		}
		else
		{
			g2.drawString(spellScore + "%" , 0, 60);
			g2.drawString("Succesvolle spreuken: " + wins, 0, 20);
		}
		if(finished)
		{
			confusion.add(new Particle(midX-80,270,10,0));
			if(wins > 3)
			{
				g2.drawString("Jullie hebben gewonnen!", (int) (midX/1.8-125), 50);
			}
			else
			{
				System.out.println(time);
				for(int i = 0; i < 8; i++)
				{
					int size = (int)(rand.nextInt(20)*1.1);
					confusion.add(new Particle(x1+midX,y1+midY,size,0));
				}
				
				int x2 = (int)(20*counter*(Math.cos(counter)));
				int y2= (int)(20*counter*(Math.sin(counter)));
						
				this.x1 = x2;
				this.y1 = y2;
				
				for(int i = 0; i < 8; i++)
				{
					int size = (int)(rand.nextInt(20)*1.1);
					confusion.add(new Particle(a1+midX,b1+midY,size,0));
				}
				
					
				int a2 = (int)(20*reversecounter*(Math.cos(reversecounter)));
				int b2= (int)(20*reversecounter*(Math.sin(reversecounter)));
						
				this.a1 = a2;
				this.b1 = b2;
				
				reversecounter -= 0.05;
				counter += 0.05;
						
					Iterator<Particle> i = confusion.iterator();
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
		}
	
	private void drawAttackAnimation(Graphics2D g2)
	{
		if(win)
		{
			if(time > 100)
			{
				AffineTransform tf1 = new AffineTransform();
				AffineTransform tf2 = new AffineTransform();
				tf1.translate(0 - (int) (time*4), -400);
				tf2.translate(-800 + (int) (time*4), -400);
				tf1.rotate(Math.PI*(time*0.02), 400, 400);
				tf2.rotate(-Math.PI*(time*0.02), 400, 400);
				g2.drawImage(particles.get(level*3), tf1, null);
				g2.drawImage(particles.get(level*3+1), tf2, null);
			}
			else if(time > 0)
			{
				if((time % 8 < 4 && level == 4) || level != 4)
				{
					
					g2.scale(0.01*time, 0.01*time);
					g2.drawImage(particles.get(level*3+2), -400, -400, null);
				}
			}
			else
			{
				if(time % 10 == 0)
		 	    {
					confusion.add(new Particle(midX-100,250,10,0));
		 	    }
				g2.scale(-time*0.16, -time*0.16);
				g2.drawImage(particles.get((level*3)+2), -400, -400, null);
			}
			if(time < 0 && time > -10)
			{
				for(int i = 0; i < 5; i++)
				{
					confusion.add(new Particle(midX,midY,20,0));
				}
			}
		}
		else
		{
			if(time > 0 && time % 20 > 10)
			{
				g2.drawImage(particles.get(level*3), 0, -400, null);
				g2.drawImage(particles.get(level*3+1), -800, -400, null);
			}
			else if(time <= 0)
			{
				g2.drawImage(particles.get(level*3), 0, (int) (-400 + (time*0.2*time)), null);
				g2.drawImage(particles.get(level*3+1), -800, (int) (-400 + (time*0.2*time)), null);
			}
		}
	}
	
	private void drawPointsLeft(int x, int y)
	{
		 Graphics2D g2 = currentImage.createGraphics();
		 g2.setColor(Color.BLUE);
		 g2.setStroke(new BasicStroke(96f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		 if(x < 500)
		 {
			 g2.drawLine((int) (x*1.8) + ((1920 - (midX*2))/2), (int) (1.8*y) + ((1080 - (midY*2))/2), (int) (1.8*x) + ((1920 - (midX*2))/2), (int) (1.8*y) + ((1080 - (midY*2))/2));
		 }
	}
	
	private void drawPointsRight(int x, int y)
	{
		 Graphics2D g2 = currentImage.createGraphics();
		 g2.setColor(Color.RED);
		 g2.setStroke(new BasicStroke(96f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		 if(x > 500 && x < 1100)
		 {
			 g2.drawLine((int) (x*1.8) + ((1920 - (midX*2))/2), (int) (1.8*y) + ((1080 - (midY*2))/2), (int) (1.8*x) + ((1920 - (midX*2))/2), (int) (1.8*y) + ((1080 - (midY*2))/2));
		 }
	}
	
	public void nextSpell()
	{
		level++;
		if(level < spells.size())
		{
			currentImage = spells.get(level);
			totalScore += spellScore * 0.75 * level;
			spellScore = 0;
			inCounter = 0;
			outCounter = 0;
			try {
				initScanBMPImage(currentImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			drawing = true;
			time = 1300 - (100*level);
		}
		else
		{
			if(!finished)
			{
				time = 300;
			}
			gameCompleted();
		}
	}
	
	public void gameCompleted()
	{
		finished = true;
		if(time != 0)
		{
			time--;
		}
		else
		{
			time = 0;
			level = 0;
			wins = 0;
			cm.getScoreHandler().bossScore = (int) Math.round(totalScore);
			started = false;
			cm.getGameStateManager().next();
		}
	}
	
	public void showScore()
	{
		if(time != -150)
		{
			time--;
		}
		else
		{
			nextSpell();
		}
	}
	
	@Override
	public void update() 
	{
		for(Particle particle: confusion){
			particle.update(true);
			particle.update(true);
		}
		Iterator<Particle> i = confusion.iterator();
		while (i.hasNext()) {
			Particle p = i.next(); 
			if(p.getLife() == 200){
				i.remove();
			}
		}
		bgPosY += 2;
		
		midX = ControlManager.screenWidth/2;
		midY = ControlManager.screenHeight/2;
		if(!started)
		{
			try {
				cm.playMusic3();
			} catch (LineUnavailableException | IOException e1) {
				e1.printStackTrace();
			}
			spellScore = 0;	//resetting game
			inCounter = 0;
			outCounter = 0;
			level = 0;
			totalScore = 0;
			wins = 0;
			finished = false;
			drawing = true;
			currentImage = spells.get(level);
			try {
				outColor = currentImage.getRGB(0, 0);
				inColor = Color.white.getRGB();
				outCounter = 0;
				inCounter = 0;
				initScanBMPImage(currentImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			position1 = new Point2D.Double(0,0);
			position2 = new Point2D.Double(0,0);
			positioni1 = new Point2D.Double(-100,-100);
			positioni2 = new Point2D.Double(-100,-100);
			time = 1800;
			started = true;
		}
		if(drawing)
		{
			if(cm.getInputHandler().getX1() < 500 && cm.getInputHandler().getX1() > 0)
			{
				position1.setLocation(cm.getInputHandler().getX1(), cm.getInputHandler().getY1());
				positioni1.setLocation(cm.getInputHandler().getX1(), cm.getInputHandler().getY1());
			}
			else if(cm.getInputHandler().getX1() > 500)
			{
				position1.setLocation(midX/1.8, cm.getInputHandler().getY1());
				positioni1.setLocation(cm.getInputHandler().getX1(), cm.getInputHandler().getY1());
			}
			else
			{
				position1.setLocation(0, cm.getInputHandler().getY1());
			}
			if(cm.getInputHandler().getX2() > 500)
			{
				position2.setLocation(cm.getInputHandler().getX2(), cm.getInputHandler().getY2());
				positioni2.setLocation(cm.getInputHandler().getX2(), cm.getInputHandler().getY2());
			}
			else
			{
				position2.setLocation(midX/1.8, cm.getInputHandler().getY2());
				positioni2.setLocation(cm.getInputHandler().getX2(), cm.getInputHandler().getY2());
			}
			if(time != 0)
			{
				time--;
			}
			else
			{
				drawing = false;
				spellScore = calculateSpellScore();
				if(spellScore > 60 + (level*5))
				{
					win = true;
					wins++;
				}
				time = 200;
			}
		}
		else
		{
			showScore();
		}
	}
	
	private double calculateSpellScore()
	{
		try {
			return scanBMPImage(currentImage);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}		
	}
	
	@Override
	public void init() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			wins++;
			nextSpell();
		}
	}
}
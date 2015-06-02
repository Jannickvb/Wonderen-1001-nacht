//package model.gamestates;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Point2D;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//
//import model.DrawThread;
//import control.ControlManager;
//import control.ImageHandler;
//
//public class BossFightState extends GameState{
//
//	private List<BufferedImage> spells;
//	private BufferedImage currentImage;
//	private List<Point2D> pixelThread, userInput1, userInput2, intersection;
//	private int midX,midY, percentScore;
//	private Thread timer;
//	private Point2D position1, position2;
//	private boolean apressed;
//	
//	public BossFightState(ControlManager cm) {
//		super(cm);		
//		pixelThread = new ArrayList<Point2D>();
//		this.userInput1 = new ArrayList<Point2D>();
//		this.userInput2 = new ArrayList<Point2D>();
//		intersection = new ArrayList<Point2D>();
//		this.position1 = new Point2D.Double(0,0);
//		this.position2 = new Point2D.Double(0,0);
//		apressed = false;
//		
//		this.timer = new Thread(new DrawThread(this));
//		timer.start();
//		spells = new ArrayList<BufferedImage>();
//		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell1));
//		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell2));
//		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell3));
//		
//		Random generator = new Random();
//		currentImage = spells.get(generator.nextInt(3));
//		
//		try {
//			scanBMPImage();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Override
//	public void draw(Graphics2D g2) {
//		AffineTransform oldAF = new AffineTransform();
//		oldAF.setTransform(g2.getTransform());
//		oldAF.scale(1.6,1.6);
//		AffineTransform tx = new AffineTransform();
//		tx.translate(midX, midY);
//		
//		g2.setTransform(tx);
//		g2.drawImage(currentImage, -currentImage.getWidth()/2, -currentImage.getHeight()/2,null);
//				
//		g2.setTransform(oldAF);	
//		g2.setColor(Color.red);
//		g2.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
//		g2.drawLine((int) position1.getX(), (int) position1.getY(), (int) position1.getX(), (int) position1.getY());
//		g2.drawLine((int) position2.getX(), (int) position2.getY(), (int) position2.getX(), (int) position2.getY());
//		
//		for(int i = 1; i < userInput1.size(); i++)
//		{
//			if(userInput1.get(i).getX() == 500000.0)
//			{
//				i++;
//			}
//			else
//			{
//				g2.drawLine((int) userInput1.get(i-1).getX(), (int) userInput1.get(i-1).getY(), (int) userInput1.get(i).getX(), (int) userInput1.get(i).getY());
//			}
//		}
//		
//		for(int i = 1; i < userInput2.size(); i++)
//		{
//			if(userInput2.get(i).getX() == 500000.0)
//			{
//				i++;
//			}
//			else
//			{
//				g2.drawLine((int) userInput2.get(i-1).getX(), (int) userInput2.get(i-1).getY(), (int) userInput2.get(i).getX(), (int) userInput2.get(i).getY());
//			}
//		}
//
//	}
//
//	@Override
//	public void update() {
//		midX = cm.getWidth()/2;
//		midY = cm.getHeight()/2;
//		
//	}
//	
//	public void refresh(){
//		this.position1.setLocation(cm.getInput().getX1(), cm.getInput().getY1());
//		this.position2.setLocation(cm.getInput().getX2(), cm.getInput().getY2());
//		
//		if(cm.getInput().getA1Pressed())
//		{
//			userInput1.add(new Point2D.Double(this.position1.getX(),this.position1.getY()));
//			apressed = true; 
//			//calculateSpellScore();
//			System.out.println(percentScore);
//		}
//		else
//		{
//			if(apressed == true)
//			{
//				System.out.println(position1 + "RELEASE");
//				userInput1.add(new Point2D.Double(500000.0,0.0));
//				apressed = false;
//			}
//			else
//			{				
//				apressed = false;
//			}
//		}
//		
//		if(cm.getInput().getA2Pressed())
//		{
//			userInput2.add(new Point2D.Double(this.position2.getX(),this.position2.getY()));
//			apressed = true; 
//			//calculateSpellScore();
//			System.out.println(percentScore);
//		}
//		else
//		{
//			if(apressed == true)
//			{
//				System.out.println(position2 + "RELEASE");
//				userInput2.add(new Point2D.Double(500000.0,0.0));
//				apressed = false;
//			}
//			else
//			{				
//				apressed = false;
//			}
//		}
//	}
//		
//	private void scanBMPImage() throws IOException {
//
//	    for (int xPixel = 0; xPixel < currentImage.getWidth(); xPixel++)
//	    {
//	            for (int yPixel = 0; yPixel < currentImage.getHeight(); yPixel++)
//	            {
//	                int color = currentImage.getRGB(xPixel, yPixel);
//	                if (color == Color.BLACK.getRGB())
//	                {
//	                	pixelThread.add(new Point2D.Float(xPixel, yPixel));
//	                }
//	            }
//	     }
//	  }
//	
//	private void calculateSpellScore()
//	{
//		for(Point2D imagePixel : pixelThread)
//		{
//			for(Point2D linePoint : userInput1)
//			{
//				if(imagePixel == linePoint)
//				{
//					intersection.add(linePoint);
//				}
//			}
//			
//			percentScore = intersection.size() / (pixelThread.size() / 100);
//		}
//	}
//	
//	
//	@Override
//	public void init() {
//		// TODO Auto-generated method stub	
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//	}
//}

package model.gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.DrawThread;
import control.ControlManager;
import control.ImageHandler;

public class BossFightState extends GameState{

	private Thread timer;
	private BufferedImage currentImage, userImage;
	private List<BufferedImage> spells;
	private List<Point2D> spellBlack, spellWhite;
	private List<Point2D> userInput1, intersection1;
	private List<Point2D> userInput2, intersection2;
	private int midX,midY, percentScore, time;
	private Point2D position1, position2;
	private boolean a1pressed, a2pressed;
	
	private int outCounter,inCounter;
	private int outColor,inColor;
	
	public BossFightState(ControlManager cm) {
		super(cm);				
		spells = new ArrayList<BufferedImage>();
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell1));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell2));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell3));
		
		spellBlack = new ArrayList<Point2D>();
		spellWhite = new ArrayList<Point2D>();
		
		Random generator = new Random();
		currentImage = spells.get(generator.nextInt(3));
		userImage = new BufferedImage(1920, 1080,BufferedImage.TYPE_INT_RGB);
		
		try {
			outColor = currentImage.getRGB(0, 0);
			outCounter = 0;
			inCounter = 0;
			InitscanBMPImage(currentImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		userInput1 = new ArrayList<Point2D>();
		userInput2 = new ArrayList<Point2D>();
		
		intersection1 = new ArrayList<Point2D>();
		intersection2 = new ArrayList<Point2D>();
		
		position1 = new Point2D.Double(0,0);
		position2 = new Point2D.Double(0,0);
		
		a1pressed = false;
		a2pressed = false;
		time = 600;
	}
	
	private void InitscanBMPImage(BufferedImage image) throws IOException {
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
	                else if(inColor != 0 && temp == inColor)
	                {
	                	inCounter++;
	                }
	                else
	                {
	                	inColor = temp;
	                }
	        }
	     }
	  }
	
	private double scanBMPImage(BufferedImage image) throws IOException {
		int temp = 0;
		int outTemp = 0;
		int inTemp = 0;
		double toReturn = 100;
	    for (int xPixel = 0; xPixel < image.getWidth(); xPixel++)
	    {
	    	for (int yPixel = 0; yPixel < image.getHeight(); yPixel++)
	        {
	                temp = image.getRGB(xPixel, yPixel);
	                if(temp == outColor)
	                {
	                	outTemp++;
	                }
	                else if(temp == inColor)
	                {
	                	inTemp++;
	                }
	                
	        }
	     }
	   toReturn -= (inCounter - inTemp);
	   return toReturn;
	  }
	
	@Override
	public void draw(Graphics2D g2) 
	{
		Graphics2D image = userImage.createGraphics();
		
		AffineTransform tx = new AffineTransform();
		tx.scale(1.6, 1.6);
		
		g2.setColor(Color.black);
		g2.setTransform(tx);
		
		//DrawTimer
		g2.drawString(time + "", 100, 100);	
		
		// Draw Figure
		g2.drawImage(currentImage, midX, midY, null);
		
		// Draw player lines
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2.drawRect((int)position1.getX(),(int)position1.getY(), 1, 1);
		g2.drawRect((int)position2.getX(),(int)position2.getY(), 1, 1);
		
		drawPoints(userInput1);		
		drawPoints(userInput2);
		
		image = g2;
		
	}
	
	private void drawPoints(List<Point2D> points)
	{
		 Graphics2D g2 = currentImage.createGraphics();
		for(int i = 1; i < points.size(); i++)
		{
			if(points.get(i).getX() == 500000.0)
			{
				i++;
			}
			else
			{
				g2.drawLine((int)points.get(i-1).getX(), (int)points.get(i-1).getY(), (int)points.get(i).getX(), (int)points.get(i).getY());
			}
		}
	}
	
	@Override
	public void update() 
	{	
		if(time == 600)
		{
			timer = new Thread(new DrawThread(this));
			timer.start();
		}
	}
	
	public void refresh()
	{
		position1.setLocation(cm.getInput().getX1(), cm.getInput().getY1());
		position2.setLocation(cm.getInput().getX2(), cm.getInput().getY2());
		
		if(time != 0)
		{
			if(cm.getInput().getA1Pressed())
			{
				userInput1.add(new Point2D.Double(this.position1.getX(),this.position1.getY()));
				a1pressed = true;
			}
			else if(cm.getInput().getA2Pressed())
			{
				userInput2.add(new Point2D.Double(this.position2.getX(),this.position2.getY()));
				a2pressed = true; 
			}
			else
			{
				if(a1pressed == true)
				{
					userInput1.add(new Point2D.Double(500000.0,0.0));
					a1pressed = false;
				}
				else if(a2pressed == true)
				{
					userInput2.add(new Point2D.Double(500000.0,0.0));
					a2pressed = false;
				}
			}
		
			time--;
		}

	}
	
	private double calculateSpellScore()
	{
		
		try {
			return scanBMPImage(userImage);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}		
	}
	
	
	@Override
	public void init() 
	{
		midX = (int)(cm.getWidth()/1.6)/2;
		midY = (int)(cm.getHeight()/1.6)/2;
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}

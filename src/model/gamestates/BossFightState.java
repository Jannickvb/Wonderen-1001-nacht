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

	private List<BufferedImage> spells;
	private BufferedImage currentImage;
	private List<Point2D> pixelThread, userInput, intersection;
	private int midX,midY, percentScore;
	private Thread timer;
	private Point2D position;
	private boolean apressed;
	
	public BossFightState(ControlManager cm) {
		super(cm);		
		pixelThread = new ArrayList<Point2D>();
		this.userInput = new ArrayList<Point2D>();
		intersection = new ArrayList<Point2D>();
		this.position = new Point2D.Double(0,0);
		apressed = false;
		
		this.timer = new Thread(new DrawThread(this));
		timer.start();
		spells = new ArrayList<BufferedImage>();
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell1));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell2));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell3));
		
		Random generator = new Random();
		currentImage = spells.get(generator.nextInt(3));
		
		try {
			scanBMPImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		AffineTransform oldAF = new AffineTransform();
		oldAF.setTransform(g2.getTransform());
		oldAF.scale(1.6,1.6);
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		
		g2.setTransform(tx);
		g2.drawImage(currentImage, -currentImage.getWidth()/2, -currentImage.getHeight()/2,null);
				
		g2.setTransform(oldAF);	
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.drawLine((int) position.getX(), (int) position.getY(), (int) position.getX(), (int) position.getY());
		
		for(int i = 1; i < userInput.size(); i++)
		{
			if(userInput.get(i).getX() == 500000.0)
			{
				i++;
			}
			else
			{
				g2.drawLine((int) userInput.get(i-1).getX(), (int) userInput.get(i-1).getY(), (int) userInput.get(i).getX(), (int) userInput.get(i).getY());
			}
		}

	}

	@Override
	public void update() {
		midX = cm.getWidth()/2;
		midY = cm.getHeight()/2;
		
	}
	
	public void refresh(){
		this.position.setLocation(cm.getInput().getX1(), cm.getInput().getY1());
		
		if(cm.getInput().getAPressed())
		{
			userInput.add(new Point2D.Double(this.position.getX(),this.position.getY()));
			apressed = true; 
			//calculateSpellScore();
			System.out.println(percentScore);
		}
		else
		{
			if(apressed == true)
			{
				System.out.println(position + "RELEASE");
				userInput.add(new Point2D.Double(500000.0,0.0));
				apressed = false;
			}
			else
			{				
				apressed = false;
			}
		}		
	}
		
	private void scanBMPImage() throws IOException {

	    for (int xPixel = 0; xPixel < currentImage.getWidth(); xPixel++)
	    {
	            for (int yPixel = 0; yPixel < currentImage.getHeight(); yPixel++)
	            {
	                int color = currentImage.getRGB(xPixel, yPixel);
	                if (color == Color.BLACK.getRGB())
	                {
	                	pixelThread.add(new Point2D.Float(xPixel, yPixel));
	                }
	            }
	     }
	  }
	
	private void calculateSpellScore()
	{
		for(Point2D imagePixel : pixelThread)
		{
			for(Point2D linePoint : userInput)
			{
				if(imagePixel == linePoint)
				{
					intersection.add(linePoint);
				}
			}
			
			percentScore = intersection.size() / (pixelThread.size() / 100);
		}
	}
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub	
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

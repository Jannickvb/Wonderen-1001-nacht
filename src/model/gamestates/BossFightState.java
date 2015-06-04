
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
	private BufferedImage currentImage;
	private List<BufferedImage> spells;
	private AffineTransform tx;
	private int midX,midY, time;
	private Point2D position1, position2;
	
	private int outCounter,inCounter;
	private int outColor,inColor;
	private double spelScore;
	private boolean drawing, started;
	
	public BossFightState(ControlManager cm) {
		super(cm);				
		spells = new ArrayList<BufferedImage>();
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell1));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell2));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell5));
		
		Random generator = new Random();
		currentImage = spells.get(generator.nextInt(3));
		try {
			outColor = currentImage.getRGB(0, 0);
			inColor = Color.black.getRGB();
			outCounter = 0;
			inCounter = 0;
			initScanBMPImage(currentImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		position1 = new Point2D.Double(0,0);
		position2 = new Point2D.Double(0,0);
		this.timer = new Thread(new DrawThread(this));
		
		started = false;
		drawing = true;
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
		System.out.println("draw");
		AffineTransform oldAF = new AffineTransform();
		oldAF.setTransform(g2.getTransform());
		oldAF.scale(1.6,1.6);
		this.tx = new AffineTransform();
		tx.translate(midX, midY);
		
		g2.setTransform(tx);
		g2.drawImage(currentImage,-currentImage.getWidth()/2, -currentImage.getHeight()/2, null);//(currentImage, -currentImage.getWidth()/2, -currentImage.getHeight()/2, null);
				
		g2.setTransform(oldAF);	
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(60f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2.drawRect((int)position1.getX(),(int)position1.getY(), 1, 1);
		g2.drawRect((int)position2.getX(),(int)position2.getY(), 1, 1);
//		if(cm.getInputHandler().getA1Pressed() && drawing)
//		{
//			drawPoints(cm.getInputHandler().getX1(),cm.getInputHandler().getY1());
//		}
		
//		if(cm.getInputHandler().getA2Pressed() && drawing)
//		{
//			drawPoints(cm.getInputHandler().getX2(),cm.getInputHandler().getY2());
//		}
		g2.setColor(Color.black);
		g2.drawString(time + "", 20, 10);
		g2.drawString(spelScore + "%" , 20, 20);
	}
	
	private void drawPoints(int x, int y)
	{
		 Graphics2D g2 =  currentImage.createGraphics();
		 g2.setColor(Color.red);
		 g2.setStroke(new BasicStroke(96f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		 g2.drawLine((int) (x*1.6), (int) (1.6*y), (int) (1.6*x), (int) (1.6*y));
	}
	
	@Override
	public void update() 
	{
		if(!started)
		{
			timer.start();
			started = true;
		}
		midX = cm.getWidth()/2;
		midY = cm.getHeight()/2;
	}
	
	public void refresh()
	{
		if(drawing)
		{
			position1.setLocation(cm.getInputHandler().getX1(), cm.getInputHandler().getY1());
			position2.setLocation(cm.getInputHandler().getX2(), cm.getInputHandler().getY2());
			
			if(time != 0)
			{
				time--;
			}
			else
			{
				drawing = false;
				spelScore = calculateSpellScore();
			}
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
		// TODO Auto-generated method stub
	}
}
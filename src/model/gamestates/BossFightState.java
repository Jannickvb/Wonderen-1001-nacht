package model.gamestates;

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



import control.ControlManager;
import control.ImageHandler;

public class BossFightState extends GameState{


	private List<BufferedImage> spells;
	private BufferedImage currentImage;
	private List<Point2D> pixelThread, drawLine, intersection;
	private int width,height,midX,midY,counter;
	
	public BossFightState(ControlManager cm) {
		super(cm);		
		pixelThread = new ArrayList<Point2D>();
		drawLine = new ArrayList<Point2D>();
		intersection = new ArrayList<Point2D>();
		
		spells = new ArrayList<BufferedImage>();
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell1));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell2));
		spells.add(ImageHandler.getImage(ImageHandler.ImageType.spell3));
		
		Random generator = new Random();
		currentImage = spells.get(generator.nextInt(3));

	}
	
	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(midX, midY);
		g2.setTransform(tx);
		g2.drawImage(currentImage, -currentImage.getWidth()/2, -currentImage.getHeight()/2,null);
		
		try {
			scanBMPImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g2.setPaint(Color.RED);
		
		for(Point2D pixel : pixelThread)
		{
			g2.drawRect((int)(pixel.getX() - currentImage.getWidth()/2),(int)(pixel.getY() - currentImage.getHeight()/2), 1, 1);
		}
	}

	@Override
	public void update() {
		width = cm.getWidth();
		height = cm.getHeight();
		midX = width/2;
		midY = height/2;
		counter++;
		if(counter > 300)
		{
			cm.getGameStateManager().next();
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
			for(Point2D linePoint : drawLine)
			{
				if(imagePixel == linePoint)
				{
					intersection.add(linePoint);
				}
			}
			
			int percentScore = intersection.size() / (pixelThread.size() / 100);
			System.out.println();
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

package model.gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import control.ControlManager;
import control.ImageHandler;

public class BossFightState extends GameState{


	private List<BufferedImage> spells;
	private BufferedImage currentImage;
	private List<Point2D> pixelThread, userInput, intersection;
	private int width,height,midX,midY,counter;
	private Thread timer;
	private Point2D position;
	
	public BossFightState(ControlManager cm) {
		super(cm);		
		pixelThread = new ArrayList<Point2D>();
		this.userInput = new ArrayList<Point2D>();
		intersection = new ArrayList<Point2D>();
		this.position = new Point2D.Double(0,0);
		this.timer = new Thread(new Drawer(this));
		timer.start();
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
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(20f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.drawLine((int) position.getX(), (int) position.getY(), (int) position.getX(), (int) position.getY());
		for(int i = 0; i < userInput.size(); i++)
		{
			g2.drawLine((int) userInput.get(i).getX(), (int) userInput.get(i).getY(), (int) userInput.get(i).getX(), (int) userInput.get(i).getY());
		}
		System.out.println(userInput.size());
//		try {
//			scanBMPImage();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		g2.setPaint(Color.RED);
		
//		for(Point2D pixel : pixelThread)
//		{
//			g2.drawRect((int)(pixel.getX() - currentImage.getWidth()/2),(int)(pixel.getY() - currentImage.getHeight()/2), 1, 1);
//		}
	}

	@Override
	public void update() {
	}
	
	public void refresh(){
		this.position.setLocation(cm.getInput().getX1(), cm.getInput().getY1());
		if(cm.getInput().getAPressed())
		{
			userInput.add(new Point2D.Double(this.position.getX(),this.position.getY()));
			System.out.println(position);
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
	
	class Drawer implements Runnable, ActionListener
	{
		private BossFightState boss;
		private Timer timer;

		public Drawer(BossFightState boss)
		{
			this.boss = boss;
			this.timer = new Timer(1000/60, this);
		}

		@Override
		public void run() {
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			boss.refresh();
		}
	}

}

package model.gamestates;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

import model.entities.Foilage;
import model.entities.Player;
import model.entities.Rock;
import model.tileset.TileMap;
import control.ControlManager;
import control.ImageHandler;

public class BoatGameState extends GameState implements ActionListener {

	private Player boat;
	private BufferedImage grass;
	private int backgroundPositionY;
	private ArrayList<Rock> rocks;
	private ArrayList<Foilage> plants;
	private boolean pressurePlate1= false; //Right foot
	private boolean pressurePlate2= false; //Left foot
	private boolean pressurePlate3= false; //Right foot
	private boolean pressurePlate4= false; //Left foot
	
	public BoatGameState(ControlManager cm) {
		super(cm);
		boat = new Player(cm);
		backgroundPositionY = 0;
		Timer backgroundTimer = new Timer(1000/30,this);
		backgroundTimer.start();
		rocks = new ArrayList<>(1000);
		plants = new ArrayList<>(1000);
	}

	@Override
	public void draw(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING	,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
	    g2.drawImage(grass, 0,0,null);
		//Drawing objects:
//		AffineTransform old = g2.getTransform();
//		AffineTransform Tx = new AffineTransform();
//		Tx.translate(screenWidth/4,backgroundPositionY);
//		g2.setTransform(Tx);
//		for(Tile[] tileX: map.getTileMap())
//		{
//			for(Tile tileY: tileX)
//			{
//				tileY.draw(g2);
//			}
//		}
		//g2.setClip(new Rectangle2D.Double(0,0,screenWidth,screenHeight));
		
//	    g2.setTransform(old);
	    for(Rock rock : rocks) 
			rock.draw(g2);
	    for(Foilage plant : plants) 
			plant.draw(g2);
		boat.draw(g2);
		
	}

	@Override
	public void update() {
		boat.update();
		for(Rock rock : rocks) {
			if(rock.containsPoint(boat)) {
				collision();
			}
		}
		//Randomly spawning rocks: 
		if(Math.floor(Math.random()*15) == 3) {
			Rock rock = null;
			switch((int) Math.floor(Math.random()*3)) {
				case 0:
					rock = new Rock(cm,ImageHandler.getImage(ImageHandler.ImageType.rock1));
					break;
				case 1:
					rock = new Rock(cm,ImageHandler.getImage(ImageHandler.ImageType.rock2));
					break;
				case 2:
					rock = new Rock(cm,ImageHandler.getImage(ImageHandler.ImageType.rock3));
					break;
				case 3:
					rock = new Rock(cm,ImageHandler.getImage(ImageHandler.ImageType.rock4));
					break;		
			}
			rocks.add(rock);
			rock.init();
		}
		//Randomly spawning trees:
		if(Math.floor(Math.random()*6) == 3) {
			Foilage plant = null;
			switch((int) Math.floor(Math.random()*4)) {
				case 0:
					plant = new Foilage(cm,ImageHandler.getImage(ImageHandler.ImageType.tree1));
					break;
				case 1:
					plant = new Foilage(cm,ImageHandler.getImage(ImageHandler.ImageType.tree2));
					break;
				case 2:
					plant = new Foilage(cm,ImageHandler.getImage(ImageHandler.ImageType.tree3));
					break;
				case 3:
					plant = new Foilage(cm,ImageHandler.getImage(ImageHandler.ImageType.tree4));
					break;		
			}
			plants.add(plant);
			plant.init();
		}
	}

	@Override
	public void init() {
		grass = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.grass));
		boat.init();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		backgroundPositionY+=6;
	}
	
	public void collision() {
		boat.collision();
		backgroundPositionY = 0;
		rocks = new ArrayList<>();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        	boat.setPressurePlates(2);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        	boat.setPressurePlates(1);
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			boat.setPressurePlates(4);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        	boat.setPressurePlates(3);
        }
	}

}

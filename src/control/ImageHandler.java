package control;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import view.Main;

public class ImageHandler {
	
	public static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private static Scalr.Mode mode;
	public ImageHandler() {
		
	}
	
	static{
		try{
			images.add(ImageIO.read(Main.class.getResource("/images/bg_menu.png")));
			
			images.add(ImageIO.read(Main.class.getResource("/images/Tutorial.jpg")));
			images.add(ImageIO.read(Main.class.getResource("/images/spell_tutorial.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boat.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu_fg_right.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu_fg_left.png")));
			
			images.add(ImageIO.read(Main.class.getResource("/images/spells/circle.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/rectangle.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/spell3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/spell4.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/triangle.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Tovenaar.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Tovenaar2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Trol.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/PalaceOutside.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/DoorChoosing.jpg")));
			
			images.add(ImageIO.read(Main.class.getResource("/images/background.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/rock1.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/rock2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/rock3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/rock4.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/heart.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/tree1.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/tree2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/tree3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/tree4.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/pier.png")));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public enum ImageType{
		menubg, tutorial_plate, tutorial_spell, player_boat, menu_right, menu_left, spell1, spell2, spell3, spell4, spell5, mage1, mage2, troll, arrival, choice1, grass, rock1, rock2, rock3, rock4, heart, tree1, tree2, tree3, tree4, pier; 
	}
	
	public static BufferedImage getImage(ImageType img){
		return images.get(img.ordinal());
	}

	public static BufferedImage getScaledImage(BufferedImage image){
		getMode(image);
		int targetSize;
		if(mode == Scalr.Mode.FIT_TO_HEIGHT)
			targetSize = ControlManager.screenHeight;
		else
			targetSize = ControlManager.screenWidth;
		image = Scalr.resize(image, mode, targetSize,Scalr.OP_ANTIALIAS);
		return image;
	}
	
	private static Scalr.Mode getMode(BufferedImage image){
		if (image.getHeight() < image.getWidth()) {
			mode = Scalr.Mode.FIT_TO_WIDTH;
		} else {
			mode = Scalr.Mode.FIT_TO_HEIGHT;
		}
		return mode;
	}
}


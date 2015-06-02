package control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import view.Main;

public class ImageHandler {
	
	public static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public ImageHandler() {
		
	}
	
	static{
		try{
			images.add(ImageIO.read(Main.class.getResource("/images/bg_menu.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/tileset/tileset.png")));
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
			
			images.add(ImageIO.read(Main.class.getResource("/images/boat.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/water.jpg")));
			images.add(ImageIO.read(Main.class.getResource("/images/sideLeft.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/sideRight.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/grass.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/rock.jpg")));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public enum ImageType{
		menubg, tilemap, tutorial_plate, tutorial_spell, player_boat, menu_right, menu_left, spell1, spell2, spell3, spell4, spell5, mage1, mage2, troll, boat, water, leftSideRiver, rightSideRiver, grass, rock; 
	}
	
	public static BufferedImage getImage(ImageType img){
		return images.get(img.ordinal());
	}
	
	
	
	
}


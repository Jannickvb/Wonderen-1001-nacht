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
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public enum ImageType{
		menubg, tutorial_plate, tutorial_spell, player_boat, menu_right, menu_left, spell1, spell2, spell3, spell4, spell5, mage1, mage2, troll, grass, rock1, rock2, rock3, rock4, heart, tree1, tree2, tree3, tree4; 
	}
	
	public static BufferedImage getImage(ImageType img){
		return images.get(img.ordinal());
	}
	
	
	
	
}


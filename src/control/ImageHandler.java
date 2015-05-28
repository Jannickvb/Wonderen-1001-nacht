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
			
			images.add(ImageIO.read(Main.class.getResource("/images/spell3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spell4.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spell5.png")));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public enum ImageType{
		menubg, tilemap, tutorial_plate, tutorial_spell, player_boat, spell1, spell2, spell3 
	}
	
	public static BufferedImage getImage(ImageType img){
		return images.get(img.ordinal());
	}
	
	
	
	
}


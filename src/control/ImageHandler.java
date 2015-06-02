package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
			images.add(ImageIO.read(Main.class.getResource("/images/boat.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/sideLeft.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/sideRight.png")));
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
		menubg, tilemap, tutorial, boat, leftSideRiver, rightSideRiver, grass, rock1, rock2, rock3, rock4, heart, tree1, tree2, tree3, tree4
	}
	
	public static BufferedImage getImage(ImageType img){
		return images.get(img.ordinal());
	}
	
	
	
	
}


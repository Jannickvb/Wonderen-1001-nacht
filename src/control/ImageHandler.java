package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageHandler {
	
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public ImageHandler() {
		//insert image first
	}

	public BufferedImage getImage(int imageID){
		try {
			return images.get(imageID);
		} catch (Exception e) {
			System.out.println("No Image Selected");
		}
	}
	
	public BufferedImage loadImage(String imagePath){		
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath));
		} catch (IOException e) {
			System.out.println("Wrong Path");
		}
		return image;
	}
	
}


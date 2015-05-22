package model;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Animation{
	
	private BufferedImage spritesheet;
	private BufferedImage[] subimage;
	private int spriteIndex;
	private boolean hasPlayedOnce;
	
	public Animation(Image spritesheet,int width, int height){
		this.spritesheet = (BufferedImage) spritesheet;
		spriteIndex = 0;
		subimage = new BufferedImage[spritesheet.getWidth(null)/width];
		for(int i = 0; i< subimage.length;i++){
			subimage[i] = this.spritesheet.getSubimage(i*width,0, width, height);
		}
	}
	public boolean hasPlayedOnce() {
		return hasPlayedOnce;
	}
	public BufferedImage giveNext()
	{
		if(subimage.length>spriteIndex)
		{
			spriteIndex++;
			return subimage[spriteIndex-1];
		}
		else
		{			
			spriteIndex = 0;
		}
		return subimage[spriteIndex];
	}
	public void reset()
	{
		spriteIndex = 0;
	}
}

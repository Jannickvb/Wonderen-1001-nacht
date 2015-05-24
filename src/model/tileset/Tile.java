package model.tileset;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
	
	public boolean isSolid;
	private int x,y;
	public static int size = 64;
	private BufferedImage tile;
	private BufferedImage tilemap;
	private int id;
	
	public Tile(boolean isSolid,int x,int y,int id) {
		this.isSolid = isSolid;
		this.x = x;
		this.y = y;
		this.id = id;
		tilemap.getSubimage(((tilemap.getWidth()/size)/id)*size,(id/(tilemap.getWidth()/size)*size), size, size);
	}
	
	public void setSolid(boolean isSolid){
		this.isSolid = isSolid;
	}
	
	public int checkCollision(int x1,int y1,int x2,int y2)
	{
		if(x< x1 && x+size>x1 )
		{
			if(y<y1 && y+size > y2)
			{
				return 1; // links onder
			}
			else if(y<y2 && y+size >y2)
			{
				return 2; // links boven
			}
		}
		else if(x < x2 && x +size >x2 )
		{
			if(y<y1 && y+size > y2)
			{
				return 3; //rechts onder
			}
			else if(y<y2 && y+size >y2)
			{
				return 4; //rechts boven
			}
		}
		return 0;
	}
	
	public void draw(Graphics2D g2)
	{
		g2.drawImage(tile,null, x, y);
	}
}

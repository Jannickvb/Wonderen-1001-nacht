package control;

import java.awt.Color;
import java.awt.Graphics2D;





public class DrawFuctions {

	public static void drawString(Graphics2D g2,String toDraw,int x,int y)
	{
		String[] split = toDraw.split(".");
		int length = 0;
		int counter =0;
		for(String s : split)
		{
			counter++;
			if(length < s.length())
			{
				length = s.length();
			}
		}
		g2.setColor(Color.white);
		g2.fillRect(x, y, length +100, split.length + 50);
		g2.setColor(Color.black);
		for(int i = 0; i < counter ; i++)
		{
			g2.drawString(split[i], x+10, y+10+(i*30));
		}
		
	}
	
	
}

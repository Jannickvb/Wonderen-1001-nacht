package control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;





public class DrawFuctions {


private static Font menuFont;
	
	public static void drawString(Graphics2D g2,String toDraw,int y)
	{
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ArrayList<String> split = new ArrayList<String>();
		int temp = 0;
		int length = -1;
		int heigthSingle = 0;
		for(int i =0 ;i< toDraw.length();i++)
		{
			if(toDraw.charAt(i) == '.')
			{
				split.add(toDraw.substring(temp, i+1));
				if((i-temp)>length)
				{
					length++;
				}
				temp = i +1;
				
			}
		}
		
		try {
			menuFont = Font.createFont(Font.TRUETYPE_FONT,new File(System.getProperty("user.dir") + "/resources/texts/font.ttf")).deriveFont(36f);
			
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.setColor(Color.white);
		g2.setFont(menuFont);
		int sLength = g2.getFontMetrics(menuFont).stringWidth(split.get(length));
		heigthSingle = g2.getFontMetrics().getHeight();
		g2.fillRect(200, y, ControlManager.screenWidth-400,300);
		
		g2.setColor(Color.black);
		
		for(int i = 0; i < split.size() ; i++)
		{
			g2.setStroke(new BasicStroke(1.5f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));
			  FontRenderContext frcontext = g2.getFontRenderContext();
			  GlyphVector vec = g2.getFont().createGlyphVector(frcontext, split.get(i));
			  Shape glyph = vec.getOutline(210,y+(i+1)*heigthSingle);
			  g2.setColor(Color.BLACK);
			  g2.draw(glyph);
			  //g2.drawString(split.get(i), x+10,y+(i+1)*heigthSingle);
//			GlyphVector v = menuFont.createGlyphVector(g2.getFontRenderContext(),split.get(i) );
//			Shape shape= v.getOutline(x+10, y+(i+1)*heigthSingle);
//			g2.setColor(Color.black);
//			
//			g2.draw(shape);
			
			//g2.setColor(Color.white);
		}
		
	}
	
	
}

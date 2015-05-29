package model.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import model.tileset.Tile;
import model.tileset.TileMap;
import control.ControlManager;
import control.MapReader;

public class PlayState extends GameState {

	private TileMap map;
	private int[][] mapArray = { { 95, 1, 2, 3, 4, 5, 6, 7 },
								 {0 , 9, 10, 1, 0, 1, 2, 1 },
								 { 0, 25, 0, 1, 0, 1, 2, 1 },
								 { 0, 0, 0, 1, 0, 1, 2, 1 } };
	private String[] toPrint;
	private int counter = 0;
	public PlayState(ControlManager cm) {
		super(cm);
		//FileReader.readLevelFile(("/maps/testLVL.txt"));
		try {
			map = new TileMap(MapReader.readLevelFile("resources/maps/testmap.txt"));
			toPrint = MapReader.readTextLines("resources/texts/testtext.txt");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		map = new TileMap(mapArray,4,8);
	}

	@Override
	public void draw(Graphics2D g2) {
		for(Tile[] tileX: map.getTileMap())
		{
			for(Tile tileY: tileX)
			{
				tileY.draw(g2);
			}
		}
		g2.setPaint(Color.white);
		g2.drawString(toPrint[counter],100, 100);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() == KeyEvent.VK_K)
		{
			if(toPrint[counter+1] != null)
			counter++;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import view.Main;
import model.tileset.Tile;
import model.tileset.TileMap;
import control.ControlManager;
import control.FileReader;

public class PlayState extends GameState {

	private TileMap map;
	private int[][] mapArray = { { 95, 1, 2, 3, 4, 5, 6, 7 },
								 {0 , 9, 10, 1, 0, 1, 2, 1 },
								 { 0, 25, 0, 1, 0, 1, 2, 1 },
								 { 0, 0, 0, 1, 0, 1, 2, 1 } };

	public PlayState(ControlManager cm) {
		super(cm);
		FileReader.readLevelFile(Main.class.getResource("/maps/testLVL.txt"));
		//map = new TileMap(FileReader.readLevelFile("resources/maps/testLVL"),8,4);
		map = new TileMap(mapArray,4,8);
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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

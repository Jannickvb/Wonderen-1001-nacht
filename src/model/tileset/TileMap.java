package model.tileset;

public class TileMap {
	
	private int[][] tilemap;
	private Tile[][] tiles;
	public int x,y;
	public TileMap() {
		
	}
	
	public TileMap(int[][] map,int x, int y) {
		tilemap = map;
		this.x = x;
		this.y = y;
		tiles = new Tile[x][y];
		loadTiles(x, y);
	}
	
	
	
	private void loadTiles(int x, int y)
	{
		for (int i =0; i< y; i++)
		{
			for(int k=0 ; k<x; k++)
			{
				tiles[k][i] = new Tile(false,i*Tile.size,k*Tile.size,tilemap[k][i]);
			}
		}
	}
	public Tile[][] getTileMap()
	{
		return tiles;
	}
	
}

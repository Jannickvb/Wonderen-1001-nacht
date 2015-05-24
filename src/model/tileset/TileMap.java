package model.tileset;

public class TileMap {
	
	private int[][] tilemap;
	private Tile[][] tiles;
	public TileMap() {
		
	}
	
	public TileMap(int[][] map,int x, int y) {
		tilemap = map;
		tiles = new Tile[x][y];
		loadTiles(x, y);
	}
	
	public void loadTiles(int x, int y)
	{
		for (int i =0; i< x; i++)
		{
			for(int k=0 ; k<y; k++)
			{
				tiles[i][k] = new Tile(false,i*Tile.size,i*Tile.size,tilemap[i][k]);
			}
		}
	}	
	
}

package model.tileset;

public class TileMap {
	
	private int[][] tilemap;
	private Tile[][] tiles;
	public int x,y;
	public TileMap() {
		
	}
	
	public TileMap(int[][] map) {
		tilemap = map;
		this.y = tilemap[0].length;
		this.x = tilemap.length;
		tiles = new Tile[x][y];
		System.out.println("x: "+ x + " y: " + y);
		loadTiles(x, y);
	}
	
	
	
	private void loadTiles(int x, int y)
	{
		for (int i =0; i< y; i++)
		{
			for(int k=0 ; k<x; k++)
			{
//				System.out.println(tilemap);
				tiles[k][i] = new Tile(false,i*Tile.size,k*Tile.size,tilemap[k][i]);
			}
		}
	}
	public Tile[][] getTileMap()
	{
		return tiles;
	}
	
}

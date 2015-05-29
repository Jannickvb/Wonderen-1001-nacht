package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;





public class MapReader {

	
	public static int[][] readLevelFile(String file) throws NumberFormatException, IOException
	{
		int readMapWidth, readMapHeight;
		int[][] map;
		BufferedReader br = null;
		
		try {
		    br = new BufferedReader(new FileReader(file));
		    readMapWidth = Integer.parseInt(br.readLine());
		    readMapHeight = Integer.parseInt(br.readLine());
		    map = new int[readMapHeight][readMapWidth];
		    int row = 0;
		    String text = null;
		    while ((text = br.readLine()) != null){
		    	if(row < readMapHeight -1 )
		    	{
			        String[] tileValues = text.split(",");
			        for(int col = 0; col < readMapWidth-1; col++){
	//		        	System.out.println("col: "+map.length);
			            map[row][col] = Integer.parseInt(tileValues[col]);  
			        }
			        row++;
		    	}
		    }
		    
		    // Zero index rows...
		    if (row < readMapHeight - 1) {
		        throw new IOException("Expected title height does not match actual row height");
		    }  
		    return map;
		} finally {
		    try {
		        br.close();
		    } catch (Exception exp) {
		    }
		}
	}
	
	public static String[] readTextLines(String file) throws IOException
	{
		String[] toReturn;
		BufferedReader br = null;
		
		try {
		    br = new BufferedReader(new FileReader(file));
		    toReturn = new String[100];
		    int i = 0;
		    String text;
		    while ((text = br.readLine()) != null){
		    		toReturn[i] = text;
		    		i++;
		    	}
		    return toReturn;
		    
		} finally {
		    try {
		        br.close();
		    } catch (Exception exp) {
		    }
		}
	//	return null;
	}
	
	
}

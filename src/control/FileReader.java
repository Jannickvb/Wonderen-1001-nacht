package control;

import java.util.Scanner;



public class FileReader {

	public static int[][] readLevelFile(String file)
	{
		int[][] toReturn;
		String temp = "";
		try
		{
			Scanner scanner = new Scanner(file);
			temp = scanner.nextLine();
			toReturn = new int[Character.getNumericValue((temp.charAt(0)))][Character.getNumericValue(temp.charAt(2))];
			for(int i = 0; i< Character.getNumericValue((temp.charAt(0)));i++ )
			{
				temp = scanner.nextLine();
				for(int k = 0; k<Character.getNumericValue(temp.charAt(2));k++ )
				{
					toReturn[i][k] = Character.getNumericValue((temp.charAt(k*2)));
				}
			}
			scanner.close();
			return toReturn;
		}
		catch(Exception i)
		{
			
		}
		return null;
	}
	
	
}

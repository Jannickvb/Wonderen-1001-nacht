package control;

import java.io.File;
import java.util.Scanner;





public class FileReader {

	public static int[][] readLevelFile(String file)
	{
		int[][] toReturn;
		String temp[];
		String temp2[];
		try
		{
			File file1 = new File(file);
			Scanner scanner = new Scanner(file1);
			temp = scanner.nextLine().split(",");
			int x = Integer.parseInt((temp[0]));
			int y = Integer.parseInt(temp[1]);
			toReturn = new int[y][x];
			for(int i = 0; i< y;i++ )
			{
				temp2 = scanner.nextLine().split(",");
				System.out.println(temp);
				for(int k = 0; k<x;k++ )
				{
					System.out.println(Integer.parseInt((temp2[k])));
					toReturn[i][k] = Integer.parseInt((temp2[k]));
				}
			}
			scanner.close();
			return toReturn;
		}
		catch(Exception i)
		{
			System.out.println("error");
		}
		return null;
	}
	
	
}

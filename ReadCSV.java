import java.io.*;
import java.util.Scanner;

/*To use this example program, run it in the same folder as a .csv file 
named Puzzle1.csv */

public class ReadCSV
{
	private String pathname;
	
	public ReadCSV(String s)
	{
		pathname = s;
	}
	public  String[] get()
	{
		String output = "";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		
		while( input.hasNextLine() )
		{
			output += input.nextLine() + ",";
		}
		return output.split(",");
	}
	
	public void setPathName(String s)
	{
		pathname = s;
	}



}
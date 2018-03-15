import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
	public static void main(String [] args) {
		try 
		{
			File input = new File ("input.txt");
			Scanner sc = new Scanner(input);
			
			int n = sc.nextInt();

			sc.close();

			int count = Сount(n);
		
			FileWriter out = new FileWriter("output.txt");
			
			out.write(Long.toString(count));
			
			out.flush();
			out.close();
			System.exit(0);
		}
			
		catch(Exception e)
		{
			System.err.println (e);
			System.exit(-1);
		}
	}
	
	static int Сount(int n)
	{
		  if (n <= 3) 
			  return 1;
		  
		  if (n % 2 == 1) 
			  return Сount(n / 2) + Сount(n / 2 + 1);
		  
		  return 2 * Сount(n / 2);
	}
	
}
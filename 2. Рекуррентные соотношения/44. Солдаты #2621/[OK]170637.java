import java.io.*;
import java.util.*;


public class Test {
	
	
	public static int f_3(int n)

	{

	  if (n <= 3) return 1;

	  if (n % 2==1) return f_3(n / 2) + f_3(n / 2 + 1);

	  return 2 * f_3(n / 2);

	}
	
	
	public static void main(String[] args) throws IOException {
		PrintWriter output = new PrintWriter(new FileWriter("output.txt"));
		int in_number = 0;
		try{
		File file = new File("input.txt");
			
        Scanner in = new Scanner(file);
        in_number = in.nextInt();
		}
		catch(Exception e){}
        
        try{
        output.println(f_3(in_number));
        output.flush();
        }
        catch(Exception e){}
	}
		

	
	
}

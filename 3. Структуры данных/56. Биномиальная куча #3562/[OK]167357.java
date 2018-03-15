import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class TEST 
{
	public static void main(String[] args)throws FileNotFoundException  
	{
		try (Scanner in=new Scanner(new File("input.txt")))
		{
			PrintWriter out = new PrintWriter(new File("output.txt"));
			Long n=in.nextLong();
			ArrayList<Long> deg=new ArrayList<Long>();
			while(n!=1)
			{
				deg.add(n%2);
				n/=2;	
			}
			deg.add(n);
			for(int i=0;i<deg.size();i++)
			{
				if(deg.get(i)==1)
					out.println(i);
			}
			out.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}

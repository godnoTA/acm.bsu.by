
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class task
{
	public static void main(String[] args)throws FileNotFoundException  
	{
		try (Scanner in=new Scanner(new File("input.txt")))
		{
			int n=in.nextInt();
			int[] P=new int[n];
			for(int i=0;i<n;i++)
				P[i]=0;
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					if(in.nextInt()==1)
						P[j]=i+1;					
			try
			{
				PrintWriter out = new PrintWriter(new File("output.txt"));
				for(int i=0;i<n;i++)
					out.print(P[i]+" ");
				out.close();
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}

		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 

	}
}

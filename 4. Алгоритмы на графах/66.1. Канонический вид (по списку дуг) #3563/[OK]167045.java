import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class test
{
	public static void main(String[] args)throws FileNotFoundException  
	{
		try (Scanner in=new Scanner(new File("input.txt")))
		{
			int n=in.nextInt();
			in.nextLine();
			int[] P=new int[n];
			for(int i=0;i<n;i++)
				P[i]=0;
			while(in.hasNextLine())
			{
				String temp=in.nextLine();
				int j=Integer.valueOf(temp.split(" ")[1]);
				int value=Integer.valueOf(temp.split(" ")[0]);
				P[j-1]=value;
			}
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
	}
}

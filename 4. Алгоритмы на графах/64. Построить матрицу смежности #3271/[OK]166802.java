import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class matrsm 
{
	public static void main(String[] args)throws FileNotFoundException  
	{
		try (Scanner in=new Scanner(new File("input.txt")))
		{
			String temp=in.nextLine();
			int n=Integer.valueOf(temp.split(" ")[0]);
			int[][] matr=new int[n][n];
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					matr[i][j]=0;
			while(in.hasNextLine())
			{
				String tmp=in.nextLine();
				matr[Integer.valueOf(tmp.split(" ")[0])-1][Integer.valueOf(tmp.split(" ")[1])-1]=matr[Integer.valueOf(tmp.split(" ")[1])-1][Integer.valueOf(tmp.split(" ")[0])-1]=1;
			}
			try
			{
				PrintWriter out = new PrintWriter(new File("output.txt"));
				for(int i=0;i<n;i++)
				{
					for(int j=0;j<n;j++)
						out.print(matr[i][j]+" ");
					out.println();
				}
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

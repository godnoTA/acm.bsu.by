import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class test 
{
	public static int multCount(int[] sizes) 
    {
		int[][] result=new int[sizes.length][sizes.length];
		
		for(int m=2;m<=sizes.length-1;m++) 
		{   
			for(int i=1;i<=sizes.length-1-m+1;i++) 
			{
				int j=i+m-1;
				result[i][j]=Integer.MAX_VALUE;
				for (int k=i; k<=j-1;k++) 
				{
					result[i][j] = Math.min(result[i][j],result[i][k]+result[k+1][j]+sizes[i-1]*sizes[k]*sizes[j]);
				}
			}
		}
		return result[1][sizes.length-1]; 
	}
	public static void main(String[] args)throws FileNotFoundException  
	{
		int N=0;
		int[] sizes=new int[N];
		try (Scanner in=new Scanner(new File("input.txt")))
		{
			N=Integer.valueOf(in.nextLine());
			sizes=new int[N+1];
			String k=in.nextLine();
			sizes[0]=(Integer.valueOf(k.split(" ")[0]));
			sizes[1]=(Integer.valueOf(k.split(" ")[1]));
			int i=2;
			while(in.hasNextLine())
			{
				sizes[i]=(Integer.valueOf(in.nextLine().split(" ")[1]));
				i++;
			}
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		try
		{
			PrintWriter out = new PrintWriter(new File("output.txt"));
			out.print(Integer.valueOf(multCount(sizes)).toString());
			out.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}

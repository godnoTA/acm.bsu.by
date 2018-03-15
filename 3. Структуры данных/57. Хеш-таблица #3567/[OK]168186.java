import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TEST
{
	static long[] result;
	static int m,c;
	public static void main(String[] args)throws FileNotFoundException  
	{
		Scanner in=new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter(new File("output.txt"));
		m=in.nextInt();
		c=in.nextInt();
		int n=in.nextInt();
		result=new long[m];
		for(int i=0;i<m;i++)
			result[i]=-1;
		for(int i=0;i<n;i++)
			hash(in.nextLong());
		for(int i=0;i<m-1;i++)
			out.print(result[i]+" ");
		out.print(result[m-1]);
		out.close();
			
	}
	static void hash(long value)
	{
		int col=0;
		int index=(int) ((value%m+c*col)%m);
		boolean flag=false;
		while(result[index]!=-1)
		{
			if(result[index]==value)
			{
				flag=true;
				break;
			}
			col++;
			index=(int) ((value%m+c*col)%m);
		}
		if (!flag)
		{
            result[index]=value;
        }
	}
}

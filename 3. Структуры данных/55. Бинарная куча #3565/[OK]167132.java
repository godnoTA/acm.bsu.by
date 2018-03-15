import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class TEST
{
	public static void main(String[] args)throws FileNotFoundException  
	{
		try (Scanner in=new Scanner(new File("input.txt")))
		{
			PrintWriter out = new PrintWriter(new File("output.txt"));
			boolean flag=false;
			int n=in.nextInt();
			if(n==1)
			{
				out.print("Yes");
				out.close();
				return;
			}
			int[] mas=new int[n+1];
			for(int i=1;i<=n;i++)
				mas[i]=in.nextInt();
			for(int i=1;i<=n;i++)
			{
				if(2*i<n)
				{
					if(mas[i]<=mas[2*i] && mas[i]<=mas[2*i+1])
					{
						flag=true;
						continue;
					}
					else
					{
						flag=false;
						break;
					}
				}
				if(2*i==n)
				{
					if(mas[i]<=mas[2*i])
					{
						flag=true;
						continue;
					}
					else
					{
						flag=false;
						break;
					}
				}
				if(2*i>n)
					break;
			}
			if(flag==true)
				out.print("Yes");
			else
				out.print("No");
			out.close();
		}
	}

}

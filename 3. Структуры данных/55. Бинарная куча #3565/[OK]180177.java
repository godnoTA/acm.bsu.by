import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Kucha
{		 
	public static void main(String [] args) throws IOException
	{
		int n;
		Scanner fin=new Scanner(new File("input.txt"));	
		FileWriter fout = new FileWriter("output.txt");
		n=fin.nextInt();
		if((n<1)||(n>100000))
			return;
		int []mas = new int[n+1];
		int count=1;
		while(count < n+1)
		{		
			mas[count]=fin.nextInt();  
			count++;
		}
		boolean check=true;
		for(int i=1; i<=n; i++)
		{
			if(2*i+1<=n)
			{
				if((mas[i]>mas[2*i])||(mas[i]>mas[2*i+1]))
				{
					check=false;
					break;
				}
			}
			else
			{
				if(2*i==n)
				{
					if(mas[i]>mas[2*i])
					{
						check=false;
						break;
					}
				}
			}
		}
		if(check==true)
			
			fout.write("Yes");
		else 
			fout.write("No");	
		fout.close();
	    return;
	}
}

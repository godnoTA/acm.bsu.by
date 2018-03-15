import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class test 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter(new File("output.txt"));
		int n = in.nextInt(), m = in.nextInt(), d = in.nextInt();
		int count = 0;
		int ft, sd;
		int[] l = new int[n+1];
		for (int i = 1; i <= n; i++)
			l[i] = in.nextInt();
		int[][] cas1 = new int[m + 1][d + 1];
		int[][] cas2 = new int[m + 1][d + 1];
		for (int i = 0; i <= m; i++) 
		{
			for (int j = 0; j <= d; j++) 
			{
				cas1[i][j] = -1;
				cas2[i][j] = -1;
			}
		}
		cas1[1][0] = 0;
		for (int s = 0; s < n; s++) 
		{
			for (int cas = 1; cas <= m; cas++) 
			{
				for (int tm = 0; tm <= d; tm++) 
				{
					ft=cas;
					if (cas1[cas][tm] >= 0) 
					{
						sd = tm + l[s + 1];
						if (sd > d && ft < m) 
						{
							sd = l[s + 1];
							ft++;
						}
						if (cas2[cas][tm] < cas1[cas][tm])
							cas2[cas][tm] = cas1[cas][tm];
						if (sd <= d && cas2[ft][sd] < cas1[cas][tm] + 1)
							cas2[ft][sd] = cas1[cas][tm] + 1;
					}
				}
			}
			for (int i = 0; i <= m; ++i) 
			{
				for (int j = 0; j <= d; ++j) 
				{
					cas1[i][j] = cas2[i][j];
					cas2[i][j] = -1;
				}
			}
		}
		for (int i = 0; i <= m; i++) 
			for (int j = 0; j <= d; j++) 
				if (cas1[i][j]>count) 
					count=cas1[i][j];
		out.print(count);
		out.close();
	}
}

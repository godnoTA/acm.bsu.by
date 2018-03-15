import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TEST
{
	static boolean[] used;
	static int n;
	static int[] marks;
	static int number=1;
	static int[][] matrix;
	public static void main(String[] args)throws FileNotFoundException
	{
		Scanner in=new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter(new File("output.txt"));
		n=in.nextInt();
		if(n==1)
		{
			out.print(1);
			out.close();
			return;
		}
		used=new boolean[n];
		marks=new int[n];
		for(int i=0;i<n;i++)
			used[i]=true;
		matrix=new int[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
				matrix[i][j]=in.nextInt();
		}
		for(int i=0;i<n;i++)
			if(used[i])
				dfs(i);
		for(int i=0;i<n;i++)
			out.print(marks[i]+" ");
		out.close();
	}
	static void dfs(int i)
	{
		used[i]=false;
		marks[i]=number;
		number++;
		for(int j=0;j<n;j++)
			if(matrix[i][j]==1 && used[j])
				dfs(j);
	}
}

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileInputStream("input.txt"));
		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		
		int n, m;
		n = in.nextInt();
		m = in.nextInt();
		
		boolean f1 = false, f2 = false;
		int[][] mas = new int[n+1][n+1];
		int x, y;
		for (int i = 0; i < m; i++)
		{
			x = in.nextInt();
			y = in.nextInt();
			if (x == y)
			{
				f1 = false;
				f2 = true;
				break;
			}
			if (mas[x][y] != 0)
			{
				f1 = f2 = true;
			}
			mas[x][y] = mas[y][x] = 1;
		}
		if (f1 == false && f1 == f2)
		{
			out.println("Yes");
			out.println("Yes");
			out.println("Yes");
		}
		else
		{
			out.println("No");
			if (f1 == true)
				out.println("Yes");
			else
				out.println("No");
			if (f2 == true)
				out.println("Yes");
			else
				out.println("No");
		}
		
		in.close();
		out.close();
		System.exit(0);
	}

}

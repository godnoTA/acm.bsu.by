import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileInputStream("input.txt"));
		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		
		int n, m;
		n = in.nextInt();
		m = in.nextInt();
		int[][] mas = new int[n][n];
		
		for (int i = 0; i < m; i++)
		{
			int x = in.nextInt() - 1;
			int y = in.nextInt() - 1;
			mas[x][y] = mas[y][x] = 1;
		}
		
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				out.print(mas[i][j] + " ");
			out.println();
		}
		
		in.close();
		out.close();
		System.exit(0);
	}

}

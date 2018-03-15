import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;


public class MatrPr {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File("input.txt"));
		PrintStream out = new PrintStream(new File("output.txt"));
		
		int N = in.nextInt();
		int[] a = new int[N + 1];
		for (int i = 0; i < N; i++ )
		{
			a[i] = in.nextInt();
			if (i != N-1)
				in.nextInt();
		}
		a[N] = in.nextInt();
		
		int[][] kol = new int [N + 1][N + 1];
		
		for (int p = 2; p <= N; p++) {
			for (int i = 1; i <= N - p + 1; i++) {
				int j = i + p - 1;
				kol[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					kol[i][j] = Math.min(kol[i][j], kol[i][k] + kol[k + 1][j] + a[i - 1] * a[k] * a[j]);
				}
			}
		}
		
		out.println(kol[1][N]);
		in.close();
		out.close();
		System.exit(0);
	}

}

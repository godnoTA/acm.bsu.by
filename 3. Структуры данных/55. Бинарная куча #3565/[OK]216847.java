import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileInputStream("input.txt"));
		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		
		int N = in.nextInt();
		int[] a = new int[N];
		for (int i = 0; i < N; i++)
			a[i] = in.nextInt();
		
		boolean flag = true;
		int n = (N % 2 == 0) ? N/2 - 1 : N/2;
		for (int i = 0; i < n; i++)
		{
			if (a[i] > a[2*i + 1] || a[i] > a[2*i + 2])
			{
				flag = false;
				break;
			}
		}
		if (n == (N/2)-1)
			if(a[n] > a[2*n + 1])
				flag = false;
		if (!flag)
			out.println("No");
		else
			out.println("Yes");
		
		in.close();
		out.close();
		System.exit(0);
	}

}

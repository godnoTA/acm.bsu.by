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
		
		int m, c, n;
		m = in.nextInt();
		c = in.nextInt();
		n = in.nextInt();
		
		Stack st = new Stack();
		int[] mas = new int[m];
		for (int i = 0; i < m; i++)
			mas[i] = -1;
		for (int i = 0; i < n; i++)
		{
			int x = in.nextInt();
			if (!st.contains(x))
			{
				st.push(x);
				int k = 0;
				int h = ((x % m) + c * k) % m;
				while (mas[h] != -1)
				{
					k++;
					h = ((x % m) + c * k) % m;
				}
				mas[h] = x;
			}
		}
		
		for (int i = 0; i < m; i++)
			out.print(mas[i] + " ");
		
		in.close();
		out.close();
		System.exit(0);
	}

}

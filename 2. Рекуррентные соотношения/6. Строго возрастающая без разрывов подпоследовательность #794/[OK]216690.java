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
		int[] seq = new int[N];
		int[] ind = new int[N+1];
		for (int i = 0; i < N; i++)
			seq[i] = in.nextInt();
		
		int L = 0;
		for (int i = 0; i < N; i++)
		{
			int beg = 1;
			int end = L;
			while (beg <= end)
			{
				int mid = (int)Math.ceil(((double)beg + end)/2);
				if (seq[ind[mid]] < seq[i])  
					beg = mid + 1;
				else
					end = mid - 1;
			}
			if (beg > L)
				L = beg;
			ind[beg] = i;
		}
		
		out.println(L);
		
		in.close();
		out.close();
		System.exit(0);
	}

}

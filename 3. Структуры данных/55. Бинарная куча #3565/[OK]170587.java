
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test_56 {
	private static long [] Heap;
	private static int N;
	static boolean build_heap()
	{
		//if(Heap.length < 1)
		//	return false;
		for (int i = 1 ; i <= N; i++)
		{
if(2 * i  <= N){
				if(Heap[i ] > Heap[2 * i] || ((2 * i + 1 <= N) &&  Heap[i] > Heap[2 * i + 1]))
					return false;}
		}
		return true;
	}
	public static void main(String [] args) throws IOException{
		Scanner scan = new Scanner(new File("input.txt"));
		N = scan.nextInt();
		int i = 1;
		Heap = new long[N + 1];
		while(i <= N)
		{
			Heap[i] = scan.nextLong();
			i ++;
		}
		scan.close();
		FileWriter out = new FileWriter("output.txt");
		if(build_heap() == false)
			out.write("No\n");
		else
			out.write("Yes\n");
		out.close();
	}
}
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Chip {
	public static void main(String []args) throws IOException{
		Scanner scan = new Scanner(new File("input.txt"));
		FileWriter out = new FileWriter("output.txt");
		int T = scan.nextInt();
		int count = 1;
		while(count <= T){
			int N = scan.nextInt() - 1;
			int K = scan.nextInt();
			int[] array = new int[N + 1];
			array[0] = array[1] = 1;
			for(int i = 2; i <= N; i++){
				for(int j = 1; j <= K && j <= i; j++){
					array[i] += array[i - j];
				}
			}
			out.write(Integer.toString(array[N]) + "\n");
			count++;
		}
		scan.close();
		out.close();
	}
}

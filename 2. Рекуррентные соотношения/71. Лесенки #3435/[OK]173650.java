import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Stairs {
	public static void main(String []args) throws IOException{
		Scanner scan = new Scanner(new File("input.txt"));
		FileWriter out = new FileWriter("output.txt");
		int N = scan.nextInt();
		long[][] array = new long[N][N];
		for(int i = 0; i < N; i ++)
		 {
		    for(int j = 0; j < N; j ++)
		    	array[i][j] = 0;
		    array[i][i] = 1;
		}
		for(int i = 1; i < N; i ++){
		    for(int j = i - 1; j >= 0; j --){
		    	array[i][j] = array[i - j - 1][j + 1] + array[i][j + 1];
		    }
		}
		out.write(Long.toString(array[N-1][0]));
		//System.out.println(Long.toString(array[N-1][0]));
		scan.close();
		out.close();
	}
}

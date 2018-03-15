import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Test_0_3 {
	public static int multiplyOrder(int[] array) {
		int n = array.length ;
		int[][] tmp = new int[n][n];
		
		for (int i = 1; i < n; i++) {
			tmp[i][i] = 0;
		}
		
		for (int l = 2; l < n; l++) {
			for (int i = 1; i < n - l + 1; i++) {
				int j = i + l - 1;
				tmp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					tmp[i][j] = Math.min(tmp[i][j],
                                                   tmp[i][k] + tmp[k + 1][j] + array[i - 1] * array[k] * array[j]);
				}
			}
		}
		return tmp[1][n - 1]; 
	}
	public static void main(String []args) throws IOException{
		Scanner scan = new Scanner(new File("input.txt"));
		int N = scan.nextInt();
		int[] array = new int [N + 1];
		int count = 0;
		while(count < N){
			array[count] = scan.nextInt();
			if(count == N-1){
				count++;
				array[count] = scan.nextInt();
			}
			else
			{	
				scan.nextInt();
				count++;
			}
		}
		scan.close();
		FileWriter out = new FileWriter("output.txt");
		out.write(Integer.toString(multiplyOrder(array)));
		out.close();
	}
}

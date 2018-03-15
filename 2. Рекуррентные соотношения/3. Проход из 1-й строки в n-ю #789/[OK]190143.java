import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("input.txt")); 
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int array[][] = new int[n][m];
		
		long fine[][] = new long[n][m];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				array[i][j] = sc.nextInt();
				//fine[i][j] = array[i][j];
				
				//System.out.print(array[i][j] + " ");
			}
			
			//System.out.println();
		}
		//System.out.println();
		
		for(int j = 0; j < m; j++) {
			fine[0][j] = array[0][j];
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < m; j++) {
				
				fine[i][j] = array[i][j] + fine[i - 1][j];

				if(j > 0 && fine[i][j] > array[i][j] + fine[i - 1][j - 1]){
					fine[i][j] =  array[i][j] + fine[i - 1][j - 1];
				}
				if(j < m - 1 && fine[i][j] > array[i][j] + fine[i - 1][j + 1]){
					fine[i][j] =  array[i][j] + fine[i - 1][j + 1];
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {				
				System.out.print(fine[i][j] + " ");
			}
			
			System.out.println();
		}
		
		long min = fine[n - 1][0];
		
		for(int j = 1; j < m; j++) {
			if(min > fine[n - 1][j]) {
				min = fine[n - 1][j];
			}
		}
		
		FileWriter writer = new FileWriter("output.txt");
		writer.write(String.valueOf(min));
		writer.close();
	}

}
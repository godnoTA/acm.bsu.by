import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("input.txt")); 
		
		int k = sc.nextInt();
		
		int trigon[][] = new int[k][k];
		int fine[][] = new int[k][k];
		
		for(int i = 0; i < k; i++) {
			for(int j = 0; j <= i; j++) {
				trigon[i][j] = sc.nextInt();
				fine[i][j] = trigon[i][j];
				
				//System.out.print(trigon[i][j] + " ");
			}
			
			//System.out.println();
		}
		//System.out.println();
		
		for(int j = 0; j < k; j++){
			fine[k - 1][j] = trigon[k - 1][j];
			//System.out.print(fine[k - 1][j] + " ");
		}
		//System.out.println();
		
		for(int i = k - 2; i >= 0; i--) {
			for(int j = 0; j <= i; j++) {
				fine[i][j] = fine[i + 1][j] + trigon[i][j];
				
				if(fine[i][j] < fine[i + 1][j + 1] + trigon[i][j]){
					fine[i][j] = fine[i + 1][j + 1] + trigon[i][j];
				}
				
				//System.out.print(fine[i][j] + " ");
			}			
			//System.out.println();
		}
		//System.out.println();
		
		
		FileWriter writer = new FileWriter("output.txt");
		writer.write(String.valueOf(fine[0][0]));
		writer.close();
	}
}
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File("input.txt"));
		int numEdges = sc.nextInt();
		int[] matrix = new int[numEdges];		
		int k;	
		for(int i = 0; i < numEdges; i++){
			for(int j = 0; j < numEdges; j++){
				k = sc.nextInt();				
				if(k == 1){
					System.out.print(k);
					matrix[j] = i + 1;
				}
			}
		}	
        FileWriter fileWriter = new FileWriter("output.txt");
        for (int i = 0; i < numEdges; i++) {
        	fileWriter.write(matrix[i] + " ");
        }
        fileWriter.close();
	}
}
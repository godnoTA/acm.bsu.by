import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("input.txt")); 
		int numVertexs = sc.nextInt();
        int[][] matrix = new int[numVertexs][numVertexs];
        
        int numEdges = sc.nextInt();
        
        int x;
        int y;
        for(int k = 0; k < numEdges; k++){
        	x = sc.nextInt();
        	y = sc.nextInt();
        	
        	matrix[x-1][y-1] = 1;
        	matrix[y-1][x-1] = 1;
        }
        
        int mLenght = matrix.length;
        
        FileWriter fileWriter = new FileWriter("output.txt");
        for (int i = 0; i < mLenght; i++) {
            for (int j = 0; j < mLenght; j++) {
                fileWriter.write(matrix[i][j] + " ");
            }
            if (i < mLenght - 1) {
                fileWriter.write("\n");
            }
        }
        fileWriter.close();
	}
}

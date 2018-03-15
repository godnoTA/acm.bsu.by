import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("input.txt"));
		int numEdges = sc.nextInt();
		int[] matrix = new int[numEdges];
        int x;
        int y;
        for (int i = 0; i < numEdges - 1; i++) {
        	x = sc.nextInt();
        	y = sc.nextInt();
        	matrix[y-1] = x;
        }
        FileWriter fileWriter = new FileWriter("output.txt");
        for (int i = 0; i < numEdges; i++) {
        	fileWriter.write(matrix[i] + " ");
        }
        fileWriter.close();
	}

}
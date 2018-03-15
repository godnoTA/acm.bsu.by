import java.io.File;
import java.io.FileWriter; 
import java.io.IOException; 
import java.util.Scanner;

public class Main { 
	private static int[][] ribsGraph; 
	private static boolean[] visited; 
	private static int[] marksArray; 
	private static int size; 
	private static int count = 0; 

	public static void main(String[] args) throws IOException { 

		readGraf();
		
		createMarks();

		writeMarks();
	} 
	
	
	private static void readGraf() throws IOException {
		Scanner sc = new Scanner(new File("input.txt")); 
		size = sc.nextInt();
		ribsGraph = new int[size][size]; 
		visited = new boolean[size]; 
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				ribsGraph[i][j] = sc.nextInt();
			}
		}
		sc.close();
	}
	
	
	private static void createMarks() {
		marksArray = new int[size]; 

		for (int i = 0; i < size; i++) { 
			dfs(i, marksArray); 
		} 
	}
	
	
	private static void dfs(int pos, int marksArray[]) { 

	if (!visited[pos]) { 
		marksArray[pos] = count + 1; 
		count++; 
		} 
	visited[pos] = true; 
	for (int i = 0; i < size; i++) { 
		if (ribsGraph[pos][i] == 1 && !visited[i]) { 
			dfs(i, marksArray); 
			} 
		} 
	} 
	
	
	private static void writeMarks() throws IOException {
		FileWriter fileWriter = new FileWriter("output.txt"); 
		for (int i = 0; i < size; i++) { 
			fileWriter.write((marksArray[i] + " ")); 
			} 
		fileWriter.close(); 
	}
}
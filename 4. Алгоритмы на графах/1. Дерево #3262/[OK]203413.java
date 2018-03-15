import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	private static int size; 
	private static int[][] ribsGraph;
	private static boolean[] visited;
	public static boolean c = true;
	public static int numRibs = 0;
	
	public static void main(String[] args) throws IOException { 
		readGraf();
		dfs(0, visited);
		calcRibs();
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
	
	private static int dfs(int u, boolean[] visited) {
		int visitedVertices = 1;
		visited[u] = true;
		for(int v = 0; v < size; v++){
			if(ribsGraph[u][v] == 1 && !visited[v]){
				visitedVertices += dfs(v, visited);
			}
		}
		return visitedVertices;
	}
	
	private static void calcRibs() {
		for(int i = 1; i < size; i++){
			for(int j = 0; j <= i; j++){
				if(ribsGraph[i][j] == 1){
					numRibs++;
				}
			}
		}
	}
	
	private static void writeMarks() throws IOException {
		FileWriter fileWriter = new FileWriter("output.txt"); 
		
		for(int k = 0; k < size; k++){
			if(!visited[k]) {
				c = false;
			}
		}
		
		if(c && numRibs+1 == size){
			fileWriter.write("Yes");
		} else {
			fileWriter.write("No");
		}

		fileWriter.close(); 
	}
}
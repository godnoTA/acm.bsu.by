import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	private static int size; 
	private static int[][] ribsGraph;
	private static boolean[] visited;
	private static int[] marksArray; 
	private static int count = 0; 
	public static boolean c = true;
	
	public static void main(String[] args) throws IOException { 
		readGraf();
		dfs(0, visited);
		writeMarks();	
	}
	
	
	private static void readGraf() throws IOException {
		Scanner sc = new Scanner(new File("input.in")); 
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
	
	private static void writeMarks() throws IOException {
		FileWriter fileWriter = new FileWriter("output.out"); 
		
		for(int k = 0; k < size; k++){
			if(!visited[k]) {
				c = false;
			}
		}
		
		if(c){
			fileWriter.write("YES");
		} else {
			fileWriter.write("NO");
		}

		fileWriter.close(); 
	}
}
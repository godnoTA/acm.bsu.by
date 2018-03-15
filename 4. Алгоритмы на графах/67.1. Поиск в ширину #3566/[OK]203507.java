import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int[][] ribsGraph; 
	private static boolean[] visited; 
	private static int[] marksArray; 
	private static int size; 
	private static int cnt = 1;
	
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
		marksArray = new int[size]; 
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				ribsGraph[i][j] = sc.nextInt();
			}
		}
		sc.close();
	}
	
	private static void createMarks() {
		

		for (int i = 0; i < size; i++) { 
			bfs(i); 
		} 
	}
	
	
	private static void bfs(int unit) { 
		int queue[] = new int[size];
		int count = 0, 
			head = 0;
		for (int i = 0; i < size; i++) {
			queue[i]=0;
		}
		queue[count++]=unit;
		visited[unit] = true;
		while (head < count) {
			unit = queue[head++];
			//cout<<unit+1<<" ";Порядок обхода
			if(marksArray[unit] == 0){
				marksArray[unit] = cnt;
				cnt++;
			}
			for (int i = 0; i < size; i++) {
				if (ribsGraph[unit][i] == 1 && !visited[i]) {
					queue[count++] = i;
					visited[i] = true;
				}
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
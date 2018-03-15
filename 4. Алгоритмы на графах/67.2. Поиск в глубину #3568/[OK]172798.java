import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DFS {
    
    private static int n; 
    private int counter = 0;
    private List<Integer>[] graph;
    private boolean[] used;
    static FileWriter writeFile;
    private static int []result;

    public void dfs(int pos) throws IOException {
    	if (used[pos]) {
            return;
        }
        used[pos] = true; //помечаем вершину как пройденную
        counter++;
        result[pos]=counter;
        for (int i = 0; i < graph[pos].size(); ++i) {
            int w = graph[pos].get(i);
            dfs(w); //вызов обхода в глубину от вершины w, смежной с вершиной v
        }
    }
    
    //процедура считывания входных данных с консоли
    private void readData() throws IOException {
        		List<String> matrix_of_graph= new ArrayList<String>();
        		String str_test = null;
        		try {
        			Scanner sc = new Scanner(new File("input.txt"));
        			n = sc.nextInt();
        			while (sc.hasNextLine()) {
        				if(!((str_test=sc.nextLine()).isEmpty()))
        					matrix_of_graph.add(str_test);
        			}
        			sc.close();
        		} catch (FileNotFoundException e) {};
        
        		int mas[][]= new int[n][n];
        		String temp_mas[]= new String[n];
        		for(int i = 0; i<n; i++){
        			temp_mas = matrix_of_graph.get(i).split(" ");
        			for(int j = 0; j<n; j++){
        				mas[i][j] = Integer.parseInt(temp_mas[j]);
        			}
        		}
        

        graph = new ArrayList[n]; 
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i<n;i++){
        	for(int j = 0; j< n; j++){
        		if(mas[i][j]!=0){
        			graph[i].add(j);
        		}
        	}
        }
        
        result = new int[n];
        used = new boolean[n];
        Arrays.fill(used, false);
        writeFile = new FileWriter(new File("output.txt"));
    }
    
    private void run() throws IOException {
    	readData();
        for (int v = 0; v < n; ++v) {
            dfs( v);
        }
    }
    
    public static void main(String[] args) throws IOException {
        DFS solution = new DFS();
        solution.run();
        for (int i = 0; i<n-1; i++){
        	writeFile.write("" + result[i]+" ");
        }
        writeFile.write(""+result[n-1]);
        writeFile.close();
    }
}
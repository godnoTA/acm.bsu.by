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

public class BFS {
    
    private static int n; 
    private int counter = 0;
    private List<Integer>[] graph;
    private boolean[] used;
    static FileWriter writeFile;
    private static int []result;
    private Queue<Integer> queue;

    private void bfs(int v) { 
        if (used[v]) { //если вершина является пройденной, то не производим из нее вызов процедуры
            return;
        }
        queue.add(v); //начинаем обход из вершины v
        used[v] = true; //помечаем вершину как пройденную
        while (!queue.isEmpty()) {
            v = queue.poll(); //извлекаем вершину из очереди
            counter++;
            result[v]=counter;
            //запускаем обход из всех вершин, смежных с вершиной v
            for (int i = 0; i < graph[v].size(); ++i) { 
                int w = graph[v].get(i);
                //если вершина уже была посещена, то пропускаем ее
                if (used[w]) { 
                    continue;
                }
                queue.add(w); //добавляем вершину в очередь обхода
                used[w] = true; //помечаем вершину как пройденную
            }
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
        queue = new LinkedList<Integer>();
    }
    
    private void run() throws IOException {
    	readData();
        for (int v = 0; v < n; ++v) {
            bfs( v);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BFS solution = new BFS();
        solution.run();
        for (int i = 0; i<n-1; i++){
        	writeFile.write("" + result[i]+" ");
        }
        writeFile.write(""+result[n-1]);
        writeFile.close();
    }
}
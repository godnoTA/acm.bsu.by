import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Breadth {
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static Boolean visited[];
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	static ArrayList<Integer> vector = new ArrayList<Integer>();
	private static Queue<Integer> queue = new LinkedList<Integer>();
	public static void main (String args[]) throws IOException{
		exists(filename); 
		try {

	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
            String s;
	    	int nodes = 0;
			if((s = in.readLine()) != null){
	        	nodes = Integer.parseInt(s);
			}
			int i = 0;
	        	while ((s = in.readLine()) != null) {	        	
	        	String[] str = s.split(" ");
	        	adj.add(new ArrayList<Integer>());
	        	for(int j = 0; j < nodes; j++){
	        		if (Integer.parseInt(str[j]) == 1)
	        			adj.get(i).add(j);
	        	}
	        	i++;
	        }
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	        	visited = new Boolean[nodes];
	        	for(int j = 0; j < nodes; j++){
	        		visited[j] = false;
	        	}
	        	
	        	int[]path = new int[nodes];
	        	
	        	for(int l = 0; l < nodes; l++){
	        		BFS(l);
	        	}
	        	
	        	int key = 1;
	            for(int j = 0; j < vector.size(); j++){
	                path[vector.get(j) - 1] = key;
	                key++;
	            }
	            
	            for(int j = 0; i < vector.size(); i++){
	                System.out.print(path[j] + " ");
	            }
	            for(int j = 0; j < nodes; j++){
	                out.print(path[j] + " ");
	            }
	        } finally {
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    	} finally {
	    		in.close();
	    	}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void exists(String fileName) throws FileNotFoundException {
	    File file = new File(fileName);
	    if (!file.exists()){
	        throw new FileNotFoundException(fin.getName());
	    }
	}
	
	public static void BFS(int visit){
		if(visited[visit] == true)
			return;
		queue.add(visit);
		visited[visit] = true;
		while (!queue.isEmpty()){
			visit = queue.poll();
			vector.add(visit + 1);
			for (int i = 0; i < adj.get(visit).size(); ++i) {
                int next = adj.get(visit).get(i);
                if (visited[next]) {
                    continue;
                }
                queue.add(next);
                visited[next] = true;
            }
		}
	}
}
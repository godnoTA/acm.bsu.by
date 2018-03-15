import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Plan {
	
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static Boolean visited[];
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	static int N = 0;
	public static void main (String args[]) throws IOException{
		ArrayList<Integer> inevitable = new ArrayList<Integer>();
		ArrayList<Integer> split = new ArrayList<Integer>();
		Boolean flag = true;
		exists(filename);
		try {
			
	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
	        try {
	            String s;
	            if ((s = in.readLine()) != null) {
	            	N = Integer.parseInt(s);
	            }
	            
	            int i = 0;
	            String[] dots;
	            while ((s = in.readLine()) != null) {
	            	adj.add(new ArrayList<Integer>());
	            	dots = s.split(" ");
	            	for(int j = 0; j < dots.length - 1; j++){
	            		adj.get(i).add(Integer.parseInt(dots[j]));
	            	}
	            	i++;
	            }

	        } finally {
	            in.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());	        
	 
	        try {	
	        	visited = new Boolean[N];
	        	for(int i = 1; i < N - 1; i++){
	        		while(!adj.get(i).isEmpty()){
	        			split.add(adj.get(i).remove(0));
	        		}
	        		for (int j = 0; j < N; j++){
	        			visited[j] = false;
	        		}
	        		DFS(0);
	        		if (visited[N - 1] == false)
	        			inevitable.add(i);
	        		while(!split.isEmpty()){
	        			adj.get(i).add(split.remove(0));
	        		}
	        	}
	        	
	        	for (int i = 0; i < inevitable.size(); i++){
	        		int pretendent = inevitable.get(i);
	        		for (int j = 0; j < pretendent; j++){
	        			for (int k = 0; k < adj.get(j).size(); k++){
	        				if(adj.get(j).get(k) > pretendent)
	        					flag = false;
	        			}	        				        					        			
	        		}
	        		if (flag){
	        			for (int j = pretendent; j < adj.size(); j++){
		        			for (int k = 0; k < adj.get(j).size(); k++){
		        				if(adj.get(j).get(k) < pretendent)
		        					flag = false;
		        			}	        				        					        			
		        		}
        			}
	        		if (flag){
	        			split.add(pretendent);
	        		}
	        		flag = true;
	        	}
	        	
	        	out.print(inevitable.size());
	        	while(!inevitable.isEmpty()){
	        		out.print(" " + inevitable.remove(0));
	        	}
	        	out.println();
	        	out.print(split.size());
	        	while(!split.isEmpty()){
	        		out.print(" " + split.remove(0));
	        	}
	        	
	        } finally {
	            out.close();
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
	
	public static void DFS(int visit){
		if(visited[visit] == true)
			return;
		visited[visit] = true;
		for (int i = 0; i < adj.get(visit).size(); i++){
	        int next = adj.get(visit).get(i);
	        DFS(next);
		}
	}
}
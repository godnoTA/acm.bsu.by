import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Pair{
	int enter;
	int exit;
	public Pair(int x, int y) {
		this.enter = x;
		this.exit = y;
	}
	public Pair(){
		this.enter = 0;
		this.exit = 0;
	}
}

public class Word {
	
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	static ArrayList<Character> letters = new ArrayList<Character>();
	public static Boolean visited[];
	static ArrayList<Integer> vector = new ArrayList<Integer>();
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	static String[] words;
	static int N = 0;
	public static void main (String args[]) throws IOException{
		Map<Character, Pair> letters4 = new HashMap<Character, Pair>();
		Map<Character, Integer> vocabulary = new HashMap<Character, Integer>();
		Boolean flag = true;
		exists(filename);
		try {
	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
	        try {
	            String s;
	            if ((s = in.readLine()) != null) {
	            	N = Integer.parseInt(s);
	            }
	            words = new String[N];
	            
	            int i = 0;
	            int k = 0;
	            while ((s = in.readLine()) != null) {
	            	words[i++] = s;
	            	char l = s.charAt(0);
	            	if (!letters.contains(l)){
	            		letters.add(l);
	            		letters4.put(l, new Pair(1, 0));
	            		vocabulary.put(l, k);
	            		k++;
	            	}
	            	else {
	            		letters4.put(l, new Pair(letters4.get(l).enter + 1, letters4.get(l).exit));
	            	}
	            	l = s.charAt(s.length() - 1);
	            	if (!letters.contains(l)) {
	            		letters.add(l);
	            		letters4.put(l, new Pair(0, 1));
	            		vocabulary.put(l, k);
	            		k++;
	            	}
	            	else{
	            		letters4.put(l, new Pair(letters4.get(l).enter, letters4.get(l).exit + 1));
	           		}
	            }
	            
	            for(int j = 0; j < letters.size(); j++){
	        		adj.add(new ArrayList<Integer>());
	        	}
	            for(int j = 0; j < N; j++){
	        		adj.get(vocabulary.get(words[j].charAt(0))).add(vocabulary.get(words[j].charAt(words[j].length() - 1)));
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
	        	
	        	for(int i = 0; i < letters.size(); i++){
	        		char c = letters.get(i);
	        		if(letters4.get(c).enter != letters4.get(c).exit){
	        			flag = false;
	        			break;
	        		}
	        	}
	        	
	        	visited = new Boolean[letters.size()];
	        	for(int j = 0; j < letters.size(); j++){
	        		visited[j] = false;
	        	}
	        	

        		DFS(0);
        		for(int j = 0; j < letters.size(); j++){
	        		if (visited[j] == false){
	        			flag = false;
	        			break;
	        		}	        		
	        	}
	        	
	        	if(flag) out.print("Yes");
	        	else out.print("No");
	        	
	        	
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
		vector.add(visit + 1);
		for (int i = 0; i < adj.get(visit).size(); i++){
	        int next = adj.get(visit).get(i);
	        DFS(next);
		}
	}
}
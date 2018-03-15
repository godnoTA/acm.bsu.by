import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Hash {
	
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	static ArrayList<Integer> places = new ArrayList<Integer>();
	static ArrayList<Integer> keys = new ArrayList<Integer>();
	static int numbers[];
	static int hash[];
	static int n = 0;
	static int m = 0;
	static int c = 0;
	public static void main (String args[]) throws IOException{
		exists(filename);
		try {
	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
	        try {
	            String s;
	            if ((s = in.readLine()) != null) {
	            	String[] str = s.split(" ");
	            	m = Integer.parseInt(str[0]);
	            	c = Integer.parseInt(str[1]);
	            	n = Integer.parseInt(str[2]);
	            }
	            numbers = new int[n];
	            hash = new int[m];
	            while ((s = in.readLine()) != null) {
	            	//numbers[i] = Integer.parseInt(s);
	            	if(!keys.contains(Integer.parseInt(s)))
	            		keys.add(Integer.parseInt(s));
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
	        	
	        	for(int i = 0; i < m; i++){
	        		hash[i] = -1;
	        	}
	        	
	        	while(!keys.isEmpty()){
	        		int j = 0;
	        		int pretendent;
	        		do{
	        			pretendent = table(keys.get(0), j);
	       				j++;
	       			}while(hash[pretendent] != -1);
	       			hash[pretendent] = keys.remove(0);
	       			places.add(pretendent);
	        	}
	        	System.out.println();
	        	for(int i = 0; i < m; i++){
	        		out.print(hash[i] + " ");
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
	
	private static int table(int x, int i){
		return ((x % m) + c * i) % m;
	}
}
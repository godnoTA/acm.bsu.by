import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Heap {
	
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static void main (String args[]) throws IOException{
		ArrayList<Long> trees = new ArrayList<Long>();
		exists(filename);
		long N = 0;
		try {
	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
	        try {
	            String s;
	            if((s = in.readLine()) != null)
	            	N = Long.parseLong(s);
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

	        	long M = 0;
	        	int i = 0;
	        	if (N != 1){
	        		while(N >= 2){
	        			M = (long) (M + Math.pow(10, i)*(N%2));
	        			trees.add(N%2);
	        			i++;
	        			N /= 2;	 
	        			if (N < 2){
	        				M = (long) (M + Math.pow(10, i)*N);
	        				trees.add(N);
	        				break;
	        			}
	        		}
	        		i = 0;
	        		for(long l: trees){
	        			if(l == 1)
	        				out.println(i);
	        			i++;
	        		}
	        	}
	        	else{
	        		out.print(0);
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
}
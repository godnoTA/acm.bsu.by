import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BinaryHeap {
	
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static void main (String args[]) throws IOException{
		exists(filename);
		int N = 0;
		ArrayList<Long> knots = new ArrayList<Long>();
		try {
	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
	        try {
	            String s;
	            if((s = in.readLine()) != null)
	            	N = Integer.parseInt(s);
	            if ((s = in.readLine()) != null) {
	            	String[] str = s.split(" ");
	            	for(String k: str){
	            		knots.add(Long.parseLong(k));
	            }
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

	        	Boolean flag = true;

	        	for(int j = N - 1; j > 0; j--){
        			if (knots.get(j) < knots.get((j + 1)/2 - 1))
        				flag = false;
        		}
	        	if(flag)
	        		out.print("Yes");
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
}
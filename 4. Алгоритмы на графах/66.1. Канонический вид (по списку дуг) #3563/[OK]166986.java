import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Form {
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static void main (String args[]) throws IOException{
		exists(filename);
		List<Point> curves = new ArrayList<Point>();
		try {

	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
            String s;
	    	int n = 0;
			if((s = in.readLine()) != null)
	        	n = Integer.parseInt(s);
	    	int[] canon = new int[n];
	        try {

	            while ((s = in.readLine()) != null) {
	            	String[] str = s.split(" ");
	            	Point x = new Point(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
	            	curves.add(x);
	            }
	        } finally{
	        	
	        }
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	        	for(Point p: curves){
	        		canon[p.y - 1] = p.x;
	        	}
	        	for(int i = 0; i < n; i++){
	        		if (canon[i] == 0) canon[i] = 0;
	        	}
	        	for(int i: canon){
	        		out.print(i + " ");
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
}
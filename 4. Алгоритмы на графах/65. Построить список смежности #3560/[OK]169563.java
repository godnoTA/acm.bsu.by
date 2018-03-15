import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Source {
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static void main (String args[]) throws IOException{
		exists(filename);
		ArrayList<ArrayList> lines = new ArrayList<ArrayList>();
		try {
	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
            String s;
	    	int nodes = 0;
	    	int edges = 0;
			if((s = in.readLine()) != null){
				String[] str = s.split(" ");
				Point n = new Point(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
	        	nodes = n.x;
	        	edges = n.y;
			}
			
			for(int i = 0; i < nodes; i++){
				lines.add(new ArrayList<Integer>());
			}
			
			int x = 0;
			int y = 0;
			
	        	while ((s = in.readLine()) != null) {	        	
	        	String[] str = s.split(" ");
	        	x = Integer.parseInt(str[0]);
	        	y = Integer.parseInt(str[1]);
	        	lines.get(x - 1).add(y);
	        	lines.get(y - 1).add(x);
	        }
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	        	for (ArrayList l: lines){
	        		out.print(l.size() + " ");
	        		for(Object i: l){
	        			out.print(i.toString() + " ");
	        			
	        		}
	        		out.println();	        		
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
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Matrix {
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static void main (String args[]) throws IOException{
		exists(filename);
		List<Point> lines = new ArrayList<Point>();
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
	        	while ((s = in.readLine()) != null) {	        	
	        	String[] str = s.split(" ");
	        	Point n = new Point(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
	       		lines.add(n);
	        }
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	        	SymMatrix M = new SymMatrix(nodes);
	        	for(Point p: lines){
	        		M.arr[p.x - 1][p.y - 1] = 1;
	        		M.arr[p.y - 1][p.x - 1] = 1;
	        	}
	    	    
	    	    M.Print(out);
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

class SymMatrix {

	static final int MAX_A = 100;

    int N;
    int [][] arr = null;

    public SymMatrix() {
        N = 0;
        arr = null;
    }

    public SymMatrix( int n ) {
        assert( n > 0 );
        Init( n );
    }
    
    public void Print(PrintWriter out) {
        assert( N > 0 ): "Assertion failed: N > 0";
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                out.print( arr[i][j] + " " );
            }
            if (i < N - 1) out.println();
        }
    }

    private void Init( int n ) {
        assert( n > 0 );
        arr = new int [n][n];
        N = n;
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                arr[i][j] = 0;
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MatrixForm {
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static void main (String args[]) throws IOException{
		exists(filename);
		try {

	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
            String s;
	    	int nodes = 0;
			if((s = in.readLine()) != null){
	        	nodes = Integer.parseInt(s);
			}
			Matrix M = new Matrix(nodes);
			int i = 0;
	        	while ((s = in.readLine()) != null) {	        	
	        	String[] str = s.split(" ");
	        	for(int j = 0; j < nodes; j++){
	        		M.arr[i][j] = Integer.parseInt(str[j]);
	        	}
	        	i++;
	        }
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	        	int[] canon = new int[nodes];
	        	for(int j = 0; j < nodes; j++)
	        		for(i = 0; i < nodes; i++)
	        		{
	        			if(M.arr[i][j] == 1){
	        				canon[j] = i + 1;
	        			}
	        		}
	        	for(int n: canon){ 
	        		out.print(n + " ");
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

class Matrix {

	static final int MAX_A = 100;

    int N;
    int [][] arr = null;

    public Matrix() {
        N = 0;
        arr = null;
    }

    public Matrix( int n ) {
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
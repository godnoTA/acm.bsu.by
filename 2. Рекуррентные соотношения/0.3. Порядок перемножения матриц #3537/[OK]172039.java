import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Priority {
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	static int quantity = 0;
	static long[] sizes;
	public static void main (String args[]) throws IOException{
		exists(filename); 
		try {

	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
            String s;
			if((s = in.readLine()) != null){
				quantity = Integer.parseInt(s);
			}
			sizes = new long[quantity + 1];
			int i = 0;
			s = in.readLine();
			String[] str = s.split(" ");
        	sizes[i] = Long.parseLong(str[0]);
        	sizes[i + 1] = Long.parseLong(str[1]);
        	i += 2;
			while ((s = in.readLine()) != null) {
            	str = s.split(" ");
            	sizes[i] = Long.parseLong(str[1]);
            	i++;
            }
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	        	out.print(multiplyOrder(sizes));

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
	
	public static int multiplyOrder(long[] p) {
		int n = p.length - 1;
		int[][] dp = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			dp[i][i] = 0;
		}
		
		for (int l = 2; l <= n; l++) {
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					dp[i][j] = (int) Math.min(dp[i][j],
                                                   dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
				}
			}
		}
		return dp[1][n]; 
	}
	
}
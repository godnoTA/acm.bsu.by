import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Japan2 {
	final static String filename = "in.txt";
	static File fin = new File(filename);
	static File fout = new File("out.txt");
	static Long length = (long) 0;
	static int g = 0;
	static Long[] code;
	public static void main (String args[]) throws IOException{
		exists(filename); 
		try {

	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
            String s;
			if((s = in.readLine()) != null){
	        	length = Long.parseLong(s);
			}
			s = in.readLine();
			g = Integer.parseInt(s);
			code = new Long[g];
			in.readLine();			
			for (int i = 0; i < g; i++){
				s = in.readLine();
				code[i] = Long.parseLong(s);
			}
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	        	int n1 = Length(g, length);
	        	out.print(Combining(BigInteger.valueOf(n1 + 1), BigInteger.valueOf(g)));

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
	
	public static BigInteger factorial(BigInteger n)
    {
        BigInteger ret = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) ret = ret.multiply(i);
        return ret;
    }
	
	public static int Length(int k, Long n)
    {
		int len = 0;
		for (int i = 1; i <= k; i++){
			len += code[i - 1];						
		}
		return (int) (n - len);
    }
	
	public static BigInteger Combining(BigInteger n, BigInteger k){
		if (n.compareTo(k) < 0) return BigInteger.ZERO;
		BigInteger mult = BigInteger.ONE;
		for (BigInteger i = n; i.compareTo((n.subtract(k)).add(BigInteger.ONE)) >= 0; i = i.subtract(BigInteger.ONE)){
			mult = mult.multiply(i);
		}
		k = factorial(k);
		return mult.divide(k);
	}
}
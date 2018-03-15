import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class rabbit {
	private static long getFibb(long n, int MOD) { 
		long a11 = 1, a12 = 1, a21 = 1, a22 = 0; 
		long res11 = 1, res12 = 0; 
		long q11 = 0, q12 = 0, q21 = 0, q22 = 0; 
		while (n > 0) { 
			if ((n&1)==1) { 
			q11 = (res11 * a11 + res12 * a21) % MOD; 
			q12 = (res11 * a12 + res12 * a22) % MOD; 
			res11 = q11; 
			res12 = q12; 
			} 
			q11 = (a11 * a11 + a12 * a21) % MOD; 
			q12 = (a11 * a12 + a12 * a22) % MOD; 
			q21 = (a21 * a11 + a22 * a21) % MOD; 
			q22 = (a21 * a12 + a22 * a22) % MOD; 
			a11 = q11; 
			a12 = q12; 
			a21 = q21; 
			a22 = q22; 
	
			n >>= 1; 
		} 
		return res12; 
		} 
	
	
	public static void main(String[] args) /*throws FileNotFoundException, IOException*/
	{
		long res = 8;
		File fin = new File("input.txt");
		File fos = new File("output.txt");
		try{//(BufferedReader fin=new BufferedReader(
//				 new InputStreamReader(
//						 new FileInputStream("input.txt"))))
//		        {
			Scanner in = new Scanner(fin);
			FileWriter out = new FileWriter(fos);
				 String s;
				 s = in.nextLine();
		         //s=fin.readLine();
		        long n = Long.parseLong(s);
		        int N = 1000000007;
		        res = getFibb(n+1,N);
		        out.write(Long.toString(res));
		        in.close();
		        out.close();
		        }
		catch(IOException ex){
            
            System.out.println(ex.getMessage());
        } 
		
		
//		 try(BufferedWriter bw = new BufferedWriter(
//				 new FileWriter("output.txt")))
//	        {
//	            bw.write(res +"");
//	        }
//	        catch(IOException ex){
//	             
//	            System.out.println(ex.getMessage());
//	        } 
	}
}

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.math.*;
import java.text.*;

public class Rek {
	
	public static final String FILE_INPUT = "input.txt";
	public static final String FILE_OUTPUT = "output.txt";
	
	private static void appendUsingFileWriter(String filePath, double text) {
		File file = new File(filePath);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file,true);
			String t[]= Double.toString(text).split("\\.");
			fr.write(t[0]);
			fr.write("\n");
			//System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}//*/
		finally{
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//*/
	}
	
	private static void appendUsingFileWriter(String filePath, String text) {
		File file = new File(filePath);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file,true);
			fr.write(text);
			fr.write("\n");
			//System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}//*/
		finally{
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//*/
	}
	
	//private static void 
	
	public static int matrMult(int matr[], int N){
		int matrr[][] = new int[N][N];
		for(int i=0; i < N; i++){
			matrr[i][i]=0;
		}
		for(int L=2; L <= N; L++){
			for(int i = 0; i < N - L + 1; i++){
				int j = i + L -1;
				matrr[i][j] = Integer.MAX_VALUE;
				for(int k = i; k <= j - 1; k++){
					int q = matrr[i][k] + matrr[k + 1][j] + matr[i] * matr[k + 1] * matr[j + 1];
					if (q < matrr[i][j])
						matrr[i][j] = q;
				}
			}
		}
		return matrr[0][N-1];
	}
	
	public static double logto(double x){
		return Math.log(x)/Math.log(2);
	}
	
	public static LinkedList<Long> degrees = new LinkedList<Long>();
	public static int res = 0;
	public static int i=0;
	public static int j=0;
	
	
	static void deg(long n){
		while (n > 0) {
			if (n % 2 == 1)
				degrees.addLast((long) res);
			n/=2;
			res++;
		}
	}
	
	public static void main(String args[]){
		
		
		LinkedList<Long> mas = new LinkedList<Long>();
		long N = 0;
		try{
			FileInputStream fstream = new FileInputStream(FILE_INPUT);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			strLine = br.readLine();
			N = Long.parseLong(strLine);
			if((N<1)||(N>Math.pow(10.0,18)))
			{
				appendUsingFileWriter(FILE_OUTPUT, -1);
				System.exit(0);
			}
			deg(N);
//			double x=0;
//			while(N != 0){
//				double t = Math.floor(logto(N));
//				N = N - (int)Math.pow(2, t);
//				mas.add(t);
//			}
			
			while(!degrees.isEmpty()){
				appendUsingFileWriter(FILE_OUTPUT, degrees.removeFirst());
			}
			
			
//			appendUsingFileWriter(FILE_OUTPUT, matrMult(mas,N));
		}catch (IOException e){
			//System.out.println("Ошибка");
		}

		

		System.exit(0);
	}
}
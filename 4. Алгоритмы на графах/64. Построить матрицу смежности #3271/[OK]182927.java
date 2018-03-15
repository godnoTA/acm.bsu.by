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
			fr.write(" ");
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
	
	public static void hash(int[] arr, int x, int c, int m){
		int i=0;
		
		for(;;){
			int n = ((x % m) + c*i) % m;
			if(arr[n] == -1){
				arr[n] = x;
				break;
			}
			else{ 
				if(arr[n] == x){
					break;
				}
				else
					i++;
			}
		}
	}
	
	public static void main(String args[]){
		
		try{
			FileInputStream fstream = new FileInputStream(FILE_INPUT);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			strLine = br.readLine();
			StringTokenizer strtok= new StringTokenizer(strLine, " ");
			int N = Integer.parseInt(strtok.nextToken());
			int M = Integer.parseInt(strtok.nextToken());
			int matr[][] = new int[N][N];
			while ((strLine = br.readLine()) != null){
				strtok= new StringTokenizer(strLine, " ");
				int i = Integer.parseInt(strtok.nextToken());
				int j = Integer.parseInt(strtok.nextToken());
				matr[i-1][j-1] = matr[j-1][i-1] = 1;
			}
			
			for(int i=0; i < N; i++){
				for(int j=0; j < N; j++){
					appendUsingFileWriter(FILE_OUTPUT, matr[i][j]);
				}
				appendUsingFileWriter(FILE_OUTPUT, "");
			}
//			appendUsingFileWriter(FILE_OUTPUT, matrMult(arr,N));
		}catch (IOException e){
			//System.out.println("Ошибка");
		}
		System.exit(0);
	}
}
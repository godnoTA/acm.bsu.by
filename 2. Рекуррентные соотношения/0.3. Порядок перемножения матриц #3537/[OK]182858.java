import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.math.*;
import java.text.*;

public class Rek {
	
	public static final String FILE_INPUT = "input.txt";
	public static final String FILE_OUTPUT = "output.txt";
	
	private static void appendUsingFileWriter(String filePath, int text) {
		File file = new File(filePath);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file,true);
			fr.write(Integer.toString(text));
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
	
	public static void main(String args[]){
		
		int countSeller = 0, countBuyer = 0, sum = 0;
		int[] coinSeller;
		int[] coinBuyer;
		
		int N = 0;
		try{
			FileInputStream fstream = new FileInputStream(FILE_INPUT);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			strLine = br.readLine();
			N = Integer.parseInt(strLine);
			int mas[] = new int[N+1];
			int count = 0;
			
			while ((strLine = br.readLine()) != null){
				int sp;
				StringTokenizer strMatr= new StringTokenizer(strLine, " ");
				mas[count] = Integer.parseInt(strMatr.nextToken());
				sp = Integer.parseInt(strMatr.nextToken());
				if(count == N-1){
					mas[N] = sp;
					break;
				}
				count++;
			}
			System.out.println(matrMult(mas,N));
			appendUsingFileWriter(FILE_OUTPUT, matrMult(mas,N));
		}catch (IOException e){
			//System.out.println("Ошибка");
		}

		

		System.exit(0);
	}
}
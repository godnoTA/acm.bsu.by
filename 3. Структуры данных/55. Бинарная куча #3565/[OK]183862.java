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
	
	public static LinkedList<Double> degrees = new LinkedList<Double>();
	public static int deg(double n)
	{	
		while(n>0)
		{
			boolean not=true;
			double del=1;
			double degree=0;
			while(true)
			{
				if(n-del>=0)
				{
					if(n-del==0)
					{
						degrees.addFirst(degree);
						return -1;
					}
					del*=2;
					degree++;
				}
				else if(n==1)				
					return 0;
				else if(n==2)
					return 1;
				else
				{
					degree--;
					degrees.addFirst(degree);
					n-=(del/2);
					break;
				}
			}
		}
		return 5;
	}
	
	public static void main(String args[]){
		
		
		int N = 0;
		try{
			FileInputStream fstream = new FileInputStream(FILE_INPUT);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			strLine = br.readLine();
			N = Integer.parseInt(strLine);
			if((N<1)||(N>100000))
				return;
			int arr[] = new int[N + 1];
			strLine = br.readLine();
			StringTokenizer strTok = new StringTokenizer(strLine," ");
			int i=1;
			while(i < N + 1){
				arr[i]=Integer.parseInt(strTok.nextToken());
				i++;
			}
			boolean flag=true;
			for(i = 1; i <= N; i++){
				if(2*i + 1 <= N){
					if((arr[i]>arr[2*i])||(arr[i]>arr[2*i+1])){
						flag=false;
						break;
					}
				}
				else{
					if(2 * i == N){
						if(arr[i]>arr[2*i]){
							flag=false;
							break;
						}
					}
				}
			}
//			for(i=1; i < N +1; i++){
//				if((2*i+1)<=N){
//					if (arr[i] > arr[2 * i] || arr[i] > arr[2 * i + 1])
//					{
//						flag=false;
//						break;
//					}
//					else {
//						if(2*i<=N){
//							if(arr[i]>arr[2*i]){
//								flag=false;
//								break;
//							}
//						}
//					}
//				}
//				
//			}
			if(flag == true){
				appendUsingFileWriter(FILE_OUTPUT, "Yes");
			}else{
				appendUsingFileWriter(FILE_OUTPUT, "No");
			}
//			appendUsingFileWriter(FILE_OUTPUT, matrMult(arr,N));
		}catch (IOException e){
			//System.out.println("Ошибка");
		}
		System.exit(0);
	}
}
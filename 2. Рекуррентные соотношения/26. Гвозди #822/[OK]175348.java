import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.math.*;
import java.text.*;

public class Rek {
	
	public static final String FILE_INPUT = "in.txt";
	public static final String FILE_OUTPUT = "out.txt";
	
	private static void appendUsingFileWriter(String filePath, double text) {
		File file = new File(filePath);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file,true);
			fr.write(Double.toString(text));
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
	
	public static void main(String args[]){
		
		int countSeller = 0, countBuyer = 0, sum = 0;
		int[] coinSeller;
		int[] coinBuyer;
		double[] mas;
		
		int N = 0;
		try{
			FileInputStream fstream = new FileInputStream(FILE_INPUT);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			strLine = br.readLine();
			N = Integer.parseInt(strLine);
			mas = new double[N];
			for(int i=0; i < N ; i++){
				strLine = br.readLine();
				mas[i] = Double.parseDouble(strLine);
			}
			
			if( N == 2){
				double x = mas[1] - mas[0];
				
				String t = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP).toString();
				System.out.println(t);
				
				
				appendUsingFileWriter(FILE_OUTPUT, t);
			}else{
				double[][] data = new double[N][N];
				
				for(int i = 0; i < N; i++ ){
					for(int j = 0; j < N; j++){
						data[i][j] = 0;
					}
				}
				
				for(int j=1, i=0; i < N - 1; i++, j++){
					data[i][j] = mas[j] - mas[i];
				}
				
				for (int i = 0, j = 2; i < N - 2; i++, j++)
					data[i][j] = data[i][j - 1] + data[i + 1][j];

				int T = 3;
				
				while( T != N){
					for (int i = 0, j = T; j < N; i++, j++)
						data[i][j] = Math.min(data[i + 1][j], data[i + 2][j]) + data[i][i+1];
					T++;
				}
				data[0][N-1] = new BigDecimal(data[0][N-1]).setScale(2, RoundingMode.HALF_UP).doubleValue();
				NumberFormat nf = NumberFormat.getInstance(); // get instance
				nf.setMaximumFractionDigits(2); // set decimal places
				nf.setMinimumFractionDigits(2);
				String str = nf.format(data[0][N-1]);
				appendUsingFileWriter(FILE_OUTPUT, str);
			}
//			StringTokenizer strtok = new StringTokenizer(strLine," ");
//			countBuyer = Integer.parseInt(strtok.nextToken());
//			coinBuyer = new int[countBuyer];
//			System.out.println(countBuyer);
//			countSeller = Integer.parseInt(strtok.nextToken());
//			coinSeller = new int[countSeller];
//			System.out.println(countSeller);
//			mas = new int[countSeller + countBuyer];
//			
//			strLine = br.readLine();
//			strtok = new StringTokenizer(strLine," ");
//			for(int i=0; i < countBuyer; i++){
//				coinBuyer[i] = mas[i] = Integer.parseInt(strtok.nextToken());
//				sum+= coinBuyer[i];
//				System.out.println(coinBuyer[i]);
//			}
//			
//			strLine = br.readLine();
//			strtok = new StringTokenizer(strLine," ");
//			for(int i=0; i < countSeller; i++){
//				coinSeller[i] = mas[i + countBuyer] = Integer.parseInt(strtok.nextToken());
//				System.out.println(coinSeller[i]);
//			}
//			
//			Arrays.sort(mas);
//			//System.out.println(mas[1]);
//			
//			int k=0;
//			int s = 0;
//			while(k < (countBuyer + countSeller) && mas[k] <= s+1){
//				s+=mas[k];
//				k++;
//			}
//			if(s >= sum){
//				appendUsingFileWriter(FILE_OUTPUT, "YES");
//			}
//			 else{
//				 appendUsingFileWriter(FILE_OUTPUT, "NO");
//				 appendUsingFileWriter(FILE_OUTPUT, sum -(s+1));
//			}
			 
			 
		}catch (IOException e){
			//System.out.println("Ошибка");
		}

		

		System.exit(0);
	}
}
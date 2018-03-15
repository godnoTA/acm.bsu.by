import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

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
	
	public static void main(String args[]){
		
		int countSeller = 0, countBuyer = 0, sum = 0;
		int[] coinSeller;
		int[] coinBuyer;
		int[] mas;
		try{
			FileInputStream fstream = new FileInputStream(FILE_INPUT);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			strLine = br.readLine();
			StringTokenizer strtok = new StringTokenizer(strLine," ");
			countBuyer = Integer.parseInt(strtok.nextToken());
			coinBuyer = new int[countBuyer];
			System.out.println(countBuyer);
			countSeller = Integer.parseInt(strtok.nextToken());
			coinSeller = new int[countSeller];
			System.out.println(countSeller);
			mas = new int[countSeller + countBuyer];
			
			strLine = br.readLine();
			strtok = new StringTokenizer(strLine," ");
			for(int i=0; i < countBuyer; i++){
				coinBuyer[i] = mas[i] = Integer.parseInt(strtok.nextToken());
				sum+= coinBuyer[i];
				System.out.println(coinBuyer[i]);
			}
			
			strLine = br.readLine();
			strtok = new StringTokenizer(strLine," ");
			for(int i=0; i < countSeller; i++){
				coinSeller[i] = mas[i + countBuyer] = Integer.parseInt(strtok.nextToken());
				System.out.println(coinSeller[i]);
			}
			
			Arrays.sort(mas);
			//System.out.println(mas[1]);
			
			int k=0;
			int s = 0;
			while(k < (countBuyer + countSeller) && mas[k] <= s+1){
				s+=mas[k];
				k++;
			}
			if(s >= sum){
				appendUsingFileWriter(FILE_OUTPUT, "YES");
			}
			 else{
				 appendUsingFileWriter(FILE_OUTPUT, "NO");
				 appendUsingFileWriter(FILE_OUTPUT, sum -(s+1));
			}
			 
			 
		}catch (IOException e){
			//System.out.println("Ошибка");
		}

		

		System.exit(0);
	}
}

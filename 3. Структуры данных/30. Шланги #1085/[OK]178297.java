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
			
			LinkedList<Double> data = new LinkedList<Double>();
			
			strLine = br.readLine();
			
			int count = Integer.parseInt(strLine);
			double x = 0, y = 0, hoseTop = 0;
			for(int i = 0; i < count; i++){
				strLine = br.readLine();
				StringTokenizer strtok = new StringTokenizer(strLine," ");
				x = Double.parseDouble(strtok.nextToken());
				y = Double.parseDouble(strtok.nextToken());
				hoseTop = Double.parseDouble(strtok.nextToken());
				if(data.isEmpty()){
					data.push(hoseTop);
				}
				else if (data.get(0) == hoseTop){
					data.pop();
				}
				else {
					data.push(hoseTop);
				}
			}
			
			if(data.isEmpty()){
				appendUsingFileWriter(FILE_OUTPUT, "Yes");
			}
			 else{
				 appendUsingFileWriter(FILE_OUTPUT, "No");
			}
			 
			 
		}catch (IOException e){
			//System.out.println("Ошибка");
		}

		

		System.exit(0);
	}
}

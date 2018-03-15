import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Rek {
	
	public static final String FILE_INPUT = "in.txt";
	public static final String FILE_OUTPUT = "out.txt";
	
	private static void appendUsingFileWriter(String filePath, int text, String delimeters) {
		File file = new File(filePath);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file,true);
			fr.write(Integer.toString(text));
			fr.write(delimeters);
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
			
			
			
			LinkedList<Integer> in = new LinkedList<Integer>();
			int N = 0, M = 0;
			
			strLine = br.readLine();
			StringTokenizer strtok = new StringTokenizer(strLine," ");
			N = Integer.parseInt(strtok.nextToken());
			M = Integer.parseInt(strtok.nextToken());
			int matr[][] = new int[N][N];
			int stack[] = new int[M+1];
			for(int i = 0; i < N; i++){
				strLine = br.readLine();
				strtok = new StringTokenizer(strLine," ");
				for(int j = 0; j < N; j++){
					matr[i][j] = Integer.parseInt(strtok.nextToken());
				}
			}
			
			strLine = br.readLine();
			strtok = new StringTokenizer(strLine," ");
			int data[] = new int[M+1];
			int k=0;
			while(strtok.hasMoreTokens()){
				data[k] = Integer.parseInt(strtok.nextToken());
				k++;
			}
			int first = 0, second = 0, val = 30, head = 0;
			stack[head]=data[0];
			for (int i=1;i<M;++i)
			{
				if (matr[stack[head]-1][data[i]-1]==0)
				{
					++head;
					stack[head]=data[i];
				}
				else
				{
					stack[head]=matr[stack[head]-1][data[i]-1];
					while ((head>0) && (matr[stack[head]-1][stack[head-1]-1]!=0))
					{
						stack[head-1]=matr[stack[head]-1][stack[head-1]-1];
						--head;
					}
				}
			}
//			
//			for(int i= 1; i < M; i++){
//				if(matr[stack[head] - 1][data[i] - 1] == 0){
//					head++;
//					stack[head]=data[i];
//				}
//				else{
//					stack[head] = matr[stack[head] - 1][data[i] - 1];
//					while((head > 0) && (matr[stack[head] - 1][stack[head - 1] - 1] != 0)){
//						stack[head - 1] = matr[stack[head] - 1][stack[head - 1] - 1];
//						head--;
//					}
//				}
//			}
			while(head > 0) {
				appendUsingFileWriter(FILE_OUTPUT, stack[head], " ");
				head--;
			}
			appendUsingFileWriter(FILE_OUTPUT, stack[head], "");
			 
			 
		}catch (IOException e){
			System.out.println("Ошибка");
		}

		

		System.exit(0);
	}
}

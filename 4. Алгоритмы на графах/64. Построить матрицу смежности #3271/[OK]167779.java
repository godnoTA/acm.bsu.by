import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class MyTree {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in=new Scanner(new File("input.txt"));
		
		int cont_of_node = in.nextInt();
		int count = in.nextInt()+1;
		
		int[][]matr=new int[cont_of_node][cont_of_node];
		
		for(int i = 0 ; i < cont_of_node ;i++)
			for(int j=0 ;j<cont_of_node; j++)
				matr[i][j]=0;
		
		while(in.hasNextInt()&& count !=0){
			int a=in.nextInt()-1;
			int b=in.nextInt()-1;
			matr[a][b]=1;
			matr[b][a]=1;
			count --;
		}
		
	in.close();
		
		File file1 = new File("output.txt"); 
		PrintWriter out = new PrintWriter(file1.getAbsoluteFile());  
		
		for(int i = 0 ; i < cont_of_node ;i++){
			for(int j=0 ;j<cont_of_node; j++)
				out.print( matr[i][j]+" ");
			out.print("\r\n");
		}
		out.close();
		
	}

}

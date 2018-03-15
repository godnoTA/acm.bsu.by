import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainniaM {
	public static void main(String[] args) throws IOException {
		 File file = new File("input.txt");
		 File file1 = new File("output.txt");
		 BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		 PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		 String s;
		 int N=Integer.parseInt(in.readLine());
		 int[][] mas=new int[N][N];
		 int j=0;
		 while ((s = in.readLine()) != null){
			 String[] str=s.split(" ");
			 for (int i=0;i<N;i++){
				 mas[j][i]=Integer.parseInt(str[i]);
			 }
			 j++;
        }
		 for (int i=0;i<N;i++){
			 for (int k=0;k<N;k++){
				 System.out.print(mas[i][k]+" ");
			 }
			 System.out.print("\n");
		 }
		 
		 int[]mas1=new int[N];
		 for (int i=0;i<N;i++){
			 for (int k=0;k<N;k++){
				 if(mas[k][i]==1){
					 mas1[i]=k+1;
				 }
			 }
		 }
		 for(int i=0;i<N;i++){
			 out.print(mas1[i]+" ");
		 }
		 in.close();
		 out.close();
	}
}

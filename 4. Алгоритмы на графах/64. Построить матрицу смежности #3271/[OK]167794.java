import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Eeeeeeeeee {
	public static void main(String[] args) throws IOException {
		 File file = new File("input.txt");
		 File file1 = new File("output.txt");
		 BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		 PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		 String s;
		 s=in.readLine();
		 String[] str1=s.split(" ");
		 int V=Integer.parseInt(str1[0]);
		 int R=Integer.parseInt(str1[1]);
		 int[][] matr=new int[V][V];
		 int x,y;
		 for(int i=0;i<V;i++){
			 for(int j=0;j<V;j++){
				 matr[i][j]=0;
			 }
		 }
		 while ((s = in.readLine()) != null){
			 String[] str=s.split(" ");
			 x=Integer.parseInt(str[0]);
			 y=Integer.parseInt(str[1]);
			 matr[x-1][y-1]=1;
			 matr[y-1][x-1]=1;
		 }
		 for(int i=0;i<V;i++){
			 for(int j=0;j<V;j++){
				 out.print(matr[i][j]+" ");
			 }
			 out.println();
		 }
		 in.close();
		 out.close();
		 }
}

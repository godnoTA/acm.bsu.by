import java.io.*;
import java.util.*;
public class test {

	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		
		Scanner in=new Scanner (new File("input.txt"));
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		
		int n=in.nextInt();
		int mas [] = new int [n+1];
		mas[0]=in.nextInt();
		for(int i=1; i<n; i++){
			mas[i]=in.nextInt();
			mas[i]=in.nextInt();
		}
		mas[n]=in.nextInt();
		in.close();
		
		int matr [][] = new int [n+1][n+1];
		for (int i=1; i<n+1;i++){
			matr[i][i]=0;
		}
		for(int i=2; i<n+1; i++){
			for(int j=1; j<n-i+2; j++){
				int p=i+j-1;
				matr[j][p]=Integer.MAX_VALUE;
				for(int m=j; m<p;m++){
					matr[j][p]=Math.min(matr[j][p],matr[j][m]+matr[m+1][p]+mas[j-1]*mas[m]*mas[p]);
					
				}
			}
		}
		writer.write(matr[1][n]+" ");
		writer.flush();
	}
}
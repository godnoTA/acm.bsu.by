import java.io.*;
import java.util.Scanner;

public class kanon {
	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		int n=in.nextInt();
		int m=in.nextInt();
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		int mas[][]=new int[n][n];
		for(int i=0; i<m; i++){
			int indexi=in.nextInt();
			int indexj=in.nextInt();
			mas[indexi-1][indexj-1]=1;
			mas[indexj-1][indexi-1]=1;
		}
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				writer.write(Integer.toString(mas[i][j])+" ");
			}
			writer.write("\r\n");
		}
		in.close();
		writer.flush();
	}
}
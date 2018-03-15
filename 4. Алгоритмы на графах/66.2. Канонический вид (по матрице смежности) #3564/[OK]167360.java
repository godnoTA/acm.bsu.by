import java.io.*;
import java.util.Scanner;

public class kanon {
	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		int n=in.nextInt();
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		int mas[][]=new int[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				mas[i][j]=in.nextInt();
			}
		}
		for(int j=0; j<n; j++){
			for(int i=0; i<n; i++){
				if(mas[i][j]==1){
					writer.write(Integer.toString(i+1)+" ");
					break;
				}
				if (i==n-1)
					writer.write(Integer.toString(0)+" ");
			}
		}
		in.close();
		writer.flush();
	}
}

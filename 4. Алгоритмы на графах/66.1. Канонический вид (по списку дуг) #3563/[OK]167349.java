import java.io.*;
import java.util.Scanner;

public class kanon {
	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		int n=in.nextInt();
		int mas[]=new int[n];
		while(in.hasNextInt()){
			int chislo=in.nextInt();
			int i=in.nextInt();
			mas[i-1]=chislo;
		}
		in.close();
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		for(int i=0; i<n; i++){
			writer.write(Integer.toString(mas[i])+" ");
		}
		writer.flush();
	}
}

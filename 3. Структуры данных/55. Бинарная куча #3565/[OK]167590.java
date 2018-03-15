import java.io.*;
import java.util.*;

public class kanon {

	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		int n=in.nextInt();
		int mas []=new int[n+1];
		for(int i=1; i<=n; i++){
			mas[i]=in.nextInt();
		}
		in.close();
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		for(int i=1; i<=n/2; i++){
			if(2*i+1<=n){
				if(mas[i]>mas[2*i] || mas[i]>mas[2*i+1]){
					writer.write("No");
					writer.flush();
					return;
					
				}
			}
			else{
				if(mas[i]>mas[2*i] ){
					writer.write("No");
					writer.flush();
					return;
					
				}
				
			}
		}
		writer.write("Yes");
		writer.flush();
	}
}
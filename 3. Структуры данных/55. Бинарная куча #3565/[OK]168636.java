import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Klassik {
	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		File file1 = new File("output.txt");
		BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		int N=Integer.parseInt(in.readLine());
		int[] mas=new int[N+1];
		mas[0]=0;
		String[] str=in.readLine().split(" ");
		for(int i=1;i-1<str.length;i++){
			mas[i]=Integer.parseInt(str[i-1]);
		}
		for(int i=0;i<mas.length;i++){
			System.out.print(mas[i]+" ");
		}
		System.out.println();
		for(int i=1;2*i<mas.length;i++){
			if(2*i+1>=mas.length){
				if(!(mas[i]<=mas[2*i])){
					out.println("No");
					in.close();
					out.close();
					return;
				}
				break;
			}
			if(!((mas[i]<=mas[2*i])&&(mas[i]<=mas[2*i+1]))){
				out.println("No");
				in.close();
				out.close();
				return;
			}
		}
		out.print("Yes");
		in.close();
		out.close();
}
}

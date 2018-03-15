import java.io.*;
import java.util.*;

public class kanon {
	 static class pair{
		ArrayList<Integer> mas=null;
		int key;
		pair(int key, ArrayList<Integer> mas){
			this.mas=mas;
		}
	}
	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		int n=in.nextInt();
		int m=in.nextInt();
		pair [] array = new pair[n];
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		for(int i=0; i<n;i++){
			array[i]=new pair(i+1,new ArrayList <Integer>());
		}
		for(int i=0; i<m; i++){
			int a=in.nextInt();
			int b=in.nextInt();
			array[a-1].mas.add(b);
			array[b-1].mas.add(a);
		}
		for(int i=0; i<n; i++){
			writer.write(Integer.toString(array[i].mas.size()));
			writer.write(" ");
			for(int j=0; j<array[i].mas.size();j++){
				writer.write(Integer.toString(array[i].mas.get(j))+" ");	
			}
			writer.write("\r\n");
		}
		in.close();
		writer.flush();
	}
}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ClasssS {
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
	 int x,y;
	 ArrayList[] arr = new ArrayList[V];
	 for(int i=0;i<V;i++){
		 arr[i]=new ArrayList<Integer>();
	 }
	 while ((s = in.readLine()) != null){
		 String[] str=s.split(" ");
		 x=Integer.parseInt(str[0]);
		 y=Integer.parseInt(str[1]);
		 arr[x-1].add(y);
		 arr[y-1].add(x);
		 }
	 for(int i=0;i<V;i++){
		 out.print(arr[i].size()+" ");
		 for(int j=0;j<arr[i].size();j++){
			 out.print(arr[i].get(j)+" ");
		 }
		 out.println();
	 }
	 in.close();
	 out.close();
	 }
}

	import java.awt.Point;
	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
public class Claaaaaasssssssssss {
		public static void main(String[] args) throws IOException {
			 File file = new File("input.txt");
			 File file1 = new File("output.txt");
			 BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
			 PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
			 String s;
			 ArrayList<Point> arr=new ArrayList<Point>();
			 int N=Integer.parseInt(in.readLine());
			 while ((s = in.readLine()) != null){
				 String[] str=s.split(" ");
				 arr.add(new Point(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
	         }
			 int[]mas=new int[N];
			 /*for(int i=0;i<N;i++){
				 mas[i]=0;
			 }*/
			 for(Point j:arr){
				 mas[j.y-1]=j.x;
			 }
			 /*for(int i=1;i<=N;i++){
				 mas[i-1]=0;
				 for(Point j:arr){
					 if(j.y==i){
						 mas[i-1]=j.x;
						 break;
					 }
				 }
			 }*/
			 for(int i=0;i<N;i++){
				 out.print(mas[i]+" ");
			 }
			 in.close();
			 out.close();
		}
}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Eeeee_boy {
	public static void main(String[] args) throws IOException {
		 File file = new File("input.txt");
		 File file1 = new File("output.txt");
		 BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		 PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		 long N=Long.parseLong(in.readLine());
		 int k=-1;
		 ArrayList<Integer> arr=new ArrayList<Integer>();
		 do{
			 k++; 
		 }
		 while((long)Math.pow(2, k+1)<=N);
		 
		 while(true){
			 if(N-(long)Math.pow(2, k)>0){
				 N=(N-(long)Math.pow(2, k));
				 arr.add(k);
			 }
			 else if(N-(long)Math.pow(2, k)==0){
				 arr.add(k);
				 break;
			 }
			 else if(N-(long)Math.pow(2, k)<0){
				 k--;
				 if(k<0){
					 out.print("-1");
					 in.close();
					 out.close();
					 return;
				 }
			 }
		 }
		 arr.sort(null);
		 for(int i=0;i<arr.size();i++){
			 out.print(arr.get(i)+" ");
		 }
		 long otv=0;
		 for(int i=0;i<arr.size();i++){
			otv+=(long)Math.pow(2, arr.get(i));
		 }
		 System.out.println(otv);
		 in.close();
		 out.close();
	}
}

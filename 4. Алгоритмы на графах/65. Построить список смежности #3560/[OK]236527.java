import java.io.*;
import java.util.*;

public class Matr {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(new FileReader("input.txt"))){		

	 int n=sc.nextInt();
	 int m=sc.nextInt();
	 int u,v;
	 ArrayList[] arr = new ArrayList[n];
	 for(int i=0;i<n;i++){
		 arr[i]=new ArrayList<Integer>();
	 }
	 while ((sc.hasNext())){
		 u=sc.nextInt();
		 v=sc.nextInt();
		 arr[u-1].add(v);
		 arr[v-1].add(u);
		 }
		PrintWriter out = new PrintWriter(new File("output.txt")); 
	 for(int i=0;i<n;i++){
		 int rt = arr[i].size();
		 out.print(rt+" ");
		 for(int j=0;j<arr[i].size();j++){
			 out.print(arr[i].get(j)+" ");
		 }
		 out.println();
	 }
	 
	 sc.close();
	 out.close();
	 }
		catch(Exception e){
        }
	}
}
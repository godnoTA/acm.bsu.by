import java.io.*;
import java.util.*;

public class Matr {
	public static void main(String[] args){
		try(Scanner sc = new Scanner(new FileReader("input.txt"))){	
		 int n=sc.nextInt();;
		 int[][] mas=new int[n][n];
		 int[]mus=new int[n];
		 
		for (int j=0;sc.hasNextInt();j++){
			 for (int i=0;i<n;i++){
				 mas[j][i]=sc.nextInt();
			 }
        }
		 for (int i=0;i<n;i++){
			 for (int x=0;x<n;x++){
				 if(mas[x][i]==1){
					 mus[i]=x+1;
				 }
			 }
		 }
		 PrintWriter out = new PrintWriter(new File("output.txt"));
		 for(int i=0;i<n;i++){
			 out.print(mus[i]+" ");
		 }
		 sc.close();
		 out.close();
	}
		catch(Exception e){
        }
	}
}
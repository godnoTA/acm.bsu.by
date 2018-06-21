import java.io.*;
import java.util.*;

public class Matr {
	public static void main(String[] args) {

		try(Scanner sc = new Scanner(new FileReader("input.txt"))){		

			int n =sc.nextInt();
			int m =sc.nextInt();
			int[][] matr = new int[n][n];
			for(int i=0; i<m; i++){
				int u = sc.nextInt();
				int v = sc.nextInt();
				matr[u-1][v-1]=1;
				matr[v-1][u-1]=1;
			}
			PrintWriter out = new PrintWriter(new File("output.txt")); 
			for(int i=0; i<n;i++){
				for(int j=0; j<n; j++){
					out.print(matr[i][j]);
					out.print(" ");
				}	
				out.println("");
			}
 			sc.close();
			out.close();
		} catch(Exception e){
        }
	}

}
import java.io.*;
import java.util.*;
public class Matr {

	public static void main(String[] args){
		try(Scanner sc = new Scanner(new FileReader("input.txt"))){		
		int s=sc.nextInt();
		int mas [] = new int [s+1];
		int matr [][] = new int [s+1][s+1];
		for (int i=1; i<s+1;i++){
			matr[i][i]=0;
		}
		mas[0]=sc.nextInt();
		for(int i=1; i<s; i++){
			mas[i]=sc.nextInt();
			mas[i+1]=sc.nextInt();
		
		}
		mas[s]=sc.nextInt();
		sc.close();
		PrintWriter out = new PrintWriter(new File("output.txt"));
		for(int i=2; i<s+1; i++){
			for(int l=1; l<s-i+2; l++){
				int resh=i+l-1;
				matr[l][resh]=Integer.MAX_VALUE;
				for(int m=l; m<resh;m++){
					matr[l][resh]=Math.min(matr[l][resh],matr[l][m]+matr[m+1][resh]+mas[l-1]*mas[m]*mas[resh]);
					
				}
			}
		}
		out.print(matr[1][s]);
		out.close();
		}
		catch(Exception e){
        }	
	}	
}
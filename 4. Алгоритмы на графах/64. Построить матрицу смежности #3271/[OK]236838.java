import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws IOException {
    	File f = new File("input.txt");
        Scanner sc = new Scanner(f);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int i,j;
        int[][] matr = new int[n][n];
        while (sc.hasNext()) {
            i = sc.nextInt();
            j = sc.nextInt();
            matr[i - 1][j - 1] = 1;
            matr[j - 1][i - 1] = 1;
        }
        sc.close();
        PrintWriter q = new PrintWriter(new File("output.txt"));
        for(int ii=0;ii<n;ii++)
        {
        	for(int jj=0;jj<n-1;jj++)
        		q.print(matr[ii][jj] + " ");
        	q.print(matr[ii][n-1] + "\n");
        }
        q.flush();
    }
}
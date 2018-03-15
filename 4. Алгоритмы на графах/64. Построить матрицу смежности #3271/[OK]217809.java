import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdjacencyMatrix {
    public static void main(String[] arg) throws IOException {
        try{
            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter("output.txt");

            int n = in.nextInt();
            int m = in.nextInt();
            int[][] matr = new int[n][n];
            while (in.hasNext()){
                int a=in.nextInt(), b=in.nextInt();
                matr[a-1][b-1]=1;
                matr[b-1][a-1]=1;
            }
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++) {
                    out.print(matr[i][j]+" ");
                }
                out.println();
            }

            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

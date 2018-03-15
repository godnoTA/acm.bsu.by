import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdjacencyMatrix {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        int N = in.nextInt();
        int matrix[][] = new int[N][N];
        int M = in.nextInt();
        int index = 0;
        while(index<M)
        {
            int i = in.nextInt()-1;
            int j = in.nextInt()-1;
            matrix[i][j] = matrix[j][i] = 1;
            index++;
        }
        for(int i = 0; i<N; i++)
        {
            for(int j = 0; j<N; j++)
            {
                pw.print(matrix[i][j]+" ");
            }
            pw.println();
        }
        pw.close();
    }
}

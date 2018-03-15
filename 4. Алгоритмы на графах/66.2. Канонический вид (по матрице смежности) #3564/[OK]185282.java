import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CanonM {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        int N = in.nextInt();
        int[] parent = new int[N];
        int[][] matrix = new int[N][N];
        for(int i = 0; i < N; i++)
        {
            for (int j = 0; j<N; j++)
            {
                matrix[i][j] = in.nextInt();
            }
        }
        for(int i = 0; i < N; i++)
        {
            for (int j = 0; j<N; j++)
            {
                if(matrix[i][j] == 1)
                {
                    parent[j] = i + 1;
                }
            }
        }
        for(int val: parent)
            pw.print(val+" ");
        pw.close();
    }
}

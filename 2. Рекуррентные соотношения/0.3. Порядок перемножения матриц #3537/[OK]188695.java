import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static int[][] matrixSize;
    public static int[][] matrix;
    public static int N = 0;
    public static void counting(){
        for (int m = 1; m < N; m++) {
            int i = 0;
            for (int j = m; j < N; j++) {
                int currentSum;
                Integer minSum = null;
                for (int k = i; k < j; k++) {
                    currentSum = matrix[i][k]  + matrix[k+1][j] + matrixSize[i][0]*matrixSize[k][1]*matrixSize[j][1];
                    if (minSum == null)
                        minSum = currentSum;
                    else if (currentSum < minSum)
                        minSum = currentSum;
                }
                matrix[i][j] = minSum;
                i++;
            }
        }
    }

    public static void main(String [] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
         N = Integer.parseInt(reader.readLine());
        matrixSize = new int [N][2];
        matrix = new int [N][N];
        for (int i = 0; i < N; i++) {
            String [] my = reader.readLine().split(" ");
            matrix [i][i] = 0;
            matrixSize[i][0] = Integer.parseInt(my[0]);
            matrixSize[i][1] = Integer.parseInt(my[1]);
        }
        reader.close();
        counting();
        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());
        out.print(matrix[0][N-1]);

        out.close();
    }
}


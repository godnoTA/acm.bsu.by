import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Евгения on 23.05.2017.
 */
public class DFS {
    static int c = 0;

    public static void searching(int[] temp, int[][] matrix, int k, int n)
    {
        temp[k] = ++c;
        for (int i = 0; i < n; i++) {
            if (matrix[k][i] == 1 && temp[i] == 0) {
                searching(temp, matrix, i, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

        int n = Integer.parseInt(reader.readLine());
        int [][] matrix = new int[n][n];
        int [] temp = new int[n];

        String s[];
        for (int i = 0; i < n; i++){
            s = reader.readLine().split(" ");
            for (int j = 1; j < n; j++) {
                matrix[i][j] = Integer.parseInt(s[j]);
            }
        }
        reader.close();

        for (int k = 0; k < n; k++) {
            if (temp[k] == 0) {
                searching(temp, matrix, k, n);
            }
        }

        for (int i = 0; i < n; i++) {
            writer.print(temp[i] + " ");
        }
        writer.println();
        writer.close();
    }
}

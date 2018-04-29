import java.io.*;
import java.util.Scanner;

public class Main {

    static int[][] matrix;
    static int n;
    static int m;

    private static void readFromFile() {
        File input = new File("input.txt");
        try {
            Scanner sc = new Scanner(new FileReader(input));
            n = sc.nextInt();
            m = sc.nextInt();
            matrix = new int[n][n];
            int u, v;
            for (int i = 0; i < m; i++) {
                u = sc.nextInt() - 1;
                v = sc.nextInt() - 1;
                matrix[u][v] = 1;
                matrix[v][u] = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult() {
        try {
            File output = new File("output.txt");
            if (output.exists()) {
                output.delete();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(output, true), true);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    pw.print(matrix[i][j] + " ");
                }
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFromFile();
        printResult();
    }
}

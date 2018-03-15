import java.util.Scanner;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        int i;
        int j;
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        if ((n >= 1 || n <= 100) && (m >= 0 || m <= (n * (n - 1) / 2))) {

            int val[][] = new int [n][n];
            for (i = 0; i < m; ++i) {
                int v = scanner.nextInt();
                int u = scanner.nextInt();
                val [u - 1][v - 1] = 1;
                val [v - 1][u - 1] = 1;
            }


            FileWriter fout = null;
            try {
                fout = new FileWriter("output.txt");
                for (j = 0; j < n; j++) {
                    for (i = 0; i < n; i++)
                        fout.write(val[i][j] + " ");
                        fout.write('\n');

                }

                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
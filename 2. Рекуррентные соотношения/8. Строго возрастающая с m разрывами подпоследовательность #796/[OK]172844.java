//package t8;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class T8 {

    public static long INFINITY = 1000000001;

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("input.txt"));
        int n = s.nextInt();
        int m = s.nextInt();
        long[][] mas = new long[m + 1][n];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n; j++) {
                mas[i][j] = INFINITY;
            }
        }
        for (int k = 0; k < n; k++) {
            long a = s.nextLong();
            for (int i = m; i >= 0; i--) {
                int e = Arrays.binarySearch(mas[i], a);
                if (e < 0) {
                    e = -(e + 1);
                }
                mas[i][e] = a;
                if (i > 0) {
                    int p = e + 1;
                    while (mas[i - 1][p - 1] != INFINITY) {
                        mas[i][p] = a;
                        p++;
                    }
                }
            }
        }
        s.close();
        int j;
        for (j = n - 1; j >= 0; j--) {
            if (mas[m][j] != INFINITY) {
                break;
            }
        }
        PrintWriter out = new PrintWriter("output.txt");
        out.print(j + 1);
        out.close();
    }

}

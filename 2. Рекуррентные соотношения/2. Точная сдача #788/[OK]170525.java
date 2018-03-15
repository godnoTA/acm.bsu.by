//package t2;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class T2 {

    public static int n, m, S, s1, s2;
    public static int[] p;
    public static boolean mas[][];

    public static int jIndex(int j) {
        return j + s2;
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("input.txt"));
        n = s.nextInt();
        m = s.nextInt();
        S = s.nextInt();
        p = new int[n + m];

        s1 = 0;
        for (int i = 0; i < n; i++) {
            s1 += p[i] = s.nextInt();
        }

        s2 = 0;
        for (int i = n; i < n + m; i++) {
            s2 -= p[i] = -s.nextInt();
        }
        s.close();

        PrintWriter out = new PrintWriter(new File("output.txt"));
        if (S > s1) {
            out.print("No");
            out.close();
            System.exit(0);
        }
        mas = new boolean[n + m + 1][s1 + s2 + 1];

        mas[n + m][jIndex(S)] = true;
        for (int i = n + m - 1; i >= 0; i--) {
            for (int j = -s2; j <= s1; j++) {
                mas[i][jIndex(j)] = (mas[i + 1][jIndex(j)])
                        || ((j + p[i] >= -s2) && (j + p[i] <= s1) && (mas[i + 1][jIndex(j + p[i])]));
            }
        }
        if (mas[0][jIndex(0)]) {
            out.print("Yes");
        } else {
            out.print("No");
        }
        out.close();
    }

}

//package com.company;

import java.io.*;

public class Main {

    static int n = 0, m = 0;
    static int[] a = null;
    static int[][] c = null, f = null;

    public static void main(String[] args) {

        try {
            StreamTokenizer st = new StreamTokenizer(
                    new BufferedReader(new FileReader("in.txt")));
            if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                n = (int) st.nval;
            }
            if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                m = (int) st.nval;
            }
            a = new int[n];
            c = new int[n][n];
            f = new int[n][m];
            for (int i = 0; i < n; i++) {
                if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                    a[i] = (int) st.nval;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < n - 1; i++) {
            c[i][i + 1] = a[i + 1] - a[i];
        }

        for (int i = 0; i < n - 2; i++) {
            int index = i;
            for (int j = i + 2; j < n; j++) {
                if ((j - i) % 2 == 0) {
                    index++;
                    c[i][j] = c[i][j - 1] + a[j] - a[index];
                } else {
                    c[i][j] = c[i][j - 1] + a[j] - a[index];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            f[i][0] = c[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (i >= j) {
                    f[i][j] = f[j-1][j - 1] + c[j][i];
                    for (int k = j; k < i; k++) {
                        int v = f[k][j - 1] + c[k + 1][i];
                        if (v < f[i][j]) {
                            f[i][j] = v;
                        }
                    }
                }
            }
        }

        try {
            File file = new File("out.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("out.txt");
            out.println(f[n - 1][m - 1]);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int a[] = null, b[] = null;
    static int[] c = null;

    static int f(int val) {
        int left = 0;
        int right = b.length;
        while (left != right) {
            int aver = (int) ((left + right) / 2);
            if (c[aver] <= val) {
                left = aver + 1;
            } else {
                right = aver;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int n = 0, m = 0;

        try {
            StreamTokenizer st = new StreamTokenizer(
                    new BufferedReader(new FileReader("input.txt")));
            if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                n = (int) st.nval;
            }
            if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                m = (int) st.nval;
            }

            a = new int[n];
            b = new int[m];
            c = new int[m + 1];
            for (int i = 1; i <= m; i++) {
                c[i] = 2000000001;
            }
            c[0] = -1;
            HashMap<Integer, Integer> map = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                    int val = (int) st.nval;
                    map.put(val, i);
                }
            }
            for (int i = 0; i < m; i++) {
                if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                    int val = (int) st.nval;
                    Integer index = map.remove(val);
                    if (index != null) {
                        b[i] = index;
                    } else {
                        b[i] = -1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int max = 0;
        if (b != null) {
            for (int i = 0; i < m; i++) {
                if (b[i] >= 0) {
                    int index = f(b[i]);
                    if (b[i] < c[index] && b[i] > c[index - 1]) {
                        c[index] = b[i];
                        if (index > max) max = index;
                    }
                }
            }
        }

        try {
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            out.println(max);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

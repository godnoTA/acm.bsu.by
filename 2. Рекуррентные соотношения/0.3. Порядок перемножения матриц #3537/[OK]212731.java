//package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        int n = 0;
        int l[][] = null, data[][] = null;
        try {
            StreamTokenizer st = new StreamTokenizer(
                    new BufferedReader(new FileReader("input.txt")));
            if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                n = (int) st.nval;
            }
            l = new int[n][n];
            data = new int[n][2];
            for (int i = 0; i<n; i++){
                if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                    data[i][0] = (int) st.nval;
                }
                if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                    data[i][1] = (int) st.nval;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (l!=null && data!=null) {
            for (int j = 1; j < n; j++) {
                for (int i = 0; i < n - j; i++) {
                    int min = l[i][i] + l[i + 1][i + j] + data[i][0] * data[i][1] * data[i + j][1];
                    for (int k = i; k < i + j; k++) {
                        int val = l[i][k] + l[k + 1][i + j] + data[i][0] * data[k][1] * data[i + j][1];
                        if (val < min) {
                            min = val;
                        }
                    }
                    l[i][i+j] = min;
                }
            }
        }
        //System.out.println(l[0][n-1]);
        try {
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            out.println(l[0][n-1]);
            out.flush();
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

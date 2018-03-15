//package com.company;

import java.io.*;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        int[] a = null;
        int n = 0;
        try {
            StreamTokenizer st = new StreamTokenizer(
                    new BufferedReader(new FileReader("input.txt")));
            if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                n = (int) st.nval;
            }

            a = new int[n+1];
            for (int i = 1; i <= n; i++) {
                if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                    a[i] = (int) st.nval;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isHeap = true;
        if (a!= null) {
            for (int i = 1; i <= n; i++) {
                if (2 * i <= n) {
                    if (a[i] > a[2 * i]) {
                        isHeap = false;
                        break;
                    }
                }
                if (2*i +1 <=n){
                    if(a[i] > a[2 * i + 1]){
                        isHeap = false;
                        break;
                    }
                } else break;
            }
        } else {
            isHeap = false;
        }

        try {
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            if (isHeap) {
                out.println("Yes");
            } else {
                out.println("No");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

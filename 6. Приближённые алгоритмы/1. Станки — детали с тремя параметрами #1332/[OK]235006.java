//package com.company;

import javafx.util.Pair;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    throw new EOFException();
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        try {
            FastScanner fs = new FastScanner("input.txt");

            int n = fs.nextInt();
            int m = fs.nextInt();
            long[] t1 = new long[n];
            long[] t2 = new long[n];
            long[] t3 = new long[n];
            Pair<Long, Integer>[] t = new Pair[n];

            Pair<Integer, Integer>[] ans = new Pair[n];
            long[] time = new long[m];
            long[] fTime = new long[m];
            for (int i = 0; i < n; i++) {
                t1[i] = fs.nextInt();
                t2[i] = fs.nextInt();
                t3[i] = fs.nextInt();
                t[i] = new Pair<>(t1[i] + t2[i] - t3[i], i);
            }

            Arrays.sort(t, Comparator.comparing(Pair::getKey));

            int c = 0;
            int v = 1;
            while (c < n) {
                while (v <= m && c < n) {
                    ans[c] = new Pair<>(t[c].getValue() + 1, v);
                    time[v - 1] = Math.max(time[v - 1] + t2[t[c].getValue()], t1[t[c].getValue()] + t2[t[c].getValue()]);
                    fTime[v - 1] = Math.max(fTime[v - 1], time[v - 1] + t3[t[c].getValue()]);
                    c++;
                    v++;
                }
                v--;
                while (v > 0 && c < n) {
                    ans[c] = new Pair<>(t[c].getValue() + 1, v);
                    time[v - 1] = Math.max(time[v - 1] + t2[t[c].getValue()], t1[t[c].getValue()] + t2[t[c].getValue()]);
                    fTime[v - 1] = Math.max(fTime[v - 1], time[v - 1] + t3[t[c].getValue()]);
                    c++;
                    v--;
                }
                v++;
            }

            /*for (int i = 0; i < n; i++) {
                System.out.println(ans[i]);
            }*/

            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            long max = 0;
            for (int i = 0; i<m; i++){
                if(fTime[i]>max){
                    max = fTime[i];
                }
            }
            out.print(max);
            for (int i = 0; i<n; i++){
                out.println();
                out.print(ans[i].getKey());
                out.print(' ');
                out.print(ans[i].getValue());
            }
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }
}

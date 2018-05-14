//package com.company;

import java.io.*;
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
        int n = 0,m=0;
        int[][] matrix = null;
        try {
            FastScanner fs = new FastScanner("input.txt");
            n = fs.nextInt();
            m = fs.nextInt();
            matrix = new int[n][n];
            for (int i = 0; i< m; i++) {
                int l = fs.nextInt();
                int k = fs.nextInt();
                matrix[l-1][k-1] = matrix[k-1][l-1] = 1;
            }
        } catch (IOException e) {
        }

        try {
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            for (int i = 0;i<n;i++){
                for(int j = 0;j<n;j++){
                    out.print(matrix[i][j]);
                    out.print(' ');
                }
                out.println();
            }
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }
}

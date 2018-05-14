//package com.company;

import java.io.*;
import java.util.ArrayDeque;
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

    static int n;
    static int[][] matrix;
    static int[] marks;
    static int count;

    static void dfs(Integer node) {
        for (int i = 0; i < n; i++) {
            if (matrix[node][i] == 1 && marks[i] == 0) {
                count++;
                marks[i] = count;
                dfs(i);
            }
        }
    }

    public static void main(String[] args) {

        try {
            FastScanner fs = new FastScanner("input.txt");
            n = fs.nextInt();
            marks = new int[n];
            matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = fs.nextInt();
                }
            }

            count = 0;
            for (int i = 0; i < n; i++) {
                if (marks[i] == 0) {
                    count++;
                    marks[i] = count;
                    dfs(i);
                }
            }
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            for (int i = 0; i < n; i++) {
                out.print(marks[i]);
                out.print(' ');
            }
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }
}


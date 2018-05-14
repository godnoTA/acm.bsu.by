//package com.company;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

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
        int n;
        int[] array;
        try {
            FastScanner fs = new FastScanner("input.txt");
            n = fs.nextInt();
            array = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j<n; j++) {
                    int l = fs.nextInt();
                    if (l==1){
                        array[j] = i+1;
                    }
                }
            }

            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            for (int i = 0; i < n; i++) {
                out.print(array[i]);
                out.print(' ');
            }
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }
}


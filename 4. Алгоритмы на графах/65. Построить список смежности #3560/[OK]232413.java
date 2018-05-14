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
        int n = 0,m=0;
        Vector<Integer>[] lists = null;
        try {
            FastScanner fs = new FastScanner("input.txt");
            n = fs.nextInt();
            m = fs.nextInt();
            lists = new Vector[n];
            for (int i = 0; i< n; i++){
                lists[i] = new Vector<>();
            }
            for (int i = 0; i< m; i++) {
                int l = fs.nextInt();
                int k = fs.nextInt();
                lists[l-1].add(k);
                lists[k-1].add(l);
            }
        } catch (IOException e) {
        }

        try {
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            for (int i = 0;i<n;i++){
                out.print(lists[i].size());
                out.print(' ');
                for(Integer j : lists[i]){
                    out.print(j);
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


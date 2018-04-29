//package com.company;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

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

    static long nod(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return nod(b, a % b);
        }
    }

    static long nok(int a, int b) {
        return a / nod(a, b) * b;
    }

    static class MyComparator implements Comparator<Pair<Long, Integer>> {

        @Override
        public int compare(Pair<Long, Integer> o1, Pair<Long, Integer> o2) {
            return Long.compare(o1.getKey(),o2.getKey());
        }
    }

    public static void main(String[] args) {
        long[] devices = null;
        //int[][] rooms = null;
        Vector<ArrayList<Pair<Integer, Vector<Integer>>>> vector = null;
        int[] visited = null;
        int[] way = null;
        long[] length = null;
        PriorityQueue<Pair<Long, Integer>> queue = new PriorityQueue<>(new MyComparator());
        int n = 0, m;
        try {
            FastScanner fs = new FastScanner("input.txt");
            n = fs.nextInt();
            m = fs.nextInt();
            //rooms = new int[n][n];
            vector = new Vector<>(n);
            for (int i = 0; i < n; i++) {
                vector.add(new ArrayList<>());
            }
            devices = new long[m];
            visited = new int[n];
            way = new int[n];
            length = new long[n];
            for (int k = 0; k < n; k++) {
                length[k] = Long.MAX_VALUE;
            }
            for (int k = 0; k < m; k++) {
                int i = fs.nextInt();
                int a = fs.nextInt();
                int j = fs.nextInt();
                int b = fs.nextInt();
                /*rooms[i - 1][j - 1] = *//*rooms[j - 1][i - 1] = *//* k + 1;
                devices[k] = nok(a, b);*/
                Pair<Integer, Vector<Integer>> ind = null;
                for (Pair<Integer, Vector<Integer>> p : vector.get(i - 1)) {
                    if (j - 1 == p.getKey()) {
                        ind = p;
                        break;
                    }
                }
                if (ind == null) {
                    Vector<Integer> v = new Vector<>();
                    v.add(k + 1);
                    vector.elementAt(i - 1).add(new Pair<>(j - 1, v));
                    vector.elementAt(j - 1).add(new Pair<>(i - 1, v));
                } else {
                    ind.getValue().add(k + 1);
                }
                devices[k] = nok(a, b);
            }
        } catch (IOException e) {
        }
        int []w = new int[n];
        queue.add(new Pair<>(0L, 0));
        way[0] = -1;
        length[0] = 0;
        while (!queue.isEmpty()) {
            int x = queue.peek().getValue();
            long l = queue.poll().getKey();
            visited[x] = 1;
            if (l <= length[x]) {
                for (Pair<Integer, Vector<Integer>> p : vector.get(x)) {
                    if (visited[p.getKey()] == 0) {
                        long dist = Long.MAX_VALUE;
                        int dev = 0;
                        for (Integer v : p.getValue()) {
                            long d;
                            if (l % devices[v - 1] == 0 && l != 0) {
                                d = l + 1;
                            } else {
                                d = l + 1 + devices[v - 1] - l % devices[v - 1];
                            }
                            if (d < dist) {
                                dist = d;
                                dev = v;
                            }
                        }

                        if (length[p.getKey()] > dist) {
                            length[p.getKey()] = dist;
                            way[p.getKey()] = x;
                            w[p.getKey()] = dev;
                            queue.add(new Pair<>(dist, p.getKey()));
                        }
                    }
                }
            }
        }


        //System.out.println(length[n-1] - 0.5);

        int count = 0;
        int ind = n - 1;
        int[] devWay = new int[n];
        while (ind != 0) {
            devWay[count] = w[ind];
            ind = way[ind];
            count++;
        }
        /*for (int i=0;i<n;i++){
            System.out.println(length[i]);
        }*/
        try {
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            out.println(length[n - 1] - 0.5);
            for (int i = count - 1; i >= 0; i--) {
                out.print(devWay[i]);
                out.print(' ');
            }
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }
}

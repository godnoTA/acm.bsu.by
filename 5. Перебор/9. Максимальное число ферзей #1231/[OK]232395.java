//package com.company;

import javafx.util.Pair;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main implements Runnable {

    @Override
    public void run() {
        int k;
        PriorityQueue<Integer>[] lines = null, columns = null, diagonals = null, diagonalsRev = null;
        try {
            FastScanner fs = new FastScanner("input.txt");
            n = fs.nextInt();
            m = fs.nextInt();
            matrix = new int[n][m];
            k = fs.nextInt();
            lines = new PriorityQueue[n];
            for (int i = 0; i < n; i++) {
                PriorityQueue<Integer> q = new PriorityQueue<>();
                q.add(-1);
                q.add(m);
                lines[i] = q;
            }
            columns = new PriorityQueue[m];
            for (int i = 0; i < m; i++) {
                PriorityQueue<Integer> q = new PriorityQueue<>();
                q.add(-1);
                q.add(n);
                columns[i] = q;
            }
            diagonals = new PriorityQueue[n + m - 1];
            for (int i = 0; i < n + m - 1; i++) {
                PriorityQueue<Integer> q = new PriorityQueue<>();
                q.add(-1);
                q.add(n);
                diagonals[i] = q;
            }
            diagonalsRev = new PriorityQueue[n + m - 1];
            for (int i = 0; i < n + m - 1; i++) {
                PriorityQueue<Integer> q = new PriorityQueue<>();
                q.add(-1);
                q.add(n);
                diagonalsRev[i] = q;
            }

            for (int l = 0; l < k; l++) {
                int i = fs.nextInt();
                int j = fs.nextInt();
                matrix[i][j] = 2;
                lines[i].add(j);
                columns[j].add(i);
                diagonals[n - 1 - i + j].add(i);
                diagonalsRev[i + j].add(i);
            }

            fs.reader.close();
        } catch (Exception e) {
        }
        //System.out.println(function(matrix,new PriorityQueue<>()));

        vectors = new Vector<>();
        vectors.add(new Vector<>());

        try {
            Vector<Pair<Integer, Integer>> vector = new Vector<>();
            for (int i = 0; i < n; i++) {
                int subLinesNum = 0;
                for (int h = i; h < n; h++) {
                    subLinesNum += lines[h].size();
                }
                if (subLinesNum - n + i < vectors.get(0).size() - vector.size()) {
                    //System.out.println(i);
                    break;
                }
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] != 2) {
                        function(vector, i, j, lines, columns, diagonals, diagonalsRev);
                    }
                }
            }
        } catch (Exception e) {
        }

        //System.out.println(vectors.get(0));
        //System.out.println(vectors.size());
        try {
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            out.println(vectors.get(0).size());
            if (vectors.get(0).size() != 0) {
                out.println(vectors.size());
                for (Vector<Pair<Integer, Integer>> v : vectors) {
                    for (Pair<Integer, Integer> i : v){
                        out.print(i.getKey());
                        out.print(' ');
                        out.print(i.getValue());
                        out.print(' ');
                    }
                    out.println();
                }
            } else {
                out.println(0);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }

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

    static int n = 0, m = 0;
    //static PriorityQueue<Integer>[] lines = null, columns = null, diagonals = null, diagonalsRev = null;
    static int[][] matrix = null;
    static Vector<Vector<Pair<Integer, Integer>>> vectors = null;

    static void function(Vector<Pair<Integer, Integer>> vector1, int i, int j, PriorityQueue<Integer>[] lines1, PriorityQueue<Integer>[] columns1, PriorityQueue<Integer>[] diagonals1, PriorityQueue<Integer>[] diagonalsRev1) {

        PriorityQueue<Integer>[] lines = new PriorityQueue[n];
        for (int k = 0; k < n; k++) {
            lines[k] = new PriorityQueue<Integer>(lines1[k]);
        }
        PriorityQueue<Integer>[] columns = new PriorityQueue[m];
        for (int k = 0; k < m; k++) {
            columns[k] = new PriorityQueue<Integer>(columns1[k]);
        }
        PriorityQueue<Integer>[] diagonals = new PriorityQueue[n + m - 1];
        for (int k = 0; k < n + m - 1; k++) {
            diagonals[k] = new PriorityQueue<Integer>(diagonals1[k]);
        }
        PriorityQueue<Integer>[] diagonalsRev = new PriorityQueue[n + m - 1];
        for (int k = 0; k < n + m - 1; k++) {
            diagonalsRev[k] = new PriorityQueue<Integer>(diagonalsRev1[k]);
        }

        while (j > lines[i].peek()) {
            lines[i].poll();
        }
        while (i > columns[j].peek()) {
            columns[j].poll();
        }
        while (i > diagonals[n - 1 - i + j].peek()) {
            diagonals[n - 1 - i + j].poll();
        }
        while (i > diagonalsRev[i + j].peek()) {
            diagonalsRev[i + j].poll();
        }

        Vector<Pair<Integer, Integer>> vector = new Vector<>(vector1);
        vector.add(new Pair<>(i, j));

        if (lines[i].peek() < m - 1) {
            for (int k = lines[i].peek() + 1; k < m; k++) {
                if (matrix[i][k] != 2) {
                    if (k > lines[i].peek() && i > columns[k].peek() && i > diagonals[n - 1 - i + k].peek() && i > diagonalsRev[i + k].peek()) {
                        if (i == n - 1 && k == m - 1) {
                            vector.add(new Pair<>(i, k));
                            if (vector.size() > vectors.get(0).size()) {
                                vectors.clear();
                                vectors.add(vector);
                            } else if (vector.size() == vectors.get(0).size()) {
                                vectors.add(vector);
                            }
                            break;
                        }
                        function(vector, i, k, lines, columns, diagonals, diagonalsRev);
                    } else if (i == n - 1 && k == m - 1) {
                        if (vector.size() > vectors.get(0).size()) {
                            vectors.clear();
                            vectors.add(vector);
                        } else if (vector.size() == vectors.get(0).size()) {
                            vectors.add(vector);
                        }
                    }
                } else if (i == n - 1 && k == m - 1) {
                    if (vector.size() > vectors.get(0).size()) {
                        vectors.clear();
                        vectors.add(vector);
                    } else if (vector.size() == vectors.get(0).size()) {
                        vectors.add(vector);
                    }
                }
            }
        } else if (i == n - 1) {
            if (vector.size() > vectors.get(0).size()) {
                vectors.clear();
                vectors.add(vector);
            } else if (vector.size() == vectors.get(0).size()) {
                vectors.add(vector);
            }
            return;
        }
        for (int l = i + 1; l < n; l++) {
            int subLinesNum = 0;
            for (int h = l; h < n; h++) {
                subLinesNum += lines[h].size();
            }
            if (subLinesNum - n + l < vectors.get(0).size() - vector.size()) {
                break;
            }
            for (int k = 0; k < m; k++) {
                if (matrix[l][k] != 2) {
                    if (k > lines[l].peek() && l > columns[k].peek() && l > diagonals[n - 1 - l + k].peek() && l > diagonalsRev[l + k].peek()) {
                        if (l == n - 1 && k == m - 1) {
                            vector.add(new Pair<>(l, k));
                            if (vector.size() > vectors.get(0).size()) {
                                vectors.clear();
                                vectors.add(vector);
                            } else if (vector.size() == vectors.get(0).size()) {
                                vectors.add(vector);
                            }
                            break;
                        }
                        function(vector, l, k, lines, columns, diagonals, diagonalsRev);

                    } else if (l == n - 1 && k == m - 1) {
                        if (vector.size() > vectors.get(0).size()) {
                            vectors.clear();
                            vectors.add(vector);
                        } else if (vector.size() == vectors.get(0).size()) {
                            vectors.add(vector);
                        }
                    }
                } else if (l == n - 1 && k == m - 1) {
                    if (vector.size() > vectors.get(0).size()) {
                        vectors.clear();
                        vectors.add(vector);
                    } else if (vector.size() == vectors.get(0).size()) {
                        vectors.add(vector);
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();

    }
}

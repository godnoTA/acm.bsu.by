//package com.company;

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

    static int[][] matrix = null;
    static boolean[] visited = null;
    static int[] counts = null;
    static int[] rcounts = null;
    static int n = 0;
    static int sourcesNumber = 0;
    static int endsNumber = 0;
    static int componentNumber = 0;
    static Vector<Vector<Integer>> components = new Vector<>();

    static void dfs(Integer node, Integer compNum) {
        for (int i = 0; i < n; i++) {
            if ((matrix[node][i] == 1 || matrix[i][node] == 1) && !visited[i] && i!=node) {
                visited[i] = true;
                components.get(compNum).add(i);
                dfs(i, compNum);
            }
        }
    }

    public static void main(String[] args) {

        try {
            FastScanner fs = new FastScanner("input.txt");
            n = fs.nextInt();
            matrix = new int[n][n];
            counts = new int[n];
            rcounts = new int[n];
            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                visited[i] = false;
            }
            int v;
            for (int i = 0; i < n; i++) {
                while ((v = fs.nextInt()) != 0) {
                    rcounts[v-1]++;
                    counts[i]++;
                    matrix[i][v - 1] = 1;
                }
            }
        } catch (IOException e) {
        }

        int[][] accessMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            accessMatrix[i] = Arrays.copyOf(matrix[i], n);
        }

        for (int i = 0; i < n; i++) {
            accessMatrix[i][i] = 1;
        }

        int[] accessCounts = new int[n];

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    accessMatrix[i][j] = accessMatrix[i][j] | (accessMatrix[i][k] & accessMatrix[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                accessCounts[i] += accessMatrix[i][j];
            }
        }

        int schoolCount = 0;
        int max = accessCounts[0];
        int indMax = 0;

        for (int i = 1; i < n; i++) {
            if (accessCounts[i] > max) {
                max = accessCounts[i];
                indMax = i;
            }
        }
        while (max != 0) {
            schoolCount++;
            for (int j = 0; j < n; j++) {
                if (accessMatrix[indMax][j] == 1) {
                    for (int i = 0; i < n; i++) {
                        if (accessMatrix[j][i] == 1 && j != indMax) {
                            accessMatrix[j][i] = 0;
                            accessCounts[j]--;
                        }
                        if (accessMatrix[i][j] == 1 && i != indMax) {
                            accessMatrix[i][j] = 0;
                            accessCounts[i]--;
                        }
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                if (accessMatrix[indMax][j] == 1) {
                    accessCounts[indMax]--;
                    accessMatrix[indMax][j] = 0;
                }
            }
            max = accessCounts[0];
            indMax = 0;
            for (int i = 1; i < n; i++) {
                if (accessCounts[i] > max) {
                    max = accessCounts[i];
                    indMax = i;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                Vector<Integer> vector = new Vector<>();
                vector.add(i);
                components.add(vector);
                dfs(i, componentNumber);
                componentNumber++;
            }
        }

        if (componentNumber == 1){
            int sources = 0;
            int ends = 0;
            for (Integer j : components.get(0)) {
                if (rcounts[j] == 0) {
                    sources++;
                }
                if (counts[j] == 0) {
                    ends++;
                }
            }
            sourcesNumber = sources;
            endsNumber = ends;
        } else {
            for (Vector<Integer> i : components) {
                int sources = 0;
                int ends = 0;
                for (Integer j : i) {
                    if (rcounts[j] == 0) {
                        sources++;
                    }
                    if (counts[j] == 0) {
                        ends++;
                    }
                }
                sourcesNumber += Math.max(1, sources);
                endsNumber += Math.max(1, ends);
            }
        }
        try {
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            out.println(schoolCount);
            out.println(Math.max(sourcesNumber,endsNumber));
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }
}

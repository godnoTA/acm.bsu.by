import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner sc = new FastScanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        int n = sc.nextInt();
        int[][] mtr = new int[n][n];
        int[] entr = new int[n];
        int met = 1;
        try {
            while (true) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        mtr[i][j] = sc.nextInt();
                    }
                }
            }
        } catch (EOFException e) {
        }
        for (int i = 0; i < n; i++) {
            if (entr[i] == 0) {
                met = rec(mtr, entr, n, i, met);
            }
        }
        for (int k = 0; k < n; k++) {
            fw.write(entr[k] + " ");
        }

        fw.close();
    }


    private static int rec(int[][] mtr, int[] entr, int n, int i, int met) {
        if (entr[i] == 0) {
            entr[i] = met;
            met++;
        }
        for (int j = 0; j < n; j++) {
            if (mtr[i][j] == 1 && entr[j] == 0) {
                entr[j] = met;
                met++;
                met = rec(mtr, entr, n, j, met);
            }
        }
        return met;
    }
}

class FastScanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    public FastScanner(File fileName) throws IOException {
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
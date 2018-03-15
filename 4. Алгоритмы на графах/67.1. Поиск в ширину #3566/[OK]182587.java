import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
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
        Queue<Integer> queue = new LinkedList<>();

        while (met < n + 1) {
            for (int i = 0; i < n; i++) {
                if (entr[i] == 0) {
                    queue.add(i);
                    break;
                }
            }

            while (!queue.isEmpty()) {
                int num = queue.poll();

                if (entr[num] == 0) {
                    entr[num] = met;
                    met++;
                }

                for (int j = 0; j < n; j++) {
                    if (mtr[num][j] == 1 && entr[j] == 0) {
                        queue.add(j);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            fw.write(entr[i] + " ");
        }
        fw.close();
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
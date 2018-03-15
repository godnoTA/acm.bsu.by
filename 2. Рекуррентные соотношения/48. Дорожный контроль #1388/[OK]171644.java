//package pkg48;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

class FastScanner {

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

public class Main implements Runnable {

    public static int N;
    public static LinkedList[] v;
    public static int[] Best, SC;

    public static int Best(int i, int parent) {
        if (Best[i] != -1) {
            return Best[i];
        }
        int a = 1, b = 0;
        for (Object t : v[i]) {
            int tt = (Integer) t;
            if (tt != parent) {
                a += Best(tt, i);
                b += SC[tt] + 1;
            }
        }
        SC[i] = a - 1;
        Best[i] = Math.min(a, b);
        return Best[i];
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        FastScanner s = null;
        try {
            s = new FastScanner("input.txt");
        } catch (IOException ex) {
        }
        try {
            N = s.nextInt();
        } catch (IOException ex) {
        }
        v = new LinkedList[N];
        Best = new int[N];
        SC = new int[N];
        for (int i = 0; i < N; i++) {
            Best[i] = -1;
            int k = -1;
            try {
                k = s.nextInt();
            } catch (IOException ex) {
            }
            v[i] = new LinkedList();
            for (int j = 0; j < k; j++) {
                try {
                    v[i].add(s.nextInt() - 1);
                } catch (IOException ex) {
                }
            }
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter("output.txt");
        } catch (FileNotFoundException ex) {
        }
        out.print(Best(0, -1));
        out.close();
    }

}

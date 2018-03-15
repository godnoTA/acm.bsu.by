//package t51;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class BinaryHeap {

    private int[] heap;
    private int[] data;
    private int max, n;

    public BinaryHeap(int n) {
        max = n;
        this.n = 0;
        heap = new int[max];
        data = new int[max];
    }

    private void swap(int i, int j) {
        int c = heap[i];
        heap[i] = heap[j];
        heap[j] = c;

        c = data[i];
        data[i] = data[j];
        data[j] = c;
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    public void add(int p, int d) {
        heap[n] = p;
        data[n] = d;
        int i = n;
        n++;
        while (heap[getParentIndex(i)] > heap[i]) {
            swap(i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }

    public int[] deleteMin() {
        int dat = data[0];
        int pr = heap[0];
        heap[0] = heap[n - 1];
        data[0] = data[n - 1];
        n--;
        int i = 0;

        while (true) {
            int a = getLeftChildIndex(i);
            int b = getRightChildIndex(i);
            if (a >= n && b >= n) {
                return new int[]{pr, dat};
            }
            if (b < n) {
                if (heap[b] < heap[a]) {
                    a = b;
                }
            }
            if (heap[i] > heap[a]) {
                swap(i, a);
                i = a;
            } else {
                return new int[]{pr, dat};
            }
        }
    }

    public void construct(int all[][]) {
        n = all.length;
        for (int i = 0; i < n; i++) {
            heap[i] = all[i][0];
            data[i] = i;
        }

        for (int ci = getParentIndex(n - 1); ci >= 0; ci--) {
            int i = ci;
            while (true) {
                int a = getLeftChildIndex(i);
                int b = getRightChildIndex(i);
                if (a >= n && b >= n) {
                    break;
                }
                if (b < n) {
                    if (heap[b] < heap[a]) {
                        a = b;
                    }
                }
                if (heap[i] > heap[a]) {
                    swap(i, a);
                    i = a;
                } else {
                    break;
                }
            }
        }
    }
}

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

public class T51 {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner("input.txt");
        int n = fs.nextInt();
        int m = fs.nextInt();

        int all[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                all[i][j] = fs.nextInt();
            }
        }

        int aindex[] = new int[n];
        for (int i = 0; i < n; i++) {
            aindex[i] = 1;
        }

        PrintWriter out = new PrintWriter(new File("output.txt"));
        BinaryHeap bh = new BinaryHeap(n);
        bh.construct(all);
        for (int i = 0; i < m * n; i++) {
            int next[] = bh.deleteMin();
            out.print(next[0]);
            if (i < m * n - 1) {
                out.print(' ');
            }
            if (aindex[next[1]] < m) {
                bh.add(all[next[1]][aindex[next[1]]], next[1]);
                aindex[next[1]]++;
            }
        }
        out.close();
    }

}

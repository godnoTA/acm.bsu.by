//package t17;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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

class Kletka {

    public int x, y;

    public Kletka(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Kletka shift(int dx, int dy) {
        return new Kletka(x + dx, y + dy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kletka other = (Kletka) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

}

class Queue {

    private class Node {

        public Kletka data;
        public Node next, previous;

        public Node(Kletka data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

    }

    private Node first, last;

    public Queue() {
        first = last = null;
    }

    public void push(Kletka k) {
        if (first != null) {
            Node node = new Node(k, null, last);
            last.next = node;
            last = node;
        } else {
            Node node = new Node(k, null, null);
            first = last = node;
        }
    }

    public Kletka pop() {
        if (first == null) {
            return null;
        }

        Kletka kletka = first.data;

        if (first == last) {
            first = last = null;
        } else {
            first = first.next;
            first.previous = null;
        }
        return kletka;
    }

    public boolean hasNext() {
        return first != null;
    }
}

public class T17 {

    public static int n, m, x1, y1, x2, y2;
    public static int[][] doska, visit, result;
    public static Queue qq;
    public static boolean flag;

    public static void push(Kletka kletka, Kletka from) {
        if (!((kletka.x >= 0) && (kletka.y >= 0) && (kletka.x < n) && (kletka.y < m))) {
            return;
        }
        if (visit[kletka.x][kletka.y] == 1) {
            return;
        }

        if ((visit[kletka.x][kletka.y] == -1) && (kletka.equals(from) == false)) {
            return;
        }

        if ((doska[kletka.x][kletka.y] == -1)) {
            return;
        }

        qq.push(kletka);
        //System.out.println("+++(" + (kletka.x + 1) + "; " + (kletka.y + 1) + ")");
        result[kletka.x][kletka.y] = result[from.x][from.y] + 1;
        if ((visit[kletka.x][kletka.y] == 0) && (doska[kletka.x][kletka.y] == 1)) {
            visit[kletka.x][kletka.y] = -1;
        } else {
            visit[kletka.x][kletka.y] = 1;
        }
        if ((kletka.x == x2) && (kletka.y == y2) && (visit[x2][y2] == 1)) {
            flag = false;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner("in.txt");
        n = fs.nextInt();
        m = fs.nextInt();
        doska = new int[n][m];
        visit = new int[n][m];
        result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                doska[i][j] = fs.nextInt();
                visit[i][j] = 0;
                result[i][j] = 0;
            }
        }
        x1 = fs.nextInt() - 1;
        y1 = fs.nextInt() - 1;
        x2 = fs.nextInt() - 1;
        y2 = fs.nextInt() - 1;

        qq = new Queue();
        qq.push(new Kletka(x1, y1));
        visit[x1][y1] = 1;

        if ((x1 == x2) && (y1 == y2)) {
            flag = false;
        } else {
            flag = true;
        }
        while ((qq.hasNext()) && flag) {
            Kletka k = qq.pop();
            //System.out.println("---(" + (k.x + 1) + "; " + (k.y + 1) + ")");
            if (visit[k.x][k.y] == -1) {
                push(k, k);
            } else {
                push(k.shift(1, 2), k);
                push(k.shift(1, -2), k);
                push(k.shift(-1, 2), k);
                push(k.shift(-1, -2), k);
                push(k.shift(2, 1), k);
                push(k.shift(2, -1), k);
                push(k.shift(-2, 1), k);
                push(k.shift(-2, -1), k);
            }
        }

        PrintWriter out = new PrintWriter(new File("out.txt"));
        if (flag || doska[x1][y1] == -1) {
            out.print("No");
        } else {
            out.print(result[x2][y2]);
        }
        out.close();
    }

}

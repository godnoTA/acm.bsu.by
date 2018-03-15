//package t14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import static java.lang.Integer.parseUnsignedInt;
import java.util.ArrayList;

class Point {

    public int n, duga, l;

    public Point(int n, int duga, int l) {
        this.n = n;
        this.duga = duga;
        this.l = l;
    }
}

class BinaryHeap {

    private final Point[] data;
    private int max, n;

    public BinaryHeap(int n) {
        max = n;
        this.n = 0;
        data = new Point[max];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void swap(int i, int j) {
        Point p = data[i];
        data[i] = data[j];
        data[j] = p;
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

    public void add(Point d) {
        data[n] = d;
        int i = n;
        n++;
        while (data[getParentIndex(i)].l > data[i].l) {
            swap(i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }

    public Point deleteMin() {
        Point dat = data[0];
        Point p = data[0];
        data[0] = data[n - 1];
        n--;
        int i = 0;

        while (true) {
            int a = getLeftChildIndex(i);
            int b = getRightChildIndex(i);
            if (a >= n && b >= n) {
                return p;
            }
            if (b < n) {
                if (data[b].l < data[a].l) {
                    a = b;
                }
            }
            if (data[i].l > data[a].l) {
                swap(i, a);
                i = a;
            } else {
                return p;
            }
        }
    }
}

public class T14 {

    private static int[] graf;
    private static int[][] grafData;
    private static int num = 0;

    public static void add(int from, int where, int length) {
        num++;
        grafData[num - 1][0] = where;
        grafData[num - 1][1] = length;
        grafData[num - 1][2] = graf[from];
        grafData[num - 1][3] = from;
        graf[from] = num - 1;
    }

    public static int[] Deikstra(int n, int m, int s) {
        boolean[] visited = new boolean[n];
        int[] metka = new int[n];
        int path[] = new int[n];
        for (int i = 0; i < n; i++) {
            metka[i] = -1;
            path[i] = -1;
        }
        metka[s] = 0;
        BinaryHeap heap = new BinaryHeap(m);
        heap.add(new Point(s, -1, metka[s]));
        while (!heap.isEmpty()) {
            Point p = heap.deleteMin();
            if (!visited[p.n]) {
                visited[p.n] = true;
                path[p.n] = p.duga;
                metka[p.n] = p.l;
                int i = graf[p.n];
                while (i != -1) {
                    if (!visited[grafData[i][0]]) {
                        heap.add(new Point(grafData[i][0], i, p.l + grafData[i][1]));
                    }
                    i = grafData[i][2];
                }
            }
        }
        return path;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("input.in"));
        String s = in.readLine();
        int p = s.indexOf(' ');
        int n = parseUnsignedInt(s.substring(0, p));
        int m = parseUnsignedInt(s.substring(p + 1));
        graf = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            graf[i] = -1;
        }
        grafData = new int[3 * m][4];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ", false);
            int from = parseUnsignedInt(st.nextToken()) - 1;
            int where = parseUnsignedInt(st.nextToken()) - 1;
            int length = parseUnsignedInt(st.nextToken());
            add(from, where, length);
        }
        s = in.readLine();
        p = s.indexOf(' ');
        int start = parseUnsignedInt(s.substring(0, p)) - 1;
        int end = parseUnsignedInt(s.substring(p + 1)) - 1;
        in.close();

        int[] path = Deikstra(n, m, start);

        for (int i = 0; i < n; i++) {
            int j = graf[i];
            while ((j != -1) && (j < m)) {
                add(i + n, grafData[j][0] + n, grafData[j][1]);
                if (path[grafData[j][0]] != j) {
                    add(i, grafData[j][0] + n, grafData[j][1]);
                }
                j = grafData[j][2];
            }
        }

        path = Deikstra(2 * n, 3 * m, start);

        int length = 0;
        ArrayList<Integer> pathResult = new ArrayList<>();
        int i = end + n;
        while (i != start) {
            pathResult.add((i >= n) ? i - n : i);
            length += grafData[path[i]][1];
            i = grafData[path[i]][3];
        }

        PrintWriter out = new PrintWriter("output.out");
        out.println(length);
        out.print(start + 1);
        for (int k = pathResult.size() - 1; k >= 0; k--) {
            out.print(' ');
            out.print(pathResult.get(k) + 1);
        }
        out.close();
    }

}

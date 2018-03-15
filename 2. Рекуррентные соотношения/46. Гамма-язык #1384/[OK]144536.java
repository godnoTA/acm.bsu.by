import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by Yauheni on 29.02.16.
 */


class SthLikeMap {
    class Element implements Comparable<Element> {
        Element(int value, int key) {
            this.value = value;
            this.key = key;
            bigger = null;
            from = key;
        }

        public int value;
        public int key;
        public Element bigger;
        public int from;

        @Override
        public int compareTo(Element o) {
            return value - o.value;
        }
    }

    SthLikeMap(int size) {
        data = new Element[size];
        this.size = size;
        index = 0;
    }

    public void add(int value) {
        data[index] = new Element(value, index);
        index++;
    }

    public void sort() {
        Arrays.sort(data);
    }

    public int key(int value) {
        int l = 0;
        int r = size - 1;
        int s = 0;
        while (l != r) {
            s = (l + r) / 2;
            if (data[s].value < value)
                l = s + 1;
            else
                r = s;
        }
        if (value == data[l].value)
            return data[l].key;
        return -1;
    }

    public Element[] data;
    public int size;
    static int index = 0;
}

public class Hope {
    public static int upperBound(int begin, int end, int value) {
        int s = 0;
        while (begin != end) {
            s = (begin + end) / 2;
            if (d[s] < value)
                begin = s + 1;
            else
                end = s;
        }
        return begin;
    }
    public static void main(String[] args) throws IOException {
        //filling

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new SthLikeMap(n);
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++)
            a.add(Integer.parseInt(st.nextToken()));
        b = new SthLikeMap(m);
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++)
            b.add(Integer.parseInt(st.nextToken()));
        reader.close();

        //solving

        b.sort();

        indices = new int[n];
        boolean found = false;
        for (int i = 0; i < n; i++) {
            indices[i] = b.key(a.data[i].value);
            if (indices[i] == -1) {
                if (found) {
                    indices[i] = indices[i - 1];
                }
                continue;
            }
            if (!found) {
                found = true;
                for (int j = 0; j < i; j++) {
                    indices[j] = indices[i];
                }
            }
        }

        if (!found) {
            PrintWriter pw = new PrintWriter("output.txt");
            pw.print(0);
            pw.close();
            return;
        }

        d = new int[n + 1];
        d[0] = -1;
        for (int i = 1; i <= n; ++i)
            d[i] = n + m;
        int j = 1;
        for (int i = 0; i < n; i++) {
            j = upperBound(0, r, indices[i]);
            if (d[j - 1] < indices[i] && indices[i] < d[j]) {
                if (d[j] == n + m)
                    r++;
                d[j] = indices[i];
            }
        }

        //printing

        PrintWriter pw = new PrintWriter("output.txt");
        for (int i = 1; i < n + 1; i++) {
            if (d[i] == n + m) {
                pw.print(i - 1);
                pw.close();
                return;
            }
        }
        pw.print(n);
        pw.close();
    }

    static public int n;
    static public int m;
    static SthLikeMap a;
    static SthLikeMap b;
    static int[] d;
    //static int[] bestRes;
    static int[] indices;
    static int r = 1;
}

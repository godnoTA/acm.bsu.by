import java.io.*;
import java.util.Scanner;

/**
 * Created by Polina on 13-Mar-16.
 */
public class Solution {
    int n;
    int nMax = 1;
    int s[];
    Element t[];
    StreamTokenizer in;
    PrintWriter out;

    File fileIn = new File("input.txt");
    File fileOut = new File("output.txt");

    public Solution() {
        try {
            run();

            n = nextInt();

            while (nMax < n)
                nMax <<= 1;

            s = new int[nMax];
            t = new Element[nMax * 2 - 1];

            build();
            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    void run() throws IOException {
        in = new StreamTokenizer(new BufferedReader(new FileReader(fileIn)));
        out = new PrintWriter(fileOut);
    }

    void solve() throws IOException {
        int a;
        int b;
        int index;
        int newValue;
        int value;
        long answer;

        int query = nextInt();

        while (query != 0) {
            switch (query) {
                case 1:
                    index = nextInt();
                    newValue = nextInt();
                    set(0, 0, nMax - 1, index, newValue, nMax);
                    break;
                case 2:
                    a = nextInt();
                    b = nextInt();
                    value = nextInt();
                    modify(0, 0, nMax - 1, a, b, value, nMax);
                    break;
                case 3:
                    a = nextInt();
                    b = nextInt();
                    answer = sum(0, 0, nMax - 1, a, b, nMax);
                    printAnswer(answer);
                    break;
                case 4:
                    a = nextInt();
                    b = nextInt();
                    answer = min(0, 0, nMax - 1, a, b, nMax);
                    printAnswer(answer);
                    break;
                case 5:
                    a = nextInt();
                    b = nextInt();
                    answer = max(0, 0, nMax - 1, a, b, nMax);
                    printAnswer(answer);
                    break;
            }
            query = nextInt();
        }

        //for (int i = 0; i < t.length; i++)
          //System.out.println(t[i].sum + " " + t[i].mark);
    }

    public void printAnswer(long answer) {
        out.println(answer);
    }

    public void build() {
        for (int i = 0; i < nMax; i++)
            t[i + nMax - 1] = new Element(s[i], s[i], s[i]);
        for (int i = nMax - 2; i >= 0; i--) {
            int index1 = 2 * i + 1;
            int index2 = 2 * i + 2;
            t[i] = new Element(t[index1].sum + t[index2].sum, Math.min(t[index1].min, t[index2].min), Math.max(t[index1].max, t[index2].max));
        }
    }

    public void modifyDown(int cur, int leavesAmount) {
        int index1 = 2 * cur + 1;
        int index2 = 2 * cur + 2;

        if (index1 < t.length) {
            t[index1].sum += t[cur].mark*leavesAmount;
            t[index1].min += t[cur].mark;
            t[index1].max += t[cur].mark;

            t[index2].sum += t[cur].mark*leavesAmount;
            t[index2].min += t[cur].mark;
            t[index2].max += t[cur].mark;

            t[index1].mark += t[cur].mark;
            t[index2].mark += t[cur].mark;
            t[cur].mark = 0;
        }
    }

    // lb - left bound, rb - right bound, index - position of element, cur - index of current vertex
    public void set(int cur, int lb, int rb, int index, int newVal, int leavesAmount) {
        if (rb == lb) {
            t[cur].sum = newVal;
            t[cur].min = newVal;
            t[cur].max = newVal;
        } else {
            leavesAmount>>=1;

            if (t[cur].mark != 0)
                modifyDown(cur, leavesAmount);

            int index1 = 2 * cur + 1;
            int index2 = 2 * cur + 2;

            int m = (lb + rb) / 2;
            if (index <= m)
                set(index1, lb, m, index, newVal, leavesAmount);
            else
                set(index2, m + 1, rb, index, newVal, leavesAmount);
            //t[cur] = t[cur*2+1]+t[cur*2+2];
            t[cur].sum = t[index1].sum + t[index2].sum;
            t[cur].min = Math.min(t[index1].min, t[index2].min);
            t[cur].max = Math.max(t[index1].max, t[index2].max);
        }
    }

    public void modify(int cur, int lb, int rb, int a, int b, int val, int leavesAmount) {

        if (lb > b || rb < a) return;

        if (lb == a && rb == b) {
            t[cur].sum += val*leavesAmount;
            t[cur].max += val;
            t[cur].min += val;
            t[cur].mark += val;
            return;
        }

        int m = (lb + rb) / 2;
        if (cur * 2 + 1 < t.length) {
            int index1 = 2 * cur + 1;
            int index2 = 2 * cur + 2;

            leavesAmount>>=1;

            if (t[cur].mark != 0)
                modifyDown(cur, leavesAmount);

            modify(index1, lb, m, a, Math.min(m, b), val, leavesAmount);
            modify(index2, m + 1, rb, Math.max(a, m + 1), b, val, leavesAmount);

            t[cur].sum = t[index1].sum + t[index2].sum;
            t[cur].min = Math.min(t[index1].min, t[index2].min);
            t[cur].max = Math.max(t[index1].max, t[index2].max);
        }
    }

    public long sum(int cur, int lb, int rb, int a, int b, int leavesAmount) {
        if (lb == a && rb == b)
            return t[cur].sum;

        if (lb > b || rb < a) return 0;

        leavesAmount>>=1;

        if (t[cur].mark != 0)
            modifyDown(cur, leavesAmount);

        int m = (lb + rb) / 2;
        return sum(cur * 2 + 1, lb, m, a, Math.min(m, b), leavesAmount) + sum(cur * 2 + 2, m + 1, rb, Math.max(a, m + 1), b, leavesAmount);
    }

    public long min(int cur, int lb, int rb, int a, int b, int leavesAmount) {
        if (lb == a && rb == b) {
            return t[cur].min;
        }

        if (lb > b || rb < a) return Integer.MAX_VALUE;

        leavesAmount>>=1;

        if (t[cur].mark != 0)
            modifyDown(cur, leavesAmount);

        int m = (lb + rb) / 2;
        return Math.min(min(cur * 2 + 1, lb, m, a, Math.min(m, b), leavesAmount), min(cur * 2 + 2, m + 1, rb, Math.max(a, m + 1), b, leavesAmount));
    }

    public long max(int cur, int lb, int rb, int a, int b, int leavesAmount) {
        if (lb == a && rb == b) {
            return t[cur].max;
        }

        if (lb > b || rb < a) return Integer.MIN_VALUE;

        leavesAmount>>=1;

        if (t[cur].mark != 0)
            modifyDown(cur, leavesAmount);

        int m = (lb + rb) / 2;
        return Math.max(max(cur * 2 + 1, lb, m, a, Math.min(m, b), leavesAmount), max(cur * 2 + 2, m + 1, rb, Math.max(a, m + 1), b, leavesAmount));
    }

    /*public void modify(int cur, int lb, int rb, int a, int b, int val) {
        //System.out.println(cur + " " + lb + " " + rb + " " + a + " " + b);
        if (lb == rb && lb == a && rb == b) {
            t[cur].sum += val;
            t[cur].min += val;
            t[cur].max += val;
        }

        if (lb > b || rb < a) return;
        int m = (lb + rb) / 2;
        if (cur * 2 + 1 < t.length) {

            int index1 = 2 * cur + 1;
            int index2 = 2 * cur + 2;

            modify(index1, lb, m, a, Math.min(m, b), val);
            modify(index2, m + 1, rb, Math.max(a, m + 1), b, val);
            //t[cur] = t[cur*2+1]+t[cur*2+2];
            t[cur].sum = t[index1].sum + t[index2].sum;
            t[cur].min = Math.min(t[index1].min, t[index2].min);
            t[cur].max = Math.max(t[index1].max, t[index2].max);
        }
    }*/

    /*public long sum(int cur, int lb, int rb, int a, int b) {
        if (lb == a && rb == b)
            return t[cur].sum;

        if (lb > b || rb < a) return 0;

        int m = (lb + rb) / 2;
        return sum(cur * 2 + 1, lb, m, a, Math.min(m, b)) + sum(cur * 2 + 2, m + 1, rb, Math.max(a, m + 1), b);
    }

    public long min(int cur, int lb, int rb, int a, int b) {
        if (lb == a && rb == b) {
            return t[cur].min;
        }

        if (lb > b || rb < a) return Integer.MAX_VALUE;

        int m = (lb + rb) / 2;
        return Math.min(min(cur * 2 + 1, lb, m, a, Math.min(m, b)), min(cur * 2 + 2, m + 1, rb, Math.max(a, m + 1), b));
    }

    public long max(int cur, int lb, int rb, int a, int b) {
        if (lb == a && rb == b) {
            return t[cur].max;
        }

        if (lb > b || rb < a) return Integer.MIN_VALUE;

        int m = (lb + rb) / 2;
        return Math.max(max(cur * 2 + 1, lb, m, a, Math.min(m, b)), max(cur * 2 + 2, m + 1, rb, Math.max(a, m + 1), b));
    }*/

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new Solution();
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        //System.out.println(timeConsumedMillis);
    }

    class Element {
        Element(long s, int mi, int ma) {
            sum = s;
            min = mi;
            max = ma;
            mark = 0;
        }

        long sum;
        int min;
        int max;
        int mark;
    }
}

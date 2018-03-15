//package t58;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Stack;

class Set {

    private int n;
    private int[] tree;

    public Set(int n) {
        this.n = n;
        tree = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] = -1;
        }
    }

    public int find(int e) {
        int i = tree[e];
        if (i < 0) {
            return e;
        }
        while (tree[i] >= 0) {
            i = tree[i];
        }
        return i;
    }

    public void unite(int a, int b) {
        if(a == b)
            return;
        if (tree[b] > tree[a]) {
            int c = b;
            b = a;
            a = c;
        }
        tree[b] += tree[a];
        tree[a] = b;
    }
}

public class T58 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("equal-not-equal.in"));

        String line;
        Stack<Integer> st = new Stack<>();
        line = br.readLine();
        int p = line.indexOf(' ');
        int n = Integer.parseInt(line.substring(0, p));
        Set set = new Set(n);
        while ((line = br.readLine()) != null) {
            p = line.indexOf(' ');
            int a = Integer.parseInt(line.substring(1, p));
            int b = Integer.parseInt(line.substring(p + 5));
            if (line.charAt(p + 1) == '=') {
                set.unite(set.find(a - 1), set.find(b - 1));
            } else {
                st.push(a);
                st.push(b);
            }
        }
        br.close();

        PrintWriter out = new PrintWriter("equal-not-equal.out");
        while (!st.empty()) {
            int as = set.find(st.pop() - 1);
            int bs = set.find(st.pop() - 1);
            if (as == bs) {
                out.print("No");
                out.close();
                return;
            }
        }
        out.print("Yes");
        out.close();
    }

}

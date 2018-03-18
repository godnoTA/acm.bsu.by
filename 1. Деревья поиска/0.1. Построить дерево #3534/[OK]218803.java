

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

class Node {
    Node l;
    Node r;
    int v;

    Node(int v) {
        this.v = v;
    }
}

public class Main {

    static Node root;

    static void Add(int v) {
        if (root == null)
            root = new Node(v);
        else Add(v, root);
    }

    static void Add(int v, Node n) {
        if (v < n.v) {
            if (n.l == null)
                n.l = new Node(v);
            else Add(v, n.l);
        } else if (v > n.v) {
            if (n.r == null)
                n.r = new Node(v);
            else Add(v, n.r);
        }
    }

    static void output(PrintWriter pw, Node n) {
        if (n == null)
            return;

        pw.println(n.v);

        output(pw, n.l);
        output(pw, n.r);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        String line;
        while ((line = br.readLine()) != null) {
            int x = Integer.parseInt(line);
            Add(x);
        }

        PrintWriter pw = new PrintWriter("output.txt");

        output(pw, root);

        pw.close();

    }
}

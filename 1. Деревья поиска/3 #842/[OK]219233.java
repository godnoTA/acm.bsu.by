
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

class Node {
    Node l;
    Node r;
    int v;

    int count;

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

    static void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    static Node deleteRec(Node root, int key) {
        if (root == null) return root;

        if (key < root.v)
            root.l = deleteRec(root.l, key);
        else if (key > root.v)
            root.r = deleteRec(root.r, key);

        else {
            if (root.l == null)
                return root.r;
            else if (root.r == null)
                return root.l;

            root.v = minValue(root.r);

            root.r = deleteRec(root.r, root.v);
        }

        return root;
    }

    static int minValue(Node root) {
        int minv = root.v;
        while (root.l != null) {
            minv = root.l.v;
            root = root.l;
        }
        return minv;
    }

    static int count = 0;

    static int getCount(Node n) {
        if (n == null)
            return 0;


        int c1 = getCount(n.l);
        int c2 = getCount(n.r);
        n.count = c1 + c2 + 1;

        if (Math.abs(c1 - c2) == 1)
            count++;

        return n.count;
    }

    static int cur = 0;

    static void DeleteByCount (Node n){
        if (n == null)
            return;

        DeleteByCount(n.l);

        int c1,c2;

        if (n.l == null)
            c1 = 0;
        else c1 = n.l.count;

        if (n.r == null)
            c2 = 0;
        else c2 = n.r.count;


        if (Math.abs(c1 - c2) == 1){

            if (cur == count /2){
                cur++;
                deleteKey(n.v);
                return;
            }
            cur++;
        }

        DeleteByCount(n.r);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("in.txt"));

        String line;

        while ((line = br.readLine()) != null) {
            int x = Integer.parseInt(line);
            Add(x);
        }

        getCount(root);

        System.out.println(count);

        if (count % 2 != 0)
            DeleteByCount(root);


        PrintWriter pw = new PrintWriter("out.txt");

        output(pw, root);

        pw.close();

    }
}

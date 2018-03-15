import java.io.*;
import java.io.BufferedReader;

public class M0 {
    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(
                        new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter("output.txt");
        Tree tree = new Tree();
        String k= "";
        k = br.readLine();
        while (k != null) {

            tree.root=tree.insert(tree.root, Integer.parseInt(k));
            k = br.readLine();
        }
        tree.straightleft(tree.root, pw);
        pw.close();
    }
}
class Node {
    public Node right;
    public Node left;
    public int key;

    public Node(int key) {
        this.key = key;
    }
}

class Tree {
    public Node root;

    public Node insert(Node node, int v) {
        if (node == null) {
            return new Node(v);
        }
        if (v < node.key) {
            node.left = insert(node.left, v);
        } else if (v > node.key) {
            node.right = insert(node.right, v);
        }
        return node;
    }
    public void straightleft(Node node,PrintWriter pw) {
        if (node != null) {
            pw.println(node.key);
            straightleft(node.left, pw);
            straightleft(node.right, pw);
        }
    }
}
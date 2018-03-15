import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

class MyTree {

    private static PrintStream ps;

    static {
        try {
            ps = new PrintStream(new File("output.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    static class Node {
        int key;
        Node left;
        Node right;
        public Node(int key) {
            this.key = key;
        }
    }
    private Node root;
    public void input() throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        int str;
        while (sc.hasNext()) {
            str = sc.nextInt();
           insert(str);
        }
        sc.close();

    }
    public void insert(int x) {
        root = doInsert(root, x);
    }

    private static Node doInsert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = doInsert(node.left, x);
        } else if (x > node.key) {
            node.right = doInsert(node.right, x);
        }
        return node;
    }

    public void output(Node x) throws IOException {
        ps.println(x.key);
        if(x.left!=null){
           output(x.left);
        }
        if(x.right!=null){
            output(x.right);
        }
    }

    public static void main(String[] args) throws IOException {
        MyTree mt = new MyTree();
        mt.input();
        mt.output(mt.root);
        ps.close();
    }
}

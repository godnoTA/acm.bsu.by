import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class Tree {
    static class Node {
        public int key;
        public Node left;
        public Node right;
        public Node(int key) {
            this.key = key;
        }
    }
    public Node root;
    public void add(int x) {
        root = insert(root, x);
    }
    private static Node insert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = insert(node.left, x);
        } else if (x > node.key) {
            node.right = insert(node.right, x);
        }
        return node;
    }
    public void printAnswer() throws Exception {
        PrintStream ps = new PrintStream("output.txt");
        printAnswer(root, ps);
        ps.close();
    }
    private void printAnswer(Node node, PrintStream ps){
        if(node!=null){
            ps.println(node.key);
            printAnswer(node.left, ps);
            printAnswer(node.right, ps);
        }
    }
    public void deleteKey(int x) {
        root = recursiveDelete(root, x);
    }

    private Node recursiveDelete(Node root, int x) {
        if (root == null) {
            return null;
        }
        if (x < root.key) {
            root.left = recursiveDelete(root.left, x);
            return root;
        } else if (x > root.key) {
            root.right = recursiveDelete(root.right, x);
            return root;
        }
        if (root.left == null) {
            return root.right;
        } else {
            if (root.right == null)
                return root.left;
            else {
                int minKey = findMin(root.right).key;
                root.key = minKey;
                root.right = recursiveDelete(root.right, minKey);
                return root;
            }
        }
    }

    private Node findMin(Node root) {
        if (root.left != null)
            return findMin(root.left);
        else
            return root;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        Tree tree = new Tree();
        int key = sc.nextInt();
        while (sc.hasNext()){
            tree.add(sc.nextInt());
        }
        tree.deleteKey(key);
        tree.printAnswer();
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by vlad on 01.04.2016.
 */
class Node {
    private int key;
    private Node left;
    private Node right;

    public Node(int value) {
        this.key = value;
        this.left = null;
        this.right = null;

    }

    public void setKey(int key) {
        this.key = key;
    }
    public int getKey() {
        return key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}
class Tree {
    private Node root;
    public Node getRoot() {
        return root;
    }
    public Tree() {
        root = null;
    }

    public void insert(int x) {
        if (root == null) {
            root = new Node(x);
            return;
        }

        Node leaf = root;
        while (true) {
            if (x < leaf.getKey()) {
                if (leaf.getLeft() == null) {
                    leaf.setLeft(new Node(x));
                    return;
                } else {
                    leaf = leaf.getLeft();
                }
            } else if (x > leaf.getKey()) {
                if (leaf.getRight() == null) {
                    leaf.setRight(new Node(x));
                    return;
                } else {
                    leaf = leaf.getRight();
                }
            } else {
                return;
            }
        }
    }

    Node minimum(Node leaf) {
        while (leaf.getLeft() != null)
            leaf = leaf.getLeft();
        return leaf;
    }

    Node delete(Node root, int key) {
        if (root == (null)) {
            return root;
        }
        if (key < root.getKey()) {
            root.setLeft(delete(root.getLeft(), key));
        } else if (key > root.getKey()) {
            root.setRight(delete(root.getRight(), key));
        } else if (root.getLeft() != null && root.getRight() != null) {
            root.setKey(minimum(root.getRight()).getKey());
            root.setRight(delete(root.getRight(), minimum(root.getRight()).getKey()));
        } else if (root.getLeft() != null) {
            root = root.getLeft();
        } else {
            root = root.getRight();
        }
        return root;
    }

    private int maxLength = 0;
    private Node maxNode;
    private int maxHeight = Integer.MAX_VALUE;

    public int dfs(Node leaf, int height) {
        if (leaf == null)
            return 0;
        int leftLength = dfs(leaf.getLeft(), height+1);
        int rightLength = dfs(leaf.getRight(), height+1);
        if (leftLength+rightLength > maxLength || (leftLength+rightLength == maxLength && height+1 < maxHeight)) {
            maxLength = leftLength+rightLength;
            maxNode = leaf;
        }
        return Math.max(leftLength, rightLength) + 1;
    }

    public void findMaxAndDelete() {
        dfs(root, 0);
        root = delete(root, maxNode.getKey());
    }
}

public class Main implements Runnable{

    static Scanner sc;
    static PrintWriter pw;

    public static void printNode(Node node) {
        if (node == null)
            return;
        pw.println(node.getKey());
        printNode(node.getLeft());
        printNode(node.getRight());
    }

    public static void print(Tree tree) {
        printNode(tree.getRoot());
    }

    public void run() {
        try {
            sc = new Scanner(new File("in.txt"));
            pw = new PrintWriter(new File("out.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tree tree = new Tree();
        while (sc.hasNextInt()) {
            tree.insert(sc.nextInt());
        }
        sc.close();
        tree.findMaxAndDelete();
        print(tree);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();

    }
}

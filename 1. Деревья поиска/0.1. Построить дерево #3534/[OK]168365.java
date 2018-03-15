import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by User on 19.02.2017.
 */
public class Tree {


    public List<Integer> forKeys = new ArrayList<Integer>();

    static private class Node {
        public int key;
        public Node right;
        public Node left;

        public Node(int key) {
            this.key = key;
        }
    }

    private Node root;

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


    public void leftTravelsar(Node node) {
        if (node == null) {
            return;
        }
 /*       try {
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            pw.println(node.key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } */
        System.out.println(node.key + " ");
        forKeys.add(node.key);
        leftTravelsar(node.left);
        leftTravelsar(node.right);
    }

    public Node getRoot() {
        return root;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Tree mainTree = new Tree();

        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNext()) {
            int x = sc.nextInt();
            mainTree.insert(x);
        }

        mainTree.leftTravelsar(mainTree.getRoot());
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        for (Integer number : mainTree.forKeys) {
            pw.println(number);
        }
        pw.close();
    }
}
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));

        FileWriter fw = new FileWriter(new File("output.txt"));
        try {
            if (sc.hasNextLong()) {
                //long delnum = sc.nextLong();
                Tree tree = new Tree(new Node(sc.nextLong()));
                while (sc.hasNextLong())
                    tree.insert(sc.nextLong(), tree.root);
                //tree.root = tree.deleteRec(tree.root, delnum);
                fw.append(tree.getDirectLeft());
            }
        } finally {
            sc.close();
            fw.close();
        }

    }
}

class Tree {
    Node root;

    Tree(Node root) {
        this.root = root;
    }

    boolean insert(long key, Node node) {
        if (node.key == key)
            return false;
        if (key < node.key) {
            if (node.left != null) insert(key, node.left);
            else node.left = new Node(key);
        } else {
            if (node.right != null) insert(key, node.right);
            else node.right = new Node(key);
        }
        return true;
    }

    ArrayList<Node> directLeft(Node node) {
        ArrayList<Node> res = new ArrayList<>();
        if (root.equals(node))
            res.add(node);
        if (node.left != null) {
            res.add(node.left);
            res.addAll(directLeft(node.left));
        }
        if (node.right != null) {
            res.add(node.right);
            res.addAll(directLeft(node.right));
        }
        return res;
    }

    String getDirectLeft() {
        ArrayList<Node> nodes = directLeft(root);
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes)
            sb.append(n.key).append("\n");
        return sb.toString();
    }

    Node findMin(Node root) {
        if (root.left != null) return findMin(root.left);
        else return root;
    }

    Node deleteRec(Node root, long key) {
        if (root == null)
            return null;


        if (key < root.key) {
            root.left = deleteRec(root.left, key);
            return root;

        } else {
            if (key > root.key) {
                root.right = deleteRec(root.right, key);
                return root;
            }
        }
        if (root.left == null) return root.right;
        else {
            if (root.right == null) return root.left;
            else {
                long minKey = findMin(root.right).key;
                root.key = minKey;
                root.right = deleteRec(root.right, minKey);
                return root;
            }
        }
    }
}

class Node {
    long key;
    Node left;
    Node right;

    Node(long key) {
        this.key = key;
    }

    Node(long key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (key != node.key) return false;
        if (left != null ? !left.equals(node.left) : node.left != null) return false;
        return right != null ? right.equals(node.right) : node.right == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (key ^ (key >>> 32));
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}

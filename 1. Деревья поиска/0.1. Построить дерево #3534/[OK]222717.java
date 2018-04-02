import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Runnable {
    public static void main(String[] args) throws IOException {
       Thread t = new Thread(null, new Main(), "", 10 *1024*1024){

       };
       t.start();


    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            FileWriter fw = new FileWriter(new File("output.txt"));
            String str;
            if ((str = br.readLine()) != null) {
                MyTree tree = new MyTree(new Node(Integer.parseInt(str)));
                while ((str = br.readLine()) != null)
                    tree.insert(Integer.parseInt(str), tree.root);
                fw.append(tree.getStringFromDirectLeft());
            }
            br.close();
            fw.close();
        }catch (Exception ognore){

        }
    }
}

class MyTree {
    Node root;

    MyTree(Node root) {
        this.root = root;
    }

    boolean insert(int key, Node node) {
        if (node.key == key)
            return false;
        if (key < node.key) {
            if (node.left != null) insert(key, node.left);
            else node.left = new Node(key, node);
        } else {
            if (node.right != null) insert(key, node.right);
            else node.right = new Node(key, node);
        }
        return true;
    }

    void rightRemove(int key) {


    }


    Node findNode(int key, Node currentNode) throws NoNodeWithKeyException {
        try {
            if (key == currentNode.key)
                return currentNode;
            if (key < currentNode.key)
                return findNode(key, currentNode.left);
            return findNode(key, currentNode.right);
        } catch (NullPointerException e) {
            throw new NoNodeWithKeyException(key);
        }
    }

    Node findAndRemoveMinRight(Node node, Node parent) {
        if (node == null) return null;
        if (node.left != null) return findAndRemoveMinRight(node.left, node);
        if (parent.left != null)
            if (parent.left.equals(node)) {
                parent.left = node.right;
                return node;
            }
        parent.right = node.right;
        return node;
    }


    ArrayList<Node> directLeft(Node node) {
        try {
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
        } catch (NullPointerException e) {
            return new ArrayList<Node>();
        }
    }

    String getStringFromDirectLeft() {
        ArrayList<Node> nodes = directLeft(root);
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes)
            sb.append(n.key).append("\n");
        return sb.toString();
    }
}

class Node {
    int key;
    Node left;
    Node right;
    Node parent;

    Node(int key) {
        this.key = key;
    }

    public Node(int key, Node parent) {
        this.key = key;
        this.parent = parent;
    }

    public Node(int key, Node left, Node right, Node parent) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return key == node.key;
    }

    @Override
    public int hashCode() {
        return key;
    }
}

class NoNodeWithKeyException extends Exception {
    int key;

    public NoNodeWithKeyException(int key) {
        this.key = key;
    }

@Override
    public String toString() {
        return "NoNodeWithKeyException: " +
                "key = " + key;
    }
}
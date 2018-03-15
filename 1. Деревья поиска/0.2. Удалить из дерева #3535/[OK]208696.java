import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        long deleteKey = 0;
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        if (scanner.hasNext()){
            deleteKey = scanner.nextInt();
        }
        else 
            return;
        while (scanner.hasNext()) {
            tree.add(Integer.parseInt(scanner.next()));
        }
        FileWriter fout = null;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            tree.setRoot(tree.delete(tree.getRoot(),deleteKey));
            tree.PreOrder(tree.getRoot(),bufferedWriter);
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }
}

class Tree {

    static class Node {

        public Node(long value) {
            key = value;
        }

        private long key;
        private Node left;
        private Node right;
    }

    public void add(long x) {
        root = addTo(root, x);
    }

    private static Node addTo(Node node, long x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = addTo(node.left, x);
        } else if (x > node.key) {
            node.right = addTo(node.right, x);
        }
        return node;
    }

    public void PreOrder(Node t, BufferedWriter fileWriter) throws IOException { //рекурсивная функция
        if (t == null)
            return;
        else {
            long temp = t.key;
            fileWriter.write(temp + "\n");
            PreOrder(t.left, fileWriter);
            PreOrder(t.right, fileWriter);
        }
    }

    public Node findMin(Node v){
        if (v.left != null)
            return findMin(v.left);
        else return v;
    }

    public Node delete(Node v, long x){
        if (v == null)
            return null;
        if (x < v.key) {
            v.left = delete(v.left, x);
            return v;
        }
        if (x > v.key) {
            v.right = delete(v.right, x);
            return v;
        }

        if (v.left == null) {
            return v.right;
        }
        else if (v.right == null) {
            return v.left;
        }
        else {
            long min = findMin(v.right).key;
            v.key = min;
            v.right = delete(v.right, min);
            return v;
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private Node root;
}
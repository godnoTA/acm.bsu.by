import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class Node{
    int key;
    Node rightSon;
    Node leftSon;

    public Node(int key){
        this.key = key;
    }
}

class Tree {
    public Node root;

    public Tree(){
        root = null;
    }

    public Node getRoot() {
        return root;
    }
    public void setRoot(Node v){
        root = v;
    }

    public void insert(int x) {
        root = doInsert(root, x);
    }

    private static Node doInsert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.leftSon = doInsert(node.leftSon, x);
        } else if (x > node.key) {
            node.rightSon = doInsert(node.rightSon, x);
        }
        return node;
    }

    public void rightLeftOrder(Node n, PrintWriter out){
        if (n != null){
            out.println(n.key);
            rightLeftOrder(n.leftSon, out);
            rightLeftOrder(n.rightSon, out);
        }
    }

    private static int min(Node node) {
        if (node.leftSon == null) {
            return node.key;
        }
        return min(node.leftSon);
    }

    private static Node FindMin(Node v) {
        if (v.leftSon != null)
            return FindMin(v.leftSon);
        else
            return v;
    }

    public static Node DeleteRecursively(Node n, int value){
        if (null == n)
            return null;

        if (value < n.key) {
            n.leftSon = DeleteRecursively(n.leftSon, value);
            return n;
        }
        else if (value > n.key) {
            n.rightSon = DeleteRecursively(n.rightSon, value);
            return n;
        }

        if (n.leftSon == null) {
            return n.rightSon;
        }
        else if (n.rightSon == null){
            return n.leftSon;
        }
        else{
            int min_key = FindMin(n.rightSon).key;
            n.key = min_key;
            n.rightSon = DeleteRecursively(n.rightSon, min_key);
            return n;
        }
    }

    public void delete(int value){
        root = DeleteRecursively(root, value);
    }
}

public class MainClass {
    public static void main(String[] args) {
        Tree tree = new Tree();
        try {
            Scanner in = new Scanner(new File("input.txt"));
            int node = in.nextInt();
            while (in.hasNextInt())
                tree.insert(in.nextInt());
            tree.delete(node);
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("File was not found");
            System.err.println(e.toString());
        }

        try{
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            tree.rightLeftOrder(tree.getRoot(), pw);
            pw.close();
        }
        catch(FileNotFoundException e){e.printStackTrace();}
    }
}


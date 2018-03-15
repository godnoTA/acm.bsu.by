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
    private Node root;

    public Tree(){
        root = null;
    }

    public Node getRoot() {
        return root;
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
}

public class MainClass {
    public static void main(String[] args) {
        Tree tree = new Tree();
        try {
            Scanner in = new Scanner(new File("input.txt"));
            while (in.hasNextInt())
                tree.insert(in.nextInt());
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

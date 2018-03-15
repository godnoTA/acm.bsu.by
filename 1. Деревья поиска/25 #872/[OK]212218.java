import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;

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

    public int generalNodesQuantity(Node n){
        int left = 0;
        int right = 0;
        if (n != null) {
            if (n.leftSon != null)
                left = generalNodesQuantity(n.leftSon);
            else
                left = 0;
            if (n.rightSon != null)
                right = generalNodesQuantity(n.rightSon);
            else
                right = 0;
        }

        return left + right + 1;
    }

    private int nullTest(Node n){
        if (n == null)
            return 0;
        else
            return generalNodesQuantity(n);
    }

    public int leftMajorQuantity(Node n){
        return Math.abs((nullTest(n.leftSon) - nullTest(n.rightSon)));
    }

    public Vector<Integer> maxDifference(Node n, Vector<Integer> v){
        if (n != null){
            v.add(leftMajorQuantity(n));
            maxDifference(n.leftSon, v);
            maxDifference(n.rightSon, v);
        }
        return v;
    }

    public Vector<Integer> ordinaryOrder(Node n, Vector<Integer> leftmajornodes, int max){
        if (n != null){
            if (leftMajorQuantity(n) == max)
                leftmajornodes.add(n.key);
            ordinaryOrder(n.leftSon, leftmajornodes, max);
            ordinaryOrder(n.rightSon, leftmajornodes, max);
        }
        return leftmajornodes;
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
        Vector<Integer> treevector = new Vector<>();
        try {
            Scanner in = new Scanner(new File("tst.in"));
            while (in.hasNextInt())
                tree.insert(in.nextInt());
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("File was not found");
            System.err.println(e.toString());
        }
        int max = Collections.max(tree.maxDifference(tree.getRoot(), treevector));
        treevector.clear();
        treevector = tree.ordinaryOrder(tree.getRoot(), treevector, max);
        System.out.println(treevector.size());
        Collections.sort(treevector);
        System.out.println(treevector.elementAt(treevector.size() / 2));
        try{
            if (treevector.size() % 2 != 0)
                tree.delete(treevector.elementAt(treevector.size() / 2));
            PrintWriter pw = new PrintWriter(new File("tst.out"));
            tree.rightLeftOrder(tree.getRoot(), pw);
            pw.close();
        }
        catch(FileNotFoundException e){e.printStackTrace();}
    }
}


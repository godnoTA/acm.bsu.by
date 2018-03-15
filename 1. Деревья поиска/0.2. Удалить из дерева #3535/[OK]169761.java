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




    public void deleterecursivly(int x){
        root = deleterecur(root, x);
    }

    public static Node deleterecur(Node current, int x){
        if( current == null){
            return null;
        }
        if( x < current.key){
            current.left = deleterecur(current.left, x);
            return current;
        }
        else if(x > current.key){
            current.right = deleterecur(current.right, x);
            return current;
        }
        if(current.left == null){
            return current.right;
        }
        else if (current.right==null){
            return current.left;
        }
        else{
            int min = findmin(current.right).key;
            current.key = min;
            current.right = deleterecur(current.right, min);
            return current;

        }
    }
    public static Node findmin(Node current){
        if( current.left != null){
            return findmin(current.left);
        }
        else return current;
    }


    public Node getRoot() {
        return root;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Tree mainTree = new Tree();

        Scanner sc = new Scanner(new File("input.txt"));
        int count = 0;
        int forDeleting = 0;
        while (sc.hasNext()) {
            int x = sc.nextInt();
            if (count == 0) {
                forDeleting = x;
            }
            if (count > 0) {
                mainTree.insert(x);
            }
            count++;
        }

        mainTree.deleterecursivly(forDeleting);

        mainTree.leftTravelsar(mainTree.getRoot());

        PrintWriter pw = new PrintWriter(new File("output.txt"));
        for (Integer number : mainTree.forKeys) {
            pw.println(number);
        }
        pw.close();
    }
}
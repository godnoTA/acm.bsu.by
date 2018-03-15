import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws  Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        BinaryTree tset = new BinaryTree();
        while (sc.hasNextInt()) {
            tset.add(sc.nextInt());
        }
        tset.printTree();
    }
}

class Node {
    public int data;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int item) {
        this.data = item;
    }
}

class BinaryTree {
    private PrintWriter pr;
    private Node root;

    public BinaryTree() throws Exception{
        this.root = null;
        pr = new PrintWriter(new File("output.txt"));
    }

    public void add(int item) {
        if (root == null) {
            root = new Node(item);
        } else {
            insertAfter(item, root);
        }
    }

    private void insertAfter(int item, Node node) {
        if (node.data < item) {
            if (node.right != null) {
                insertAfter(item, node.right);
            } else {
                node.right = new Node(item);
            }
        } else if (node.data > item) {
            if (node.left != null) {
                insertAfter(item, node.left);
            } else {
                node.left = new Node(item);
            }
        }
    }

    public void printTree(){
        printNode(root);
        pr.close();
    }
    private void printNode(Node node){
        if(node==null){
            return;
        }
        pr.println(node.data);
        printNode(node.left);
        printNode(node.right);
    }
}



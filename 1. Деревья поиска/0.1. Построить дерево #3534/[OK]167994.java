import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {


        Scanner sc = new Scanner(new File("input.txt"));
        Tree tree = new Tree(sc.nextInt());
        PrintStream wr = new PrintStream("output.txt");
        while (sc.hasNext()) {
            tree.add(sc.nextInt());
        }
        tree.print(tree.root, wr);

        sc.close();
    }
}


class Tree {
    private class Node {
        private Integer key;
        private Node left;
        private Node right;

        public Node(Integer key) {
            this.key = key;
            left = null;
            right = null;
        }

    }

    Node root;

    public Tree(Integer n) {
        root = new Node(n);
    }

    public boolean add(Integer newEl)  {
        if (root == null) {
            root = new Node(newEl);
        } else {
            Node work = root;
            Node nodeFin = null;
            while (work != null) {
                nodeFin = work;
                if (work.key < newEl) {
                    work = work.right;
                } else if (work.key > newEl) {
                    work = work.left;
                } else
                    if (work.key == newEl){
return false;
}
            }
            Node newNode = new Node(newEl);
            if (nodeFin.key < newNode.key) {
                nodeFin.right = newNode;
            } else
                nodeFin.left = newNode;
        }
return true;

    }

    public void print(Node node, PrintStream wr) throws Exception {
        if (node != null) {
            wr.println(node.key);
            print(node.left, wr);
            print(node.right, wr);
        }
    }
}


   

import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


class Node {
    public int key;
    public Node left;
    public Node right;

    public Node(int key) {
        this.key = key;
    }
}

public class BinaryTree {
    public Node root;

    public void insert(int x) {
        Node parent = null;
        Node node = root;
        while (node != null) {
            parent = node;
            if (x < node.key) {
                node = node.left;
            } else if (x > node.key) {
                node = node.right;
            } else {
                return;
            }
        }

        Node newNode = new Node(x);

        if (parent == null) {
            root = newNode;
        } else if (x < parent.key) {
            parent.left = newNode;
        } else if (x > parent.key) {
            parent.right = newNode;
        }
    }
    StringBuilder s = new StringBuilder();
    public void printTree(Node n) {
        if (n == null)
            return;
        s.append(String.valueOf(n.key)+"\n");
        printTree(n.left);
        printTree(n.right);
    }
    public String getStrForTree(){
        return s.toString();
    }
    public static void main(String args[]){
        BinaryTree tr= new BinaryTree();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int num;
            while (sc.hasNext()) {
                num = sc.nextInt();
                tr.insert(num);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tr.printTree(tr.root);
        FileWriter writer;
        try {
            writer = new FileWriter("output.txt");
            writer.write(tr.getStrForTree());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.util.*;

public class Test {

    static class Node {
        private int key;
        private Node left;
        private Node right;

        public Node(int key) {this.key = key;}

        public int getKey() {return this.key;}
        public Node getLeft() {return this.left;}
        public Node getRight() {return this.right;}
    }

    static class Tree {
        Node root;

        public Tree() {this.root = null;}

        public boolean isEmpty() {return root == null;}

        public void add(int key) {root = add(root, key);}

        private static Node add(Node current, int key) {
            if (current == null) {
                return new Node(key);
            }
            if (key < current.key) {
                current.left = add(current.left, key);
            } else if (key > current.key) {
                current.right = add(current.right, key);
            }
            return current;
        }

        private void visit(Node current,PrintWriter out ) {

            if (current == null)
                return;
            out.println(current.getKey());
            visit(current.getLeft(), out);
            visit(current.getRight(), out);
        }
    }

    public void run(){
        try{
            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            Tree tree=new Tree();
            while (in.hasNext()) {
                tree.add(in.nextInt());
            }
            if(!tree.isEmpty()){
                tree.visit(tree.root, out);}
            out.flush();

        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}
import java.io.*;
import java.util.Scanner;

public class Solution implements Runnable {

    private static class Tree {

        class Node {
            Node l,r;
            long key;
            Node(long key) {
                this.key = key;
                this.l = null;
                this.r = null;
            }
        }

        Node head;

        Tree(long key) {
            head = new Node(key);
        }

        void addNode(long key, Node node) {
            if(node == null) {
                node = new Node(key);
            }
            if(key < node.key) {
                if(node.l == null){
                    node.l = new Node(key);
                }
                else {
                    addNode(key, node.l);
                }
            }
            if(key > node.key) {
                if(node.r == null) {
                    node.r = new Node(key);
                }
                else {
                    addNode(key, node.r);
                }
            }

        }

        void leftPreOrdered(Node node, PrintWriter out) {
            if(node != null){
                out.println(node.key);
                leftPreOrdered(node.l, out);
                leftPreOrdered(node.r, out);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        new Thread(null, new Solution(), "", 64 * 1024 * 1024).start();
    }
    public void run() {
        try {
            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            Tree tree = null;
            tree = new Tree(in.nextLong());
            while (in.hasNext()) {
                long x = in.nextLong();
                tree.addNode(x, tree.head);
            }
            tree.leftPreOrdered(tree.head, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

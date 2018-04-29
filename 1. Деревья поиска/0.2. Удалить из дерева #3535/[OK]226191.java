import java.io.*;
import java.util.Scanner;

public class Solution implements Runnable {

    private static class Tree {

        class Node {
            Node l, r, parent;
            long key;
            Node(long key) {
                this.key = key;
                this.l = null;
                this.r = null;
                this.parent = null;
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
                    node.l.parent = node;
                }
                else {
                    addNode(key, node.l);
                }
            }
            if(key > node.key) {
                if(node.r == null) {
                    node.r = new Node(key);
                    node.r.parent = node;
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

        void deleteNode(long key, Node node) {
            if(key == node.key) {
                if(node.r != null) {
                    if(node.l != null) {
                        Node temp = node.r;
                        if(temp.l != null) {
                            while(temp.l != null){
                                temp = temp.l;
                            }
                            node.key = temp.key;
                            temp.parent.l = temp.r;
                        }
                        else {
                            node.key = temp.key;
                            temp.parent.r = temp.r;
                        }
                    }
                    else {
                        node.key = node.r.key;
                        node.l = node.r.l;
                        node.r = node.r.r;
                    }
                }
                else {
                    if(node.l != null) {
                        node.key = node.l.key;
                        node.r = node.l.r;
                        node.l = node.l.l;
                    }
                    else {
                        if(node.parent != null) {
                            if(node.parent.l != null && node.parent.l.key == node.key) {
                                node.parent.l = null;
                            }
                            else {
                                node.parent.r = null;
                            }
                        }
                        else {
                            node = null;
                        }
                    }
                }
                return;
            }
            if(key < node.key) {
                if(node.l != null) {
                    deleteNode(key, node.l);
                }
                else {
                    return;
                }
            }
            if(key > node.key) {
                if(node.r != null) {
                    deleteNode(key, node.r);
                }
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
            long key = in.nextLong();
            Tree tree = null;
            tree = new Tree(in.nextLong());
            while (in.hasNext()) {
                long x = in.nextLong();
                tree.addNode(x, tree.head);
            }
            tree.deleteNode(key, tree.head);
            tree.leftPreOrdered(tree.head, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

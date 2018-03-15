import java.io.*;
import java.util.Scanner;

import java.util.TreeSet;

public class Main {
    public static void main(String []arg) throws IOException {
        try{
            PrintWriter  writer = new PrintWriter("output.txt");
            class Tree {
                class Node {
                    public Tree left;
                    public Tree right;
                    public int value;

                    Node(int v) {
                        left = null;
                        right = null;
                        value = v;
                    }
                }
                private Node node;

                public Tree(int v) {
                    node = new Node(v);
                }

                public Tree() {
                    node = null;
                }

                public void insert(int v) {
                    if (node == null) {
                        node = new Node(v);
                        return;
                    }
                    switch (Integer.compare(node.value, v)) {
                        case 0:
                            return;
                        case 1:
                            if (node.left == null) node.left = new Tree(v);
                            else node.left.insert(v);
                            break;
                        case -1:
                            if (node.right == null) node.right = new Tree(v);
                            else node.right.insert(v);
                            break;
                    }
                }

                public boolean search(int v) {
                    if (node == null) return false;
                    switch (Integer.compare(node.value, v)) {
                        case 0:
                            return true;
                        case 1:
                            if (node.left == null) return false;
                            else return node.left.search(v);
                        case -1:
                            if (node.right == null) return false;
                            else return node.right.search(v);
                    }
                    return false;
                }


                //Корень - Левый - Правый

                private void traversePrefix(Tree t) {
                    if (t != null) {
                       // s.concat(t.node.value + " ");
                        writer.println(t.node.value);
                        traversePrefix(t.node.left);
                        traversePrefix(t.node.right);
                    }
                }

                public void traversePrefix() {traversePrefix(this);}
            }

            Tree tr = new Tree();
            Scanner in = new Scanner(new File("input.txt"));
            StringBuffer data= new StringBuffer();
            while (in.hasNext())
                tr.insert(in.nextInt());
            tr.traversePrefix();
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
     static class Tree {
         private class Node {
            public Node left;
            public Node right;
            public int value;

            Node(int v) {
                left = null;
                right = null;
                value = v;
            }
        }
        private Node node;

        private Tree(int v) {
            node = new Node(v);
        }

        private Tree() {
            node = null;
        }

        private void insert(int v) {
             if (node == null) {
                 node = new Node(v);
             } else insert(node, v);
        }

        private Node insert(Node n,int v){
            if (n == null)   return new Node(v);
            if (v<n.value)
                n.left=insert(n.left,v);
            if (v>n.value)
                n.right=insert(n.right,v);
            //n.value==x
            return n;
        }

        private void delete(int v) {
             node = delete(node, v);
        }

        private Node delete(Node n, int v) {
            if (n == null) return null;
            if (v < n.value) {
                n.left = delete(n.left, v);
                return n;
            }
            if (v > n.value) {
                n.right = delete(n.right, v);
                return n;
            }
            //n.value==v
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;
            else {
               int min_val = findMin(n.right).value;
               n.value = min_val;
               n.right = delete(n.right, min_val);
               return n;
            }
        }

        private Node findMin(Node n){
            if(n.left!=null) return findMin(n.left);
            else return n;
        }

        private ArrayList<Integer> ar;

        private void preOrderTraversal(Node n) {
            if (n != null) {
                ar.add(n.value);
                preOrderTraversal(n.left);
                preOrderTraversal(n.right);
            }
        }

         private ArrayList<Integer> toArrayList() {
             ar = new ArrayList<>();
             preOrderTraversal(node);
             return ar;
         }
    }

    public static void main(String []arg) throws IOException {
        try{
            Tree tree = new Tree();
            Scanner in = new Scanner(new File("input.txt"));
            StringBuffer data= new StringBuffer();
            int del=in.nextInt();
            while (in.hasNext())
                tree.insert(in.nextInt());
            tree.delete(del);

            PrintWriter  out = new PrintWriter("output.txt");
            tree.toArrayList().forEach(out::println);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
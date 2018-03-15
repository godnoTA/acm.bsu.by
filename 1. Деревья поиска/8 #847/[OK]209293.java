
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static class Tree {
        private class Node {
            Node left;
            Node right;
            long value;
            int height;
            int way;

            Node(long v) {
                left = null;
                right = null;
                value = v;
                height=0;
                way=0;
            }
        }
        private Node node;

        private Tree(long v) {
            node = new Node(v);
        }

        private Tree() {
            node = null;
        }

        private void insert(long v) {
            if (node == null) {
                node = new Node(v);
            } else insert(node, v);
        }

        private Node insert(Node n,long v){
            if (n == null)   return new Node(v);
            if (v<n.value)
                n.left=insert(n.left,v);
            if (v>n.value)
                n.right=insert(n.right,v);
            //n.value==x
            return n;
        }

        private void delete(long v) {
            node = delete(node, v);
        }

        private Node delete(Node n, long v) {
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
                long min_val = findMin(n.right).value;
                n.value = min_val;
                n.right = delete(n.right, min_val);
                return n;
            }
        }

        private Node findMin(Node n){
            if(n.left!=null) return findMin(n.left);
            else return n;
        }

        private ArrayList<Long> ar;
        private ArrayList<Node> arNode;

        private void preOrderTraversal(Node n) {
            if (n != null) {
                ar.add(n.value);
                preOrderTraversal(n.left);
                preOrderTraversal(n.right);
            }
        }
        private void preOrderTraversalWay(Node n) {
            if (n != null) {
                arrangeWay(n);
                preOrderTraversalWay(n.left);
                preOrderTraversalWay(n.right);
            }
        }

        private void arrangeWay(Node n){
            if(n.right==null && n.left==null){
                n.way=0;
            }
            else if(n.left==null){
                n.way=n.right.height+1;
            }
            else if(n.right==null){
                n.way=n.left.height+1;
            }
            else n.way=n.right.height+n.left.height+2;


        }
        private void postOrderTraversal(Node n){
            if (n != null) {
                postOrderTraversal(n.left);
                postOrderTraversal(n.right);
                arrangeHeight(n);
            }

        }

        private void arrangeHeight(Node n){
            if(n.right==null && n.left==null){
                n.height=0;
            }
            else if(n.left==null){
                n.height=n.right.height+1;
            }
            else if(n.right==null){
                n.height=n.left.height+1;
            }
            else {
                if(n.right.height>n.left.height)
                    n.height=n.right.height+1;
                else n.height=n.left.height+1;
            }
            arNode.add(n);
        }

        private ArrayList<Long> toArrayListPrint() {
            ar = new ArrayList<>();
            preOrderTraversal(node);
            return ar;
        }

        private long toArrayListNode(){
            arNode = new ArrayList<>();
            postOrderTraversal(node);
            preOrderTraversalWay(node);
            arNode.sort(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if (o1.way - o2.way == 0)
                        return Integer.compare(o1.height, o2.height);
                    return Integer.compare(o1.way, o2.way);
                }
            });
            return arNode.get(arNode.size()-1).value;
        }
    }

    public static void main(String []arg) throws IOException {
        try{
            Tree tree = new Tree();
            Scanner in = new Scanner(new File("in.txt"));
            StringBuffer data= new StringBuffer();

            while (in.hasNext())
                tree.insert(in.nextLong());

            PrintWriter  out = new PrintWriter("out.txt");
            tree.delete(tree.toArrayListNode());
            tree.toArrayListPrint().forEach(out::println);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

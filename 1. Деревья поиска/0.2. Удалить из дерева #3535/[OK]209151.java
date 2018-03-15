import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static class Node{
        public int key;
        public Node left;
        public Node right;

        public Node(int key){
            this.key = key;
        }

    }

    public static class Tree {
        public Node root;

        public Tree(){root = null;}


        private Node doAdd(Node node, int key){
            if (node == null) {
                return new Node(key);
            }
            if (key < node.key) {
                node.left = doAdd(node.left, key);
            } else if (key > node.key) {
                node.right = doAdd(node.right, key);
            }
            return node;
        }


        private Node findMin(Node node){
            if(node.left != null) return findMin(node.left);
            else return node;
        }


        private Node doDelete(Node node, int key){
            if(node == null) return null;
            if(key < node.key){
                node.left = doDelete(node.left, key);
                return node;
            }
            else if(key > node.key){
                node.right = doDelete(node.right,key);
                return node;
            }

            if(node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else{
                int minKey = findMin(node.right).key;
                node.key = minKey;
                node.right = doDelete(node.right, minKey);
                return node;
            }
        }


        public void add(int key){
            root = doAdd(root,key);}

        public void delete(int key){
            root = doDelete(root,key);
        }
    }


    public static void preOrderTraversal(Node node, BufferedWriter bw) throws IOException{
        if(node != null){
            bw.write(Integer.toString(node.key) + '\n');
            preOrderTraversal(node.left,bw);
            preOrderTraversal(node.right,bw);
        }
    }


    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        String s;
        Tree tree = new Tree();
        int keyForDelete = Integer.parseInt(br.readLine());
        br.readLine();
        while((s = br.readLine()) != null){
            tree.add(Integer.parseInt(s));
        }

        tree.delete(keyForDelete);
        preOrderTraversal(tree.root, bw);
        br.close();
        bw.close();
    }
}

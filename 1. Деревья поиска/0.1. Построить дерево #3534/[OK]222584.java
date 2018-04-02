import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main implements Runnable{

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


        public void add(int key){
            root = doAdd(root,key);}


    }


    public static void preOrderTraversal(Node node, BufferedWriter bw) throws IOException{
        if(node != null){
            bw.write(Integer.toString(node.key) + '\n');
            preOrderTraversal(node.left,bw);
            preOrderTraversal(node.right,bw);
        }
    }

    public void run(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("input.txt"));
            bw = new BufferedWriter(new FileWriter("output.txt"));
            String s;
            Set<Integer> set = new TreeSet<>();
            Tree tree = new Tree();
            while((s = br.readLine()) != null){
                tree.add(Integer.parseInt(s));
            }
            preOrderTraversal(tree.root, bw);
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        new Thread(null, new Main(),"", 64*1024*1024).start();
    }
}
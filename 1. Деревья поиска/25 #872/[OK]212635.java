import java.io.*;
import static java.lang.Math.abs;

public class Main {
    static boolean isRepeated;
    static int maxMark =  -1;
    static int amountOfNeccessaryVertexes = 0;
    static int neccessaryKey = 0;

    public static class Node{
        public int key;
        public Node left;
        public Node right;
        public int mark;
        public Node(int key){
            this.key = key;
            mark = 0;
        }

    }

    public static class Tree {
        public Node root;


        public Tree(){root = null;}


        private Node doAdd(Node node, int key){
            isRepeated = false;

            if (node == null) {
                return new Node(key);
            }
            int mark = node.mark;
            if (key < node.key) {
                node.left = doAdd(node.left, key);
                mark ++;
            } else if (key > node.key) {
                node.right = doAdd(node.right, key);
                mark --;
            }
            else{
                isRepeated = true;
                return node;
            }

            if(!isRepeated) {
                node.mark = mark;
            }
            return node;
        }


        private Node findMinVertex(Node node){
            if(node.left != null) return findMinVertex(node.left);
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
                int minKey = findMinVertex(node.right).key;
                node.key = minKey;
                node.right = doDelete(node.right, minKey);
                return node;
            }
        }


        public void add(int key){
            root = doAdd(root,key);
        }


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

    public static void findNeccessaryKey(Node node){
        if(node != null){
            findNeccessaryKey(node.left);
            if(abs(node.mark) == maxMark){
                if(amountOfNeccessaryVertexes == 0 && neccessaryKey == 0) {
                    neccessaryKey = node.key;
                    return;
                }
                else amountOfNeccessaryVertexes--;
            }
            findNeccessaryKey(node.right);
        }
    }


    public static void findMaxMark(Node node) {
        if (node != null) {
            if (abs(node.mark) == maxMark) amountOfNeccessaryVertexes++;
            if (abs(node.mark) > maxMark) {
                maxMark = abs(node.mark);
                amountOfNeccessaryVertexes = 1;
            }
            findMaxMark(node.left);
            findMaxMark(node.right);
        }
    }


    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("tst.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("tst.out"));
        String s;
        Tree tree = new Tree();
        while((s = br.readLine()) != null){
            tree.add(Integer.parseInt(s));
        }
        findMaxMark(tree.root);
        if(amountOfNeccessaryVertexes % 2 != 0) {
            amountOfNeccessaryVertexes /= 2;
            findNeccessaryKey(tree.root);
            tree.delete(neccessaryKey);
        }
        preOrderTraversal(tree.root, bw);
        br.close();
        bw.close();
    }
}
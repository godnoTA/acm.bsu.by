import java.io.*;
import java.util.*;

class Tree {
    ArrayList<Integer> order;
    static class Node {
        int key;
        Node left;
        Node right;
        public Node(int x){
            key = x;
        }
    }
    public Node root;

    public void insert(int x) {
        root = doInsert(root, x);
    }

    private static Node doInsert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = doInsert(node.left, x);
        } else if (x > node.key) {
            node.right = doInsert(node.right, x);
        }
        return node;
    }

    private void doLeftPreOrder(Node node){
        if (node != null) {
            order.add(node.key);
            if (node.left != null)
                doLeftPreOrder(node.left);
            if (node.right != null)
                doLeftPreOrder(node.right);
        }
        else return;
    }
    public void leftPreOrder(){
        order = new ArrayList<>();
        doLeftPreOrder(root);
    }

    public Node findMin(Node node){
        if (node.left != null)
            return findMin(node.left);
        else return node;
    }

    public Node delete(Node node, int key){
        if (node == null)
            return null;
        if (key < node.key){
            node.left = delete(node.left, key);
            return node;
        }
        else if (key > node.key){
            node.right = delete(node.right, key);
            return node;
        }
        if (node.left == null) {
            return node.right;
        }
        else if (node.right == null) {
            return node.left;
        }
        else {
            int minKey = findMin(node.right).key;
            node.key = minKey;
            node.right = delete(node.right, minKey);
            return node;
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Scanner scanner;
        int number;
        int key = 0;
        try{
            scanner = new Scanner(new File("input.txt"));
            if (scanner.hasNext())
                key = scanner.nextInt();
            while (scanner.hasNext()){
                number = scanner.nextInt();
                tree.insert(number);
            }
            tree.root  = tree.delete(tree.root, key);
            tree.leftPreOrder();
            try{
                FileWriter fileWriter = new FileWriter("output.txt");
                for (Integer i:tree.order){
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(i.toString());
                    stringBuilder.append("\r\n");
                    fileWriter.write(stringBuilder.toString());
                }
                fileWriter.flush();
            }
            catch(Exception e){}
        }
        catch(Exception e){}
    }
}
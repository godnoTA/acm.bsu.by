import java.io.*;
import java.util.*;

class Tree {
    ArrayList<Integer> order;
    static class Node {
        Integer key;
        Node left;
        Node right;
        public Node(Integer x){
            key = x;
        }
    }
    private Node root;

    public void insert(Integer x) {
        root = doInsert(root, x);
    }

    private static Node doInsert(Node node, Integer x) {
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
}

public class Main implements Runnable{
    public void run(){

        Tree tree = new Tree();
        Scanner scanner;
        Integer number;
        try{
            scanner = new Scanner(new File("input.txt"));
            while (scanner.hasNext()){
                number = scanner.nextInt();
                tree.insert(number);
            }
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
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
}
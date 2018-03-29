import java.io.*;
import java.util.*;

class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
        this.value = value;
    }
    public void print(PrintWriter pw){
        pw.print(value+"\r\n");
        if (left!=null) left.print(pw);
        if (right!=null) right.print(pw);
    }
}

class BinaryTree {
    public void add(Node node, int value) {
        if (value < node.value) {
            if (node.left != null) {
                add(node.left, value);
            } else {
                node.left = new Node(value);
            }
        } else if (value > node.value) {
            if (node.right != null) {
                add(node.right, value);
            } else {
                node.right = new Node(value);
            }
        }
    }
    public Node delete(Node node, int value){
        if(node==null)
            return null;
        if(value > node.value) {
            node.right = delete(node.right, value);
            return node;
        } else if (value < node.value) {
            node.left = delete(node.left, value);
            return node;
        }
        if (node.left==null)
            return node.right;
        else if (node.right==null)
            return node.left;
        else {
            int min = min(node.right).value;
            node.value=min;
            node.right=delete(node.right, min);
            return node;
        }

    }
    public Node min(Node node){
        if(node.left!=null){
            return min(node.left);
        }
        else return node;
    }
}

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    public void run() {
        BinaryTree binaryTree = new BinaryTree();
        ArrayList<Integer>arrayList = new ArrayList<>();
        Node number;
        try (Scanner sc = new Scanner(new FileReader("input.txt"))){
            int num = Integer.parseInt(sc.next());
            number = new Node(Integer.parseInt(sc.next()));
            arrayList.add(number.value);
            while (sc.hasNext()) {
                int next = Integer.parseInt(sc.next());
                if(!arrayList.contains(next))
                    binaryTree.add(number, next);
            }
            number = binaryTree.delete(number, num);
            File file = new File("output.txt");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                PrintWriter out = new PrintWriter(file.getAbsoluteFile());
                number.print(out);
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception ex) {System.out.println();}
    }
}
import java.io.*;
import java.util.*;

class Node {
    Node left;
    Node right;
    int value;
    String a="";

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
}

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    public void run() {
        BinaryTree binaryTree = new BinaryTree();
        Node number;
        try (Scanner sc = new Scanner(new FileReader("input.txt"))){
            number = new Node(Integer.parseInt(sc.next()));
            while (sc.hasNext()) {
                binaryTree.add(number, Integer.parseInt(sc.next()));
            }
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
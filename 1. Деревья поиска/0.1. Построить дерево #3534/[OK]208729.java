import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Node {
    Node left;
    Node right;
    int value;
    String a="";

    public Node(int value) {
        this.value = value;
    }
    public void print(PrintWriter pw){
        //System.out.println(value);
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

public class Main {
    public static void main(String[] args) {
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
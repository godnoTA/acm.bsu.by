import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(new File("input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt",false));
            if (input.hasNextLine()) {
                long key = input.nextLong();
                Tree der = new Tree(new Node(input.nextLong()));
                while (input.hasNextLong())
                    der.insert(input.nextLong(), der.root);
                der.remove(key);
                StraightLeft(der.root, bw);

            }
            input.close();
            bw.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void StraightLeft(Node k, BufferedWriter bw) throws IOException
    {
        if (k != null) {

            bw.write(Long.toString(k.key)+"\n");
            StraightLeft(k.left, bw);
            StraightLeft(k.right, bw);
        }
    }


}

class Tree {
    Node root;
    Tree(Node root) {
        this.root = root;
    }
    public void remove(long leaf) {
        if (root != null) {
            Node temp = root, parent = null;
            while (temp!=null) {
                if (temp.key > leaf) {
                    parent = temp;
                    temp = temp.left;
                } else if(temp.key<leaf) {
                    parent = temp;
                    temp = temp.right;
                }else{
                    break;
                }
            }
            if (temp==null){
                return;
            }
            if (parent==null){
                if (temp.right==null){
                    if (temp.left==null){
                        root=null;
                    }else{
                        root=temp.left;
                    }
                } else{
                    if (temp.left==null){
                        root=temp.right;
                    }else {
                        temp = temp.right;
                        parent = root;
                        if (temp.left == null) {
                            root.right = temp.right;
                            root.key=temp.key;
                        } else {
                            while (temp.left != null) {
                                parent = temp;
                                temp = temp.left;
                            }
                            parent.left = temp.right;
                            root.key = temp.key;
                        }
                    }
                }
            } else {

                if (temp.left == null) {
                    if (temp == parent.left) {
                        parent.left = temp.right;
                    } else {
                        parent.right = temp.right;
                    }
                } else if (temp.right == null) {
                    if (temp == parent.left) {
                        parent.left = temp.left;
                    } else {
                        parent.right = temp.left;
                    }

                } else {
                    Node mostLeft = temp.right;
                    parent = null;
                    while (mostLeft.left != null) {
                        parent = mostLeft;
                        mostLeft = mostLeft.left;
                    }
                    if (parent == null) {
                        temp.right = mostLeft.right;
                    } else {
                        parent.left = mostLeft.right;
                    }
                    temp.key = mostLeft.key;
                }
            }
        }
    }
    boolean insert(long key, Node node) {
        if (node.key == key)
            return false;
        if (key < node.key) {
            if (node.left != null) insert(key, node.left);
            else node.left = new Node(key);
        } else {
            if (node.right != null) insert(key, node.right);
            else node.right = new Node(key);
        }
        return true;
    }

}

class Node {
    long key;
    Node left;
    Node right;

    Node(long key) {
        this.key = key;
    }
}
import java.io.*;
import java.io.BufferedReader;

public class M0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(
                        new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter("output.txt");

            Tree tree = new Tree();
            String u = "";
            String k = "";
            int t;
            u = br.readLine();
            t = Integer.parseInt(u);
            br.readLine();
            k = br.readLine();
            while (k != null) {
                tree.root = tree.insert(tree.root, Integer.parseInt(k));
                k = br.readLine();
            }
            tree.root = tree.DelRec(tree.root, t);
            tree.straightleft(tree.root, pw);
            pw.close();
        }
}
class Node {
    public Node right;
    public Node left;
    public int key;
    public Node(int a) {
        this.key = a;
    }
}

class Tree {
    public Node root;
    public Node insert(Node node, int v) {
        if (node == null) {
            return new Node(v);
        }
        if (v < node.key) {
            node.left = insert(node.left, v);
        } else if (v > node.key) {
            node.right = insert(node.right, v);
        }
        return node;
    }
    public void straightleft(Node node,PrintWriter pw) {
        if (node != null) {
            pw.println(node.key);
            straightleft(node.left, pw);
            straightleft(node.right, pw);
        }
    }
    public Node SearchRec(Node node, int v){
        if((node==null)|(node.key==v)){
            return node;
        }
        else if(v<node.key){
            return SearchRec(node.left, v);
        }
        return SearchRec(node.right, v);
    }
    public Node DelRec(Node node, int v){
        int min_key;
        if(node==null){
            return node;
        }
        if(v>node.key){
            node.right=DelRec(node.right, v);
            return node;
        }
        if(v<node.key){
            node.left=DelRec(node.left, v);
            return node;
        }
        if(node.left==null) {
            return node.right;
        }
        else if(node.right==null){
            return node.left;
        }
        else {
            min_key=FindMin(node.right).key;
            node.key=min_key;
            node.right=DelRec(node.right, min_key);
            return node;
        }
    }
    public Node FindMin(Node node){
        if(node.left!=null){
            return FindMin(node.left);
        }
        else
            return node;
    }
}

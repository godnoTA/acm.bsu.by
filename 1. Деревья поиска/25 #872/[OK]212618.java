import java.io.*;
import java.io.BufferedReader;

public class M1 {
    public static void main(String[] args) throws IOException{

        BufferedReader br =
                new BufferedReader(
                        new FileReader("tst.in"));
        PrintWriter pw = new PrintWriter("tst.out");

        Tree tree = new Tree();
        String k = "";
        k = br.readLine();
        while (k != null) {
            tree.root = tree.insert(tree.root, Integer.parseInt(k));
            k = br.readLine();
        }

        tree.childCount(tree.root);
        tree.childCompare(tree.root);
        tree.searchMax(tree.root);
        if (tree.countKol % 2 == 0) {
            tree.straightleft(tree.root, pw);
        }
        else {
            tree.halfCount=tree.countKol/2;
            tree.middleNodeKey(tree.root);
            tree.root = tree.DelRec(tree.root, tree.middleKey);
            tree.straightleft(tree.root, pw);
        }
        pw.close();
    }
}

class Node {
    public Node right;
    public Node left;
    public int key;
    public int count;
    public int countCompare;
    public Node(int a) {
        this.key = a;
    }
}

class Tree {
    public Node root;
    public int max = -1;
    public int countKol;
    public int middleKey;
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
    public Node DelRec(Node node, int v){
        int min_key;
        if(node==null){
            return node;
        }
        if(v<node.key){
            node.left=DelRec(node.left, v);
            return node;
        }
        else if(v>node.key){
            node.right=DelRec(node.right, v);
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
    public int childCount(Node node){
        if(node!=null){
            if(node.left!=null){
                node.count+=childCount(node.left)+1;
            }
            if(node.right!=null){
                node.count+=childCount(node.right)+1;
            }
            return node.count;
        }
        return 0;
    }
    public void childCompare(Node node){
        if(node!=null){
            if((node.left==null)&&(node.right==null)){
                node.countCompare=0;
            }
            else if((node.left!=null)&&(node.right!=null)){
                node.countCompare= Math.abs(node.left.count-node.right.count);
            }
            else if(node.left!=null){
                node.countCompare=node.left.count+1;
            }
            else{
                node.countCompare=node.right.count+1;
            }
            childCompare(node.left);
            childCompare(node.right);
        }
    }
    public void searchMax(Node node){
        if(node!=null){
            if(node.countCompare>max){
                max=node.countCompare;
                countKol = 0;
            }
            if(node.countCompare==max){
                countKol++;
            }
            searchMax(node.left);
            searchMax(node.right);
        }
    }
    public int halfCount;

    public void middleNodeKey(Node node){
        if(node!=null){
            middleNodeKey(node.left);
            if(node.countCompare==max){
                if(halfCount==0){
                    middleKey=node.key;
                }
                halfCount--;
            }
            middleNodeKey(node.right);
        }
    }
}
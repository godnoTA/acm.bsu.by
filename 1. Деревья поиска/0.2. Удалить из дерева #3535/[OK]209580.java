import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;
import static java.lang.System.out;

class Node{
    long  key;
    Node left;
    Node right;
}

class MyTree{
    private Node head = null;

    public Node delete(Node tmp, long key){
        Node root = tmp;
        Node currentParent = null;
        boolean currentParentSon = true;
        while(tmp.key != key){// find
            if(tmp.key > key){
                if(tmp.left != null) {currentParent = tmp; tmp = tmp.left;currentParentSon = false; } else return root;
            }
            else if(tmp.right != null){currentParent = tmp; tmp = tmp.right; currentParentSon = true; } else return root;
        }
        if(tmp.left == null && tmp.right == null&& root.key == tmp.key) { //if key == root
            root = null;
            return root;
        }
        if(tmp.left != null && tmp.right == null && root.key == tmp.key){// -||-
            root = root.left;
            return root;
        }
        if(tmp.left == null && tmp.right != null && root.key == tmp.key){// -||-
            root = root.right;
            return root;
        }
        if(tmp.right != null && tmp.left != null && root.key == tmp.key){// -||-
            Node buff = root.right;
            Node currentParentBuff = root;
            if(buff.left == null){
                root.right = buff.right;
                buff = null;
                return root;
            }
            else{
                currentParentBuff = root.right;
                buff = buff.left;
                while(buff.left != null){
                    buff = buff.left;
                    currentParentBuff = currentParentBuff.left;
                }
                if(buff.right != null){
                    currentParentBuff.left = buff.right;
                    root.key = buff.key;
                    buff = null;
                    return root;
                } else{
                    root.key = buff.key;
                    currentParentBuff.left = null;
                    buff = null;
                    return root;
                }
            }
        }
        if(tmp.left == null && tmp.right == null) {//key is list
            tmp = null;
            if(currentParentSon)  currentParent.right = null;
            else currentParent.left = null;
            return root;
        }
        if(tmp.left != null && tmp.right == null){//key have only left son

            if(currentParentSon)  currentParent.right = tmp.left;
            else currentParent.left = tmp.left;
            tmp = null;
            return root;
        }
        if(tmp.left == null && tmp.right != null){// only right son

            if(currentParentSon)  currentParent.right = tmp.right;
            else currentParent.left = tmp.right;
            tmp = null;
            return root;
        }
        if(tmp.left != null && tmp.right != null){// both sons
            Node buff = tmp.right;
            Node currentParentBuff = tmp;
            if(buff.left == null){
                tmp.right = buff.right;
                tmp.key = buff.key;
                return root;
            }
            else{
                currentParentBuff = tmp.right;
                buff = buff.left;
                while(buff.left != null){
                    buff = buff.left;
                    currentParentBuff = currentParentBuff.left;
                }
                if(buff.right != null){
                    currentParentBuff.left = buff.right;
                    tmp.key = buff.key;
                    buff = null;
                    return root;
                } else{
                    tmp.key = buff.key;
                    currentParentBuff.left = null;
                    buff = null;
                    return root;
                }
            }
        }
        return root;

    }

    public Node insert(Node root, long x)
    {
        if(root==null) {
            Node temp = new Node();
            temp.key = x;
            temp.left = null;
            temp.right = null;
            return temp;
        }
        if(root.key < x){
            root.right = insert(root.right, x);
        }
        if(root.key > x){

            root.left = insert(root.left, x);
        }
        return root;
    }
    void travers(Node tmp,Stack<BigInteger> stack){
        if(tmp == null) return;
        BigInteger n = new BigInteger("" + tmp.key);
        stack.push(n);
        if(tmp.left != null) travers(tmp.left,stack);
        if(tmp.right!= null) travers(tmp.right,stack);
    }
    public void add(long value){
        head = insert(head, value);
    }
    public void left_front(Stack<BigInteger> stack){
        travers(head,stack);
    }
    public void remove(long key){
        head = delete(head, key);
    }
}

public class Algorithms {
    public static void main(String[] args) {
        MyTree Tree = new MyTree();
        Stack<BigInteger> stack = new Stack<BigInteger>();
        Stack<BigInteger> stack1 = new Stack<BigInteger>();
        try {
            FileReader fr = new FileReader("input.txt");
            FileWriter fw = new FileWriter("output.txt");
            Scanner scan = new Scanner(fr);
            long a = scan.nextLong();
            while(scan.hasNext()){
                Tree.add(scan.nextLong());
            }
            Tree.remove(a);
            Tree.left_front(stack);
            while(!stack.empty()) stack1.push(stack.pop());
            while(!stack1.empty()) fw.write("" + stack1.pop() + "\n");
            fw.close();
        } catch (IOException ex) {
            out.println("NANI??");
        }
    }
}


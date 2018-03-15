import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import static java.lang.System.out;

class Node{
    long  key;
    Node left;
    Node right;
}


class MyTree{
    private Node head = null;

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

}

public class Algorithms {
    public static void main(String[] args) {
        MyTree Tree = new MyTree();
        Stack<BigInteger> stack = new Stack<BigInteger>();
        Stack<BigInteger> stack1 = new Stack<BigInteger>();
        try {
            FileReader fr = new FileReader("input.txt");
            FileWriter fw = new FileWriter("output.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            Scanner scan = new Scanner(fr);
            while(scan.hasNext()){
                Tree.add(scan.nextLong());
            }
            Tree.left_front(stack);
            while(!stack.empty()) stack1.push(stack.pop());
            while(!stack1.empty()) fw.write("" + stack1.pop() + "\n");
            fw.close();
        } catch (IOException ex) {
            out.println("NANI??");
        }

    }
}


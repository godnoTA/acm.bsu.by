import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    private static PrintStream ps;
    private static Scanner sc;
    static {
        try {
            ps = new PrintStream(new File("out.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    static {
        try{
            sc = new Scanner(new File("in.txt"));
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    static class Node {
        int key;
        Node left;
        Node right;
        int leftNumber;
        int rightNumber;

        public Node(int key) {
            this.key = key;
            this.left=null;
            this.right=null;
        }
    }
    private Node root;
    public void input() {
        int str;
        while (sc.hasNext()) {
            str = sc.nextInt();
            insert(str);
        }
        sc.close();
    }
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
    private int countLeftNumber(Node node){
        int l;
        if(node.left==null || node==null){
            l=0;
        }
        else l=countLeftNumber(node.left)+countRightNumber(node.left)+1;
        node.leftNumber=l;
        return l;
    }
    private int countRightNumber(Node node){
        int r;
        if(node.right==null || node==null){
            r=0;
        }
        else r=countLeftNumber(node.right)+countRightNumber(node.right)+1;
        node.rightNumber=r;
        return r;
    }
    public void countNumbers(){
        countLeftNumber(root);
        countRightNumber(root);
    }
    public List<Integer> keys;
    public Task1(){
        keys=new ArrayList<Integer>();
    }

    private Node addKeys(Node node){
        if(node==null){
            return node;
        }
        if(Math.abs(node.rightNumber-node.leftNumber)==1){
            keys.add(node.key);
        }
        node.left=addKeys(node.left);
        node.right=addKeys(node.right);
        return node;
    }
    public void getKeys(){
        addKeys(root);
    }

    public int getKeyToDelete(){
        Collections.sort(keys);
        return keys.get(keys.size()/2);
    }
    private Node minimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }
    public Node delete(Node x, int z) {
        if (x == null) {
            return x;
        }
        if (z < x.key) {
            x.left = delete(x.left, z);
        } else if (z > x.key) {
            x.right = delete(x.right, z);
        } else if (x.left != null && x.right != null) {
            x.key = minimum(x.right).key;
            x.right = delete(x.right, x.key);
        } else {
            if (x.left != null) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }
        return x;
    }

    public void output(Node x) {
        ps.println(x.key);
        if (x.left != null) {
            output(x.left);
        }
        if (x.right != null) {
            output(x.right);
        }
    }

    public static void main(String[] args) throws IOException {
        Task1 t=new Task1();
        t.input();
        t.countNumbers();
        t.getKeys();
        if(t.keys.size()%2==1){
            t.root=t.delete(t.root, t.getKeyToDelete());
        }
        t.output(t.root);
    }
}
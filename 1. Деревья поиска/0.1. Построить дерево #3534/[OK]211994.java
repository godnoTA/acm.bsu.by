import java.io.*;
import java.util.Scanner;

public class Tree {
    static class Node{
        int key;
        Node left,right;

        public Node(int key){
            this.key=key;
        }
    }

    private Node root=null;


    public boolean add (int key){
        Node x = root;
        Node y=null;
        while (x!=null){
            if (x.key==key){
                return false;
            }else{
                y=x;
                if (x.key<key){
                    x=x.right;
                }else{
                    x=x.left;
                }
            }
        }
        Node newNode=new Node(key);
        if (y==null){
            root=newNode;
        }else{
            if (y.key>newNode.key){
                y.left=newNode;
            }else{
                y.right=newNode;
            }
        }
        return true;
    }

    public void remove(int k) {
        Node x = root, y = null;
        while (x != null) {
            int cmp = Double.compare(k,x.key);
            if (cmp == 0) {
                break;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        if (x == null) {
            return;
        }
        if (x.right == null) {
            if (y == null) {
                root = x.left;
            } else {
                if (x == y.left) {
                    y.left = x.left;
                } else {
                    y.right = x.left;
                }
            }
        } else {
            Node leftMost = x.right;
            y = null;
            while (leftMost.left != null) {
                y = leftMost;
                leftMost = leftMost.left;
            }
            if (y != null) {
                y.left = leftMost.right;
            } else {
                x.right = leftMost.right;
            }
            x.key = leftMost.key;
        }
    }
    FileWriter fw;
    private void printt(Node x)throws IOException{
        if (x==null){
            return ;
        }
        fw.write(String.valueOf(x.key)+"\n");
        fw.flush();
        printt(x.left);
        printt(x.right);
    }

    public void print(String s)throws IOException{
        fw=new FileWriter(s,false);
        printt(root);
    }

    public static void main(String[] args) {
        Tree tr=new Tree();
        String in = "input.txt";
        String out = "output.txt";
        try(FileInputStream fis=new FileInputStream(in)){
            Scanner sc=new Scanner(fis);
            while (sc.hasNextLine()){
                tr.add(Integer.parseInt(sc.nextLine()));
            }
            tr.print(out);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

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

    public void remove(int inf) {
        if (root != null) {
            Node temp = root, parent = null;
            while (temp!=null) {
                if (temp.key > inf) {
                    parent = temp;
                    temp = temp.left;
                } else if(temp.key<inf) {
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
            int dest=Integer.parseInt(sc.nextLine());
            sc.nextLine();
            while (sc.hasNextLine()){
                tr.add(Integer.parseInt(sc.nextLine()));
            }
            tr.remove(dest);
            tr.print(out);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

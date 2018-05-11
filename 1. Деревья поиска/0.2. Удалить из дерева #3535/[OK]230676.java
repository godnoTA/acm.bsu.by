import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Tree{
    class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){ this.value = value; left = null; right = null; }
    }
    Node root;
    public void delete(Integer value){
        if(root == null) return;
        int compare = value.compareTo(root.value);
        if (compare>0) {delete(root, root.right, value); return; }
        else if (compare<0){ delete(root, root.left, value); return;}

        if (root.left == null) {
            if (root.right == null) root = null;
            else root = root.right;
        }
        else {
            if (root.right == null) {
                if (root.left == null) root = null;
                else root = root.left;
            }
            else {
                Node buf;
                if (root.right.left == null){ buf = root.right; root.right = buf.right; }
                else buf = deleteMinChild(root.right);
                root.value = buf.value;
            }
        }
        return;
    }

    public void insert(int value){
        if (root == null) root = new Node(value);
        else insert(root, value);
    }
    private void insert(Node root, Integer value){
        int compare = value.compareTo(root.value);
        if (compare>0) if (root.right == null) root.right = new Node(value);
        else insert(root.right, value);
        else if (compare<0) if (root.left == null)root.left = new Node(value);
        else insert(root.left, value);
    }
    public void delete(Node father, Node root, Integer value){
        if (root == null) return;
        int compare = value.compareTo(root.value);
        if (compare>0){ delete(root, root.right, value); return; }
        else if (compare<0){ delete(root, root.left, value); return; }
        Node buf;
        if (root.right != null) {
            if (root.left == null) {
                if (father.right == root) father.right = root.right;
                else if (father.left == root) father.left = root.right;
            }
            else {
                if (root.right.left == null) {
                    buf = root.right;
                    root.right = buf.right;
                }
                else buf = deleteMinChild(root.right);
                root.value = buf.value;
            }
        }
        else {
            if (father.right == root) father.right = root.left;
            else if (father.left == root) father.left = root.left;
        }
        return;
    }
    private Node deleteMinChild(Node element){
        if (element.left.left == null){
            Node find = element.left;
            element.left = element.left.right;
            return find;
        }
        return deleteMinChild(element.left);
    }
    public void travPreorder(FileWriter writer){travPreorder(root, writer);}
    private void travPreorder(Node root, FileWriter writer){
        if (root == null) return;
        try {
            String lineSeparator = System.getProperty("line.separator");
            writer.write(Integer.toString(root.value) + lineSeparator);
            travPreorder(root.left, writer);
            travPreorder(root.right, writer);
        }
        catch (IOException io){}
    }
}


public class Main implements Runnable{

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    public void run(){
        int x=0;
        Scanner scanner=null;
        Tree tree = new Tree();
        try{
            File file = new File("input.txt");
            scanner = new Scanner(file);
            x = scanner.nextInt();
            scanner.nextLine();
            while (scanner.hasNext()) {
                tree.insert(scanner.nextInt());
            }
        }
        catch (IOException io) {}
        tree.delete(x);
        File file = new File("output.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.flush();
            tree.travPreorder(writer);
            writer.close();
        }
        catch (IOException e){}
    }
}
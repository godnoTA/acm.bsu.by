import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Tree{
    class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){this.value=value; left=null; right=null;}
    }
    Node root;
    private void addVertex(Node root,Integer value){
        int compare=value.compareTo(root.value);
        if(compare>0) if(root.right==null) root.right=new Node(value);
        else addVertex(root.right,value);
        else if(compare<0) if(root.left==null)root.left=new Node(value);
        else addVertex(root.left,value);
    }
    public void travPreorder(FileWriter writer){ travPreorder(root, writer);}
    private void travPreorder(Node root, FileWriter writer){
        if(root==null) return;
        try {
            String lineSeparator=System.getProperty("line.separator");
            writer.write(Integer.toString(root.value)+lineSeparator);
            travPreorder(root.left, writer);
            travPreorder(root.right, writer);
        }catch(IOException io){}
    }
    public void addVertex(int value){
        if(root==null) root=new Node(value);
        else addVertex(root,value);
    }
}


public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }


    public void run(){

        Tree tree = new Tree();
        Scanner scanner=null;
        try {
            File file = new File("input.txt");
            scanner = new Scanner(file);
            while (scanner.hasNext()){
                tree.addVertex(scanner.nextInt());
            }
        } catch (IOException io) {}
        File file=new File("output.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.flush();
            tree.travPreorder(writer);
            writer.close();
        }catch(IOException e){}
    }
}
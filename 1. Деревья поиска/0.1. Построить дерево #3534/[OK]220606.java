import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tree implements Runnable{
    public static void main(String[] args){
        new Thread(null, new Tree(), "", 256 * 1024 * 1024).start();
    }

    public void run(){
        Tree tree=new Tree();
        try {
            print(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tree(long value){
        root=new Node(value);
    }

    public Tree(){ }

    public void add(long value) {
        if (root == null) {
            root = new Node(value);
        }
        else
        {
            addTo(root, value);
        }
    }

    private void addTo(Node node, long value)
    {
        if ((value-node.data)< 0) {
            if (node.getLeft() == null) {
                node.left = new Node(value);
            }
            else {
                addTo(node.left, value);
            }
        }
        else if(value==node.data){ return; }
        else
        {
            if (node.getRight() == null) {
                node.right = new Node(value);
            }
            else {
                addTo(node.right, value);
            }
        }
    }


    static void preOrderHelper(Tree tree){
        FileWriter fr = null;
        try {
            fr = new FileWriter(new File("output.txt"));
            preOrder(tree.root,fr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void preOrder(Node root, FileWriter fr) throws IOException {
        if (root!=null) {
            fr.write(root.getData()+"\n");
            preOrder(root.left,fr);
            preOrder(root.right,fr);
        }
    }

    static void print(Tree tree) throws IOException{
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);
        while(scan.hasNextInt()) {
            tree.add(scan.nextInt());
        }
        preOrderHelper(tree);
    }

    class Node {

        public Node(long value){
            data=value;
        }

        public long getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setData(long data) {
            this.data = data;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        private long data;
        private Node left;
        private Node right;
    }

    private Node root;
}
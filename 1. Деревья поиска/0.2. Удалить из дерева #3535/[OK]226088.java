import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Tree{
    static class Node{
        int key;
        Node left;
        Node right;

        public Node(int key){this.key=key;}
    }
    Node root;

    public void insert(int x){
        if (root==null){
            root=new Node(x);
        }
        Node temp = root;
        while (temp!=null){
            if(x<temp.key){
                if(temp.left!=null) temp=temp.left;
                else {
                    temp.left = new Node(x);
                    return;
                }
            }
            else if(x>temp.key){
                if(temp.right!=null) temp=temp.right;
                else{
                    temp.right=new Node(x);
                    return;
                }
            }
            else return;
        }
    }

    public Node minNode(Node node){
        if(node.left!=null)
            return minNode(node.left);
        else return node;
    }

    public Node delete_recursively(Node node, int x) {
        if (node == null)
            return node;
        if (x < node.key) {
            node.left = delete_recursively(node.left, x);
            return node;
        } else if (x > node.key) {
            node.right = delete_recursively(node.right, x);
            return node;
        }
        if (node.left == null)
            return node.right;
        else if (node.right == null)
            return node.left;
        else {
            int minKey = minNode(node.right).key;
            node.key=minKey;
            node.right=delete_recursively(node.right,minKey);
            return node;
        }
    }

    public void preOrder(Node node, ArrayList arrayList){
        if (node == null) return;
        arrayList.add(node.key);
        if(node.left!=null) preOrder(node.left, arrayList);
        if (node.right!=null) preOrder(node.right, arrayList);
    }
}

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run()
     {
        Tree tree=new Tree();
        Scanner scanner;
        int fileDelete=0;
        int fileKey;
        try{
            scanner=new Scanner(new File("input.txt"));
        }
        catch (java.io.FileNotFoundException ex){
            System.out.println("Файл не найден!");
            return;
        }
        if(scanner.hasNext()){
            fileDelete=scanner.nextInt();
        }
        while (scanner.hasNext()){
            fileKey=scanner.nextInt();
            tree.insert(fileKey);
        }
        tree.root=tree.delete_recursively(tree.root, fileDelete);
        try (FileWriter writer = new FileWriter("output.txt", false)) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            tree.preOrder(tree.root, arrayList);
            for (int i = 0; i < arrayList.size(); i++) {
                writer.append(arrayList.get(i).toString() + "\r\n");
            }
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
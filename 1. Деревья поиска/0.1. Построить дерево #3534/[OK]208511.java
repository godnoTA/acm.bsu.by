import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Tree {
    static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }
    }
     Node root;

    public void insert(int x) {
        if (root == null) {
            root=new Node(x);
        }
        Node temp = root;
        while(temp!=null){
            if(x>temp.key){
                if(temp.right==null) {
                    temp.right = new Node(x);
                    return;
                }
                else temp=temp.right;
            }
            else if(x<temp.key)
            {
                if(temp.left==null){
                    temp.left=new Node(x);
                    return;
                }
                else temp=temp.left;
            }
            else return;
       }
    }

    public void preOrder(Node node, ArrayList arrayList){
        if (node == null) return;
        arrayList.add(node.key);
        if(node.left!=null) preOrder(node.left, arrayList);
        if (node.right!=null) preOrder(node.right, arrayList);
    }
}

    public class Main {
        public static void main(String[] args) {
            Tree tree = new Tree();
            Scanner scanner;
            int fileKey;
            try {
                scanner = new Scanner(new File("input.txt"));
            } catch (java.io.FileNotFoundException ex) {
                System.out.print("Файл не найден!");
                return;
            }
            while (scanner.hasNext()) {
                fileKey = scanner.nextInt();
                tree.insert(fileKey);
            }
            try (FileWriter writer = new FileWriter("output.txt", false)) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                tree.preOrder(tree.root, arrayList);
                    for (int i = 0; i < arrayList.size(); i++) {
                        writer.append(arrayList.get(i).toString() + "\r\n");
                    }
                    writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

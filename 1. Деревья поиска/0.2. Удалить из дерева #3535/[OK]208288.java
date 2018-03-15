import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        int keyToDelete = 0;
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка! Файл отсутствует");
            return;
        }
        if (scanner.hasNext()){
            keyToDelete = scanner.nextInt();
        }
        while (scanner.hasNext()) {
            tree.insert(Integer.parseInt(scanner.next()));
        }
        FileWriter fout = null;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            tree.root = tree.delete(tree.root,keyToDelete);
            tree.PreOrder(tree.root,bufferedWriter);
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }
}

class Tree {
    static class Node {
        int key;
        Node left;
        Node right;
        public Node(int i) {
            key = i;
        }
    }

    public Node root;


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
    public void PreOrder(Node t, BufferedWriter fileWriter) throws IOException   { //рекурсивная функция
        if (t == null)
            return;
        else {
            Integer temp = t.key;
            fileWriter.write(temp.toString() + "\n");
            PreOrder(t.left, fileWriter);
            PreOrder(t.right, fileWriter);
        }
    }
    public Node findMin(Node v){
        if (v.left != null)
            return findMin(v.left);
        else return v;

    }
    public Node delete(Node v, int x){
        if (v == null)
            return null;
        if (x < v.key) {
            v.left = delete(v.left, x);
            return v;
        }
        if (x > v.key) {
            v.right = delete(v.right, x);
            return v;
        }

        if (v.left == null) {
            return v.right;
        }
        else if (v.right == null) {
            return v.left;
        }
        else {
            int min = findMin(v.right).key;
            v.key = min;
            v.right = delete(v.right, min);
            return v;
        }
    }
}
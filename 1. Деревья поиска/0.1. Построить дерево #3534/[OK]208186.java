import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class BinaryTree {
    private Node root;
    public static class Node{
        private Node left;
        private Node right;
        private long key;
        public Node(long key){
            this(key, null, null);
        }
        public Node(long key, Node left, Node right){
            this.key = key;
            this.left = left;
            this.right = right;
        }
        public void add(long key){
            if (key < this.key){
                if (this.left == null){
                    left = new Node(key);
                }
                else left.add(key);
            }
            if (key > this.key){
                if (this.right == null){
                    right = new Node(key);
                }
                else right.add(key);
            }
        }

        public long getKey() {
            return key;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }
    public void add(long key){
        if (root == null){
            root = new Node(key);
        }
        else root.add(key);
    }

    public Node getRoot() {
        return root;
    }
}



public class Main {

    private static BinaryTree fromFile() throws Exception{
        Scanner scanner = new Scanner(new File("input.txt"));
        BinaryTree tree = new BinaryTree();
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            Long num = Long.parseLong(line);
            tree.add(num);
        }
        return tree;
    }




    private static Path getOutput() throws Exception{
        Path output;
        if (Files.notExists(Paths.get("output.txt"))){
            output = Files.createFile(Paths.get("output.txt"));
        }
        else output = Paths.get("output.txt");
        return output;
    }

    private static void printDirectTreeTraversing(BinaryTree.Node root, PrintWriter writer){
        writer.println(root.getKey());
        if (root.getLeft() != null){
            printDirectTreeTraversing(root.getLeft(), writer);
        }
        if (root.getRight() != null){
            printDirectTreeTraversing(root.getRight(), writer);
        }

    }

    private static void printDirectTreeTraversing(BinaryTree.Node root, Path path) throws Exception{
        PrintWriter writer = new PrintWriter(path.toFile());
        printDirectTreeTraversing(root, writer);
        writer.close();
    }

    public static void main(String[] args) throws Exception {

        BinaryTree tree = fromFile();
        printDirectTreeTraversing(tree.getRoot(), Paths.get("output.txt"));
        //printTreeSum(tree);
    }


}
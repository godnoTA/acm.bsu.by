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

    private static long treeSum(BinaryTree.Node root){
        if (root == null) return 0;
        if (root.getLeft() == null && root.getRight() == null) return root.getKey();
        else {
            return root.getKey() + treeSum(root.getLeft()) + treeSum(root.getRight());
        }
    }

    private static void printTreeSum(BinaryTree tree) throws Exception {
        long sum = treeSum(tree.getRoot());
        FileWriter writer;
        Path output = getOutput();
        writer = new FileWriter(output.toFile());
        writer.write(Long.toString(sum));
        writer.close();
    }


    private static Path getOutput() throws Exception{
        Path output;
        if (Files.notExists(Paths.get("output.txt"))){
            output = Files.createFile(Paths.get("output.txt"));
        }
        else output = Paths.get("output.txt");
        return output;
    }

    public static void main(String[] args) throws Exception {

        BinaryTree tree = fromFile();
        printTreeSum(tree);
    }


}
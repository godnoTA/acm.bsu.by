import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class Tree {
    static class Vertex {
        public int key;
        public Vertex left;
        public Vertex right;
        public Vertex(int key) {
            this.key = key;
        }
    }
    public Vertex root;
    public void add(int x) {
        root = insert(root, x);
    }
    private static Vertex insert(Vertex ver, int x) {
        if (ver == null) {
            return new Vertex(x);
        }
        if (x < ver.key) {
            ver.left = insert(ver.left, x);
        } else if (x > ver.key) {
            ver.right = insert(ver.right, x);
        }
        return ver;
    }
    public void bypass() throws FileNotFoundException {
        PrintStream ps = new PrintStream("output.txt");
        bypass(root, ps);
        ps.close();
    }
    private void bypass(Vertex ver, PrintStream ps){
        if(ver!=null){
            ps.println(ver.key);
            bypass(ver.left, ps);
            bypass(ver.right, ps);
        }
    }
    public void deleteKey(int x) {
        root = recursiveDelete(root, x);
    }

    private Vertex recursiveDelete(Vertex root, int x) {
        if (root == null) {
            return null;
        }
        if (x < root.key) {
            root.left = recursiveDelete(root.left, x);
            return root;
        } else if (x > root.key) {
            root.right = recursiveDelete(root.right, x);
            return root;
        }
        if (root.left == null) {
            return root.right;
        } else {
            if (root.right == null)
                return root.left;
            else {
                int minKey = findMin(root.right).key;
                root.key = minKey;
                root.right = recursiveDelete(root.right, minKey);
                return root;
            }
        }
    }

    private Vertex findMin(Vertex root) {
        if (root.left != null)
            return findMin(root.left);
        else
            return root;
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Tree tree = new Tree();
        int key = sc.nextInt();
        while (sc.hasNext()){
            tree.add(sc.nextInt());
        }
        tree.deleteKey(key);
        tree.bypass();
    }
}
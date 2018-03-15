import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileInputStream("input.txt"));
            PrintStream ps = new PrintStream(new FileOutputStream("output.txt"));

            Tree tree = new Tree();

            while (sc.hasNextInt()) {
                tree.insert(sc.nextInt());
            }

            ArrayList<Long> list = new ArrayList<>();

            tree.printRoot(tree.getRoot(),list);

            for (Long item : list) {
                ps.println(item);
            }
            ps.close();
            sc.close();
        } catch (IOException e) {
            System.out.println("IOError");
        }
    }
}

class Leaf {

    public long key;
    public Leaf left;
    public Leaf right;

    public Leaf(long value) {
        this.key = value;
        this.left = null;
        this.right = null;
    }
}

class Tree {

    private Leaf root;

    public Tree() {
        root = null;
    }

    public void insert(int x) {
        root = doInsert(root, x);
    }

    private static Leaf doInsert(Leaf leaf, int x) {
        if (leaf == null) {
            return new Leaf(x);
        }
        if (x < leaf.key) {
            leaf.left = doInsert(leaf.left, x);
        } else if (x > leaf.key) {
            leaf.right = doInsert(leaf.right, x);
        }
        return leaf;
    }

    public void printRoot(Leaf root, ArrayList<Long> arr) throws IOException {
        if (root != null) {
            arr.add(root.key);
            printRoot(root.left, arr);
            printRoot(root.right, arr);
        }
    }

    public Leaf getRoot() {
        return root;
    }

}
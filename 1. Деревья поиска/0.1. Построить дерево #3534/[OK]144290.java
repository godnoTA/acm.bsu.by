/**
 * Created by vlad on 01.04.2016.
 */


import java.io.*;
import java.util.ArrayList;

/**
 * Created by vlad on 01.04.2016.
 */
class Leaf {
    private int key;
    private Leaf left;
    private Leaf right;

    public Leaf(int value) {
        this.key = value;
        this.left = null;
        this.right = null;

    }
    public int getKey() {
        return key;
    }

    public Leaf getLeft() {
        return left;
    }

    public void setLeft(Leaf left) {
        this.left = left;
    }

    public Leaf getRight() {
        return right;
    }

    public void setRight(Leaf right) {
        this.right = right;
    }

}
class Treep {
    private Leaf root;
    public Leaf getRoot() {
        return root;
    }
    public Treep() {
        root = null;
    }

    public void insert(int x) {
        if (root == null) {
            root = new Leaf(x);
            return;
        }

        Leaf leaf = root;
        while (true) {
            if (x < leaf.getKey()) {
                if (leaf.getLeft() == null) {
                    leaf.setLeft(new Leaf(x));
                    return;
                } else {
                    leaf = leaf.getLeft();
                }
            } else if (x > leaf.getKey()) {
                if (leaf.getRight() == null) {
                    leaf.setRight(new Leaf(x));
                    return;
                } else {
                    leaf = leaf.getRight();
                }
            } else {
                return;
            }
        }
    }

    public void Print(Leaf leaf, ArrayList<Integer> arr) throws IOException {
        if (leaf != null) {
            arr.add(leaf.getKey());
            Print(leaf.getLeft(), arr);
            Print(leaf.getRight(), arr);
        }
    }
}
public class random01 {
    public static BufferedReader br;
    public static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(new File("input.txt").getAbsoluteFile()));
        bw = new BufferedWriter(new FileWriter(new File("output.txt")));

        Treep tree = new Treep();
        String string;
        while ((string = br.readLine()) != null) {
            String[] strings = string.split("\\s");
            for (String element : strings) {
                if (!element.equals("")) {
                    tree.insert(Integer.parseInt(element));
                }
            }
        }
        br.close();
        ArrayList<Integer> arr = new ArrayList<>();
        tree.Print(tree.getRoot(), arr);
        for (int i : arr) {
            bw.write(i + "\n");
        }
        bw.close();
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class Tree {
    static class Vertex {
        private long key;
        private Vertex left;
        private Vertex right;
        private Vertex parent;

        private Vertex(long key, Vertex parent) {
            this.key = key;
            this.parent = parent;
        }

    }

    private long i;
    private Vertex root;

    public Tree() {
        root = null;
    }

    public void add(long x) {
        root = insert(root, x, null);
    }

    private Vertex insert(Vertex ver, long x, Vertex parent) {
        if (ver == null) {
            return new Vertex(x, parent);
        }
        if (x < ver.key) {
            ver.left = insert(ver.left, x, ver);
        } else if (x > ver.key) {
            ver.right = insert(ver.right, x, ver);
        }
        return ver;
    }

    public long count() {
        count(root);
        return i;
    }

    private void count(Vertex ver) {
        if (ver != null) {
            if (ver.left == null && ver.right == null) {
                i++;
            } else {
                count(ver.left);
                count(ver.right);
            }
        }
    }

    public void inBypass() {
        i = i / 2 + 1;
        inBypass(root);
    }

    private void inBypass(Vertex ver) {
        if (ver != null) {
            inBypass(ver.left);
            if (ver.left == null && ver.right == null) {
                i--;
                if (i == 0) {
                    if (ver.parent == null)
                        delete(ver.key);
                    else
                        delete(ver.parent.key);
                }
            }
            inBypass(ver.right);
        }
    }

    private void delete(long key) {
        root = recursiveDelete(root, key);
    }

    private Vertex recursiveDelete(Vertex root, long x) {
        if (root == null) {
            return null;
        }
        if (x < root.key) {
            root.left = recursiveDelete(root.left, x);
            return root;
        }
        if (x > root.key) {
            root.right = recursiveDelete(root.right, x);
            return root;
        }
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        root.key = findMin(root.right).key;
        root.right = recursiveDelete(root.right, root.key);
        return root;
    }

    private Vertex findMin(Vertex root) {
        if (root.left != null)
            return findMin(root.left);
        else
            return root;
    }

    public void printBypass() throws FileNotFoundException {
        PrintStream ps = new PrintStream("tst.out");
        printBypass(root, ps);
        ps.close();
    }

    private void printBypass(Vertex ver, PrintStream ps) {
        if (ver != null) {
            ps.println(ver.key);
            printBypass(ver.left, ps);
            printBypass(ver.right, ps);
        }
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("tst.in"));
        Tree tree = new Tree();
        while (sc.hasNext()) {
            tree.add(sc.nextLong());
        }
        if (tree.count() % 2 != 0) {
            tree.inBypass();
        }
        tree.printBypass();
    }
}
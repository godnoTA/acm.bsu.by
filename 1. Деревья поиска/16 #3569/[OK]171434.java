import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.Math.max;

class Tree {
    static class Vertex {
        private long key;
        private Vertex left;
        private Vertex right;
        private long h;
        private long l;
        private long b;
        private long a;
        private long msl;

        private Vertex(long key) {
            this.key = key;
        }
    }

    private long mslT;
    private Vertex root;

    public Tree() {
        root = null;
    }

    public void add(long x) {
        root = insert(root, x);
    }

    private Vertex insert(Vertex ver, long x) {
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

    public void deleteRoot() {
        root = recursiveDelete(root, root.key);
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

    public void rBypass() {
        rBypass(root);
    }

    private void rBypass(Vertex ver) {
        if (ver != null) {
            rBypass(ver.left);
            rBypass(ver.right);
            if (ver.left == null) {
                if (ver.right == null) {
                    ver.l = 1;
                    ver.h = 0;
                    ver.msl = 0;
                } else {
                    ver.l = ver.right.l;
                    ver.h = ver.right.h + 1;
                    ver.msl = ver.right.h + 1;

                }
            } else if (ver.right == null) {
                ver.l = ver.left.l;
                ver.h = ver.left.h + 1;
                ver.msl = ver.left.h + 1;
            } else {
                ver.h = max(ver.right.h, ver.left.h) + 1;
                ver.msl = ver.left.h + ver.right.h + 2;
                if (ver.left.h == ver.right.h) {
                    ver.l = ver.left.l + ver.right.l;
                } else ver.l = max(ver.right.l, ver.left.l);
            }
            if (ver.msl > mslT) {
                mslT = ver.msl;
            }
        }

    }

    public void dBypass() {
        dBypass(root);
    }

    private void dBypass(Vertex ver) {
        if (ver != null) {
            ver.b = countB(ver);
            if (ver.left == null) {
                if (ver.right != null) {
                    ver.right.a = ver.a + ver.b;
                }
            } else if (ver.right == null) {
                ver.left.a = ver.a + ver.b;
            } else {
                if (ver.left.h > ver.right.h) {
                    ver.left.a = ver.a + ver.b;
                    ver.right.a = ver.b;
                } else if (ver.left.h < ver.right.h) {
                    ver.right.a = ver.a + ver.b;
                    ver.left.a = ver.b;
                } else {
                    ver.right.a = ver.b + ver.right.l * ver.a / ver.l;
                    ver.left.a = ver.b + ver.left.l * ver.a / ver.l;
                }
            }
            dBypass(ver.left);
            dBypass(ver.right);
        }
        if (mslT == 0) root.a = 1;
    }

    private long countB(Vertex ver) {
        long b = 0;
        if (ver != null) {
            if (ver.msl == mslT) {
                if (ver.left == null) {
                    if (ver.right != null) {
                        b = ver.right.l;
                    }
                } else if (ver.right == null) {
                    b = ver.left.l;
                } else {
                    b = ver.right.l * ver.left.l;
                }
            }
        }
        return b;
    }

    public void printBypass() throws FileNotFoundException {
        PrintStream ps = new PrintStream("output.txt");
        printBypass(root, ps);
        ps.close();
    }

    private void printBypass(Vertex ver, PrintStream ps) {
        if (ver != null) {
            ps.print(ver.key);
            ps.println(" " + (ver.a + ver.b));
            printBypass(ver.left, ps);
            printBypass(ver.right, ps);
        }
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Tree tree = new Tree();
        while (sc.hasNext()) {
            tree.add(sc.nextLong());
        }
        tree.deleteRoot();
        tree.rBypass();
        tree.dBypass();
        tree.printBypass();
    }
}
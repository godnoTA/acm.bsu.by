
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class BinaryTree {
        static class Top {
            int key;
            Top left;
            Top right;
            Top parent;

            public Top(int a) {
                this.key = a;
            }
        }

        private Top root;

        public void add(int x) {
            Top head = null;          //head=parent
            Top top = root;
            while (top != null) {
                head = top;
                if (x < top.key) {
                    top = top.left;
                } else if (x > top.key) {
                    top = top.right;
                } else {
                    return;
                }
            }
            Top newTop = new Top(x);

            if (head == null) {
                root = newTop;
            } else if (x < head.key) {
                head.left = newTop;
                newTop.parent = head;
            } else if (x > head.key) {
                head.right = newTop;
                newTop.parent = head;
            }
        }

        public Top search(Top top, int x) {            //return null if root is null
            while (top != null) {
                if (top.key == x) {
                    return top;
                } else if (top.key < x) {
                    top.parent = top;
                    top = top.right;
                } else {
                    top.parent = top;
                    top = top.left;
                }
            }
            return top.parent;
        }

        public void outKey(Top top, PrintStream ps) throws FileNotFoundException {
            if (top != null) {
                ps.println(top.key);
            }
        }

        public void traversal(Top top, PrintStream ps) throws FileNotFoundException {
            if (top != null) {
                outKey(top, ps);
                traversal(top.left, ps);
                traversal(top.right, ps);
            }
        }

        public Top getRoot() {
            return root;
        }

        public void deleteRight(int key) {
            Top top = search(getRoot(), key);
            Top newTop = top;
            if (top.right != null) {
                top = top.right;
                while (top.left != null) {
                    top.parent = top;
                    top = top.left;
                }
                newTop.key = top.key;
                if (top.parent.right == top) {
                    top.parent.right = top.right;
                } else if (top.parent.left == top) {
                    top.parent.left = top.left;
                }
            } else {
                if (top.left == null) {
                    if (top.parent.right == top) {
                        top.parent.right = null;
                    } else if (top.parent.left == top) {
                        top.parent.left = null;
                    }
                } else {
                    if (top.parent.right == top) {
                        top.parent.right = top.left;
                    } else if (top.parent.left == top) {
                        top.parent.left = top.left;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BinaryTree obj = new BinaryTree();
        Scanner sc = new Scanner(new File("input.txt"));
        PrintStream ps = new PrintStream("output.txt");
        //int delKey = sc.nextInt();
        while (sc.hasNextInt()) {
            obj.add(sc.nextInt());
        }
       // obj.deleteRight(delKey);
        obj.traversal(obj.getRoot(), ps);
        ps.close();
    }
}

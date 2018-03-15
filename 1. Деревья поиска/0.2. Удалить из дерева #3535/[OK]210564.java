
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
            int height;
            int MSL;

            public Top(int a) {
                this.key = a;
            }
        }

        private Top root;
        private int MaxMSL;
        private int sumLR;
        private Top rootDel;

        public BinaryTree.Top getRoot() {
            return root;
        }

        public void setRoot(Top root) {
            this.root = root;
        }

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

        public Top delete_recursively(Top v, int x) {

            if (x < v.key) {
                v.left = delete_recursively(v.left, x);
                return v;
            } else if (x > v.key) {
                v.right = delete_recursively(v.right, x);
                return v;
            }
            if (v.left == null) {
                return v.right;
            } else if (v.right == null) {
                return v.left;
            } else {
                int min_key = find_min(v.right).key;
                v.key = min_key;
                v.right = delete_recursively(v.right, min_key);
                return v;
            }
        }

        public Top find_min(Top v) {
            if (v.left != null) {
                return find_min(v.left);
            } else {
                return v;
            }
        }

        public void outKey(Top top, PrintStream ps) throws FileNotFoundException {
            if (top != null) {
                ps.println(top.key);
            }
        }

        public void traversalStraight(Top top, PrintStream ps) throws FileNotFoundException {
            if (top != null) {
                outKey(top, ps);
                traversalStraight(top.left, ps);
                traversalStraight(top.right, ps);
            }
        }

        public void deleteRight(int key) throws Exception {
            Top top = search(key);
            if (top.key != key) {
                throw new Exception();
            }
            Top newTop = top;
            if (top.right != null) {
                if (top.left != null) {
                    top = top.right;
                    while (top.left != null) {
                        top.parent = top;
                        top = top.left;
                    }
                    newTop.key = top.key;
                    if (top.parent.left == top) {
                        top.parent.left = top.right;
                        if (top.right != null) {
                            top.right.parent = top.parent;
                        }
                    } else if (top.parent.right == top) {
                        top.parent.right = top.right;
                        if (top.right != null) {
                            top.right.parent = top.parent;
                        }
                    }
                } else if (top.left == null) {
                    if (top != root) {
                        top.parent.right = top.right;
                    } else {
                        root = top.right;
                        root.parent = null;
                    }
                }
            } else {
                if (top != root) {
                    if (top.parent.left == top) {
                        top.parent.left = top.left;
                        if (top.left != null) {
                            top.left.parent = top.parent;
                        }
                    } else if (top.parent.right == top) {
                        top.parent.right = top.left;
                        if (top.left != null) {
                            top.left.parent = top.parent;
                        }
                    }
                } else {
                    root = top.left;
                    root.parent = null;
                }
            }
        }

        public Top search(int x) {            //return null if root is null
            Top top = root;
            while (top != null) {
                if (top.key == x) {
                    return top;
                } else if (top.key < x) {
                    top = top.right;
                } else {
                    top = top.left;
                }
            }
            return root;
        }
    }

    public static void main(String[] args) throws Exception {
        BinaryTree obj = new BinaryTree();
        Scanner sc = new Scanner(new File("input.txt"));
        PrintStream ps = new PrintStream("output.txt");
        int delKey = sc.nextInt();
        while (sc.hasNextInt()) {
            obj.add(sc.nextInt());
        }
        try {
            //obj.deleteRight(delKey);
            obj.setRoot(obj.delete_recursively(obj.getRoot(), delKey));
            obj.traversalStraight(obj.getRoot(), ps);
        } catch (Exception e) {
            obj.traversalStraight(obj.getRoot(), ps);
        }
        ps.close();
    }
}
import java.io.*;
import java.util.*;

/**
 * Created by Yauheni on 23.02.16.
 */

class SuperStructure {
    public SuperStructure() {
        first = null;
        last = null;
        mean = null;
        odd = false;
        stack = new Stack<>();
    }

    public void insert(Tree.Node node) {
        if (first == null) {
            first = new Element(node, null);
            last = first;
            mean = first;
        } else {
            last.next = new Element(node, last);
            last = last.next;
            if (!odd)
                mean = mean.next;
        }
        odd = !odd;
    }

    public void anotherInsert(Tree.Node node) {
        stack.push(last);
        last = new Element(node, last.previous);
        last.previous.next = last;
        first = first.next;
        if (!odd)
            mean = mean.next;
        odd = !odd;
    }

    public void remove() {
        if (first == last) {
            first = null;
            last = null;
            mean = null;
        } else {
            last.previous.next = null;
            last = last.previous;
            if (odd)
                mean = mean.previous;
        }
        odd = !odd;
    }

    public void anotherRemove() {
        first = first.previous;
        if (odd)
            mean = mean.previous;
        last = stack.pop();
        last.previous.next = last;
        odd = !odd;
    }

    class Element {
        public Element(Tree.Node node, Element previous) {
            this.node = node;
            next = null;
            this.previous = previous;
        }

        public Tree.Node node;
        private Element next;
        private Element previous;
    }

    private Element first;
    private Element last;
    public Element mean;
    boolean odd;
    private Stack<Element> stack;
}

class Tree {
    class Node implements Comparable<Node> {
        public Node(int value, Node parent) {
            this.value = value;
            left = null;
            right = null;
            if (parent != null) {
                if (value < parent.value)
                    isLeft = true;
                depth = parent.depth + 1;
            } else {
                depth = 0;
            }
        }

        public Node(int value, Node parent, int depth) {
            this.value = value;
            left = null;
            right = null;
            this.depth = depth;
            if (parent.left.value == value)
                isLeft = true;
        }

        public int value;
        private Node left;
        private Node right;
        private int depth;
        boolean isLeft;

        @Override
        public int compareTo(Node o) {
            return value - o.value;
        }
    }

    public Tree() {
        root = null;
        means = new TreeSet<>();
        minLeafDepth = 0;
        way = new SuperStructure();
        l = 0;
        r = 0;
    }

    public void fill(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
         insert(Integer.parseInt(line));
        }
    }

    public void insert(int data) {
        insert(data, root);
    }

    private void insert(int value, Node node) {
        if (node == null) {
            root = new Node(value, null);
            return;
        }
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value, node);
            } else
                insert(value, node.left);
        } else if (value > node.value) {
            if (node.right == null) {
                node.right = new Node(value, node);
            } else
                insert(value, node.right);
        }
    }

    public void findMeans(Node node) {
        if (l == r) {
            way.insert(node);
        } else if ((l > r) == (node.isLeft)) {
            way.insert(node);
        } else {
            way.anotherInsert(node);
        }
        if (node != root)
            if (node.isLeft)
                l++;
            else
                r++;
        if (node.left != null)
            findMeans(node.left);
        if (node.right != null)
            findMeans(node.right);
        if (node.left == null && node.right == null) {
            if (minLeafDepth == 0 || node.depth < minLeafDepth) {
                means = new TreeSet<>();
                minLeafDepth = node.depth;
                means.add(way.mean.node);
            }
            else if (minLeafDepth == node.depth) {
                means.add(way.mean.node);
            }
        }
        if (node != root)
            if (node.isLeft) {
                l--;
            } else {
                r--;
            }
        if (l == r) {
            way.remove();
        } else if ((l > r) == (node.isLeft)) {
            way.remove();
        } else {
            way.anotherRemove();
        }
    }

    public void removeMeans() {
        if (minLeafDepth % 2 == 0) {
            //means.forEach(this::remove);
            for (Node node : means) {
                remove(node.value, root);
            }
        }
    }

    private void remove(int value, Node from) {
        int debug;
        if (value == 26)
            debug = 0;
        Node node = from;
        Node parent = null;
        while (value != node.value) {
            parent = node;
            if (value < node.value)
                node = node.left;
            else
                node = node.right;
        }
        if (node.left == null) {
            if (node.right == null) {
                if (node.isLeft)
                    parent.left = null;
                else
                    parent.right = null;
            } else {
                if (node.isLeft) {
                    parent.left = node.right;
                    parent.left.isLeft = true;
                }
                else
                    parent.right = node.right;
            }
        } else {
            if (node.right == null) {
                if (node.isLeft)
                    parent.left = node.left;
                else {
                    parent.right = node.left;
                    parent.right.isLeft = false;
                }
            }
            else {
                Node temp = node.left;
                parent = node;
                while (temp.right != null) {
                    parent = temp;
                    temp = temp.right;
                }
                int backup = temp.value;
                remove(temp.value, parent);
                node.value = backup;
            }
        }
    }

/*
    private void remove(Node node) {
        Node temp = root;
        Node parent = null;
        while (temp != node) {
            if (node.value < temp.value) {
                parent = temp;
                temp = temp.left;
            } else if (node.value > temp.value) {
                parent = temp;
                temp = temp.right;
            }
        }
        if (node.left == null && node.right == null) {
            if (node != root) {
                if (node.isLeft)
                    parent.left = null;
                else
                    parent.right = null;
            } else
                root = null;
        } else if (node.left == null) {
            if (node != root) {
                if (node.isLeft)
                    parent.left = node.right;
                else
                    parent.right = node.right;
            } else
                root = root.right;
        } else if (node.right == null) {
            if (node != root) {
                if (node.isLeft)
                    parent.left = node.left;
                else
                    parent.right = node.left;
            } else {
                root = root.left;
            }
        } else {
            temp = node.left;
            while (temp.right != null)
                temp = temp.right;
            int nikita = temp.value;
            remove(temp);
            node.value = nikita;
        }
    }
*/
    private void preOrderTreeWalk(Node node, PrintWriter pw) {
        pw.print(node.value + "\n");
        if (node.left != null)
            preOrderTreeWalk(node.left, pw);
        if (node.right != null)
            preOrderTreeWalk(node.right, pw);
    }

    public void preOrderTreeWalk(PrintWriter pw) {
        preOrderTreeWalk(root, pw);
    }

    public Node root;
    public Set<Node> means;
    private int minLeafDepth;
    private SuperStructure way;
    private int l;
    private int r;
}

public class Main implements Runnable {
    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("tst.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tree tree = new Tree();
        try {
            tree.fill(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tree.findMeans(tree.root);
        tree.removeMeans();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("tst.out");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tree.preOrderTreeWalk(pw);
        pw.close();
    }
}
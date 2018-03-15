import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

class Tree {

    public Node root;
    public List<Node> keys;
    public List<Integer> amounts;
    public List<Node> result;

    class Node {

        public Node left;
        public Node right;
        public Node parent;
        public int key;
        public int amount;
        public int rightAmount;
        public int leftAmount;


        public Node(int key) {
            left = null;
            right = null;
            parent = null;
            this.key = key;
            amount = 1;
            rightAmount = 0;
            leftAmount = 0;
        }
    }

    public Tree() {
        root = null;
        result = new ArrayList<Node>();
        keys = new ArrayList<Node>();
        amounts = new ArrayList<Integer>();
    }

    public void addNode(int key) {

        root = doInsert(root, key);
    }

    private Node doInsert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = doInsert(node.left, key);
        } else if (key > node.key) {
            node.right = doInsert(node.right, key);
        }
        return node;
    }

    public void readTree() throws FileNotFoundException {

        Scanner sc = new Scanner(new File("in.txt"));
        root = new Node(sc.nextInt());
        while (sc.hasNext()) {
            addNode(sc.nextInt());
        }
    }

    public void leftView(Node n) {

        if (n != null) {
            result.add(n);
            leftView(n.left);
            leftView(n.right);
        }
    }

    public void downView(Node n) {


        if (n != null) {
            downView(n.left);
            downView(n.right);
            if (n.left != null) {
                n.leftAmount += n.left.amount;
                n.leftAmount += n.left.leftAmount;
                n.leftAmount += n.left.rightAmount;
            }
            if (n.right != null) {
                n.rightAmount += n.right.amount;
                n.rightAmount += n.right.leftAmount;
                n.rightAmount += n.right.rightAmount;

            }
            keys.add(n);
        }
    }

    public void findDeleteKey() {
        for (Node item : keys) {
            if (Math.abs(item.rightAmount - item.leftAmount) == 1) {
                amounts.add(item.key);
            }
        }
        Collections.sort(amounts);
        if (amounts.size() % 2 != 0) {
            if (amounts.size() == 1) {
                this.root = deleteNode(this.root, amounts.get(0));
            } else {
                this.root = deleteNode(this.root, amounts.get(amounts.size() / 2 ));
            }
        }
    }

    public void printTree() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new File("out.txt"));
        for (Node item : result) {
            ps.println(item.key);
        }
        ps.close();
    }

    private Node minimum(Node x) {
        if (x.left == null)
            return x;
        return minimum(x.left);
    }

    private Node deleteNode(Node root, int deleteKey) {
        if (root == null) {
            return root;
        }
        if (deleteKey < root.key) {
            root.left = deleteNode(root.left, deleteKey);
        } else {
            if (deleteKey > root.key) {
                root.right = deleteNode(root.right, deleteKey);
            } else {
                if (root.left != null && root.right != null) {
                    root.key = minimum(root.right).key;
                    root.right = deleteNode(root.right, root.key);
                } else {
                    if (root.left != null) {
                        root = root.left;
                    } else {
                        root = root.right;
                    }
                }
            }
        }
        return root;
    }


}

public class Main {
    public static void main(String[] args) {

        try {
            Tree t = new Tree();
            t.readTree();
            t.downView(t.root);
            t.findDeleteKey();
            t.leftView(t.root);
            t.printTree();

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
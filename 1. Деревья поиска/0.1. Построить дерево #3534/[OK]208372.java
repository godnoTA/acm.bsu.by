import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

class SearchTree {
    public class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    private TreeNode root;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean add(int value) {
        if (isEmpty()) {
            root = new TreeNode(value);
            return true;
        }
        TreeNode current = root;
        while (true) {
            if (current.getValue() > value) {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode(value));
                    return true;
                } else {
                    current = current.getLeft();
                }
            } else if (current.getValue() < value) {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode(value));
                    return true;
                } else {
                    current = current.getRight();
                }
            } else {
                return false;
            }
        }
    }

    public void printLeftWalk(PrintStream ps) {
        if (isEmpty()) {
            return;
        }
        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            ps.println(node.getValue());
            if (node.getRight() != null) {
                nodes.push(node.getRight());
            }
            if (node.getLeft() != null) {
                nodes.push(node.getLeft());
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
	    SearchTree tree = new SearchTree();
	    try (Scanner scan = new Scanner(new File("input.txt"))) {
	        while (scan.hasNextInt()) {
	            tree.add(scan.nextInt());
            }
        } catch (IOException ex) {
	        ex.printStackTrace();
        }

        try (PrintStream ps = new PrintStream(new FileOutputStream("output.txt"))) {
            tree.printLeftWalk(ps);
        } catch (IOException ex) {
	        ex.printStackTrace();
        }
    }
}

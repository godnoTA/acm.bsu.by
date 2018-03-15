import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

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

    public boolean remove(int value) {
        return remove(null, root, value);
    }

    private boolean remove(TreeNode parent, TreeNode current, int value) {
        while (current != null && current.getValue() != value) {
            parent = current;
            if (current.getValue() > value) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (current == null) {
            return false;
        }

        if (current.getLeft() == null || current.getRight() == null) {
            TreeNode temp;
            if (current.getRight() != null) {
                temp = current.getRight();
            } else {
                temp = current.getLeft();
            }
            if (parent != null) {
                if (parent.getRight() == current) {
                    parent.setRight(temp);
                } else {
                    parent.setLeft(temp);
                }
            } else {
                root = temp;
            }
            return true;
        } else {
            TreeNode temp = current.getRight();
            while (temp.getLeft() != null) {
                temp = temp.getLeft();
            }
            current.setValue(temp.getValue());
            return remove(current, current.getRight(), temp.getValue());
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
	    int deleteKey = 0;
	    try (Scanner scan = new Scanner(new File("input.txt"))) {
	        deleteKey = scan.nextInt();
	        while (scan.hasNextInt()) {
	            tree.add(scan.nextInt());
            }
        } catch (IOException ex) {
	        ex.printStackTrace();
        }

        tree.remove(deleteKey);

        try (PrintStream ps = new PrintStream(new FileOutputStream("output.txt"))) {
            tree.printLeftWalk(ps);
        } catch (IOException ex) {
	        ex.printStackTrace();
        }
    }
}

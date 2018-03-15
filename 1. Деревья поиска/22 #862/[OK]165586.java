//package pkg22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

class BinarySearchTree {

    TreeNode root;

    private int properCount;
    private int medianCount;

    public class TreeNode {

        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;

        public long data;

        public int heightLeft, heightRight, CCLeft, CCRight;
        public boolean proper = false;

        TreeNode(long data) {
            this.data = data;
            left = right = parent = null;
        }
    }

    public boolean add(TreeNode newNode) {
        if (root == null) {
            root = newNode;
            return true;
        }

        TreeNode currNode = root;
        while (true) {
            if (newNode.data > currNode.data) {
                if (currNode.right == null) {
                    currNode.right = newNode;
                    newNode.parent = currNode;
                    return true;
                } else {
                    currNode = currNode.right;
                }
            } else if (newNode.data < currNode.data) {
                if (currNode.left == null) {
                    currNode.left = newNode;
                    newNode.parent = currNode;
                    return true;
                } else {
                    currNode = currNode.left;
                }
            } else {
                return false;
            }
        }
    }

    public void deleteRight(TreeNode treeNode) {
        if (treeNode.left == null) {
            if (treeNode.parent == null) {
                root = treeNode.right;
            } else if (treeNode.data > treeNode.parent.data) {
                treeNode.parent.right = treeNode.right;
            } else {
                treeNode.parent.left = treeNode.right;
            }
            if (treeNode.right != null) {
                treeNode.right.parent = treeNode.parent;
            }
        } else if (treeNode.right == null) {
            if (treeNode.parent == null) {
                root = treeNode.left;
            } else if (treeNode.data > treeNode.parent.data) {
                treeNode.parent.right = treeNode.left;
            } else {
                treeNode.parent.left = treeNode.left;
            }
            treeNode.left.parent = treeNode.parent;
        } else {
            TreeNode newNode = treeNode.right;
            while (newNode.left != null) {
                newNode = newNode.left;
            }
            deleteRight(newNode);
            treeNode.data = newNode.data;
        }
    }

    public void Serialize(TreeNode treeNode, PrintWriter output) {
        if (treeNode == null) {
            return;
        }
        output.println(treeNode.data);
        Serialize(treeNode.left, output);
        Serialize(treeNode.right, output);
    }

    BinarySearchTree() {
        root = null;
    }

    BinarySearchTree(String fileName) throws FileNotFoundException, IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = input.readLine()) != null) {
            add(new TreeNode(Long.parseLong(line)));
        }
        input.close();
    }

    BinarySearchTree(Random random, int n) {
        for (int i = 0; i < n; i++) {
            add(new TreeNode(random.nextLong()));
        }
    }

    public void KLP(TreeNode tn) {
        if (tn == null) {
            return;
        }
        System.out.println(tn.data);
        KLP(tn.left);
        KLP(tn.right);
    }

    public void calculateProperties(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        calculateProperties(treeNode.left);
        calculateProperties(treeNode.right);
        if (treeNode.left == null) {
            treeNode.CCLeft = 0;
            treeNode.heightLeft = -1;
        } else {
            treeNode.CCLeft = treeNode.left.CCLeft + treeNode.left.CCRight + 1;
            treeNode.heightLeft = Math.max(treeNode.left.heightLeft, treeNode.left.heightRight) + 1;
        }

        if (treeNode.right == null) {
            treeNode.CCRight = 0;
            treeNode.heightRight = -1;
        } else {
            treeNode.CCRight = treeNode.right.CCLeft + treeNode.right.CCRight + 1;
            treeNode.heightRight = Math.max(treeNode.right.heightLeft, treeNode.right.heightRight) + 1;
        }

        if ((treeNode.heightLeft != treeNode.heightRight) && (treeNode.CCLeft == treeNode.CCRight)) {
            treeNode.proper = true;
            properCount++;
        }
    }

    public TreeNode properMedian() {
        if (properCount % 2 == 0) {
            return null;
        }
        medianCount = properCount / 2;
        return median(root);
    }

    private TreeNode median(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        TreeNode result = median(treeNode.left);
        if (result == null) {
            if ((treeNode.proper) && (medianCount-- == 0)) {
                return treeNode;
            }
            result = median(treeNode.right);
        }
        return result;
    }
}

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BinarySearchTree tree = new BinarySearchTree("in.txt");
        tree.calculateProperties(tree.root);
        BinarySearchTree.TreeNode node = tree.properMedian();
        if (node != null) {
            tree.deleteRight(node);
        }
        PrintWriter output = new PrintWriter("out.txt");
        tree.Serialize(tree.root, output);
        output.close();
    }

}

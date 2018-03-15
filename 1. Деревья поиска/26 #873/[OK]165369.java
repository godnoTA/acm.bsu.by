//package pkg26;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

class BinarySearchTree {

    TreeNode root;
    int maxDifference;

    public class TreeNode {

        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;

        public long data;
        public int childCount;
        public int difference;

        TreeNode(long data) {
            this.data = data;
            left = right = parent = null;
            childCount = difference = 0;
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

    public void countDifferences(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        countDifferences(treeNode.left);
        countDifferences(treeNode.right);
        if (treeNode.right != null) {
            treeNode.difference = treeNode.childCount = 1 + treeNode.right.childCount;
        }
        if (treeNode.left != null) {
            treeNode.childCount += 1 + treeNode.left.childCount;
            treeNode.difference -= 1 + treeNode.left.childCount;
        }
        treeNode.difference = Math.abs(treeNode.difference);
        if (maxDifference < treeNode.difference) {
            maxDifference = treeNode.difference;
        }
    }

    public TreeNode problemFind(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        TreeNode result;
        result = problemFind(treeNode.right);
        if (result == null) {
            if (maxDifference == treeNode.difference) {
                return treeNode;
            }
            result = problemFind(treeNode.left);
        }
        return result;
    }

    BinarySearchTree() {
        root = null;
        maxDifference = 0;
    }

    BinarySearchTree(String fileName) throws FileNotFoundException, IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = input.readLine()) != null) {
            add(new TreeNode(Long.parseLong(line)));
        }
        maxDifference = 0;
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
}

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BinarySearchTree tree = new BinarySearchTree("tst.in");
        tree.countDifferences(tree.root);
        tree.deleteRight(tree.problemFind(tree.root));
        PrintWriter output = new PrintWriter("tst.out");
        tree.Serialize(tree.root, output);
        output.close();
    }

}

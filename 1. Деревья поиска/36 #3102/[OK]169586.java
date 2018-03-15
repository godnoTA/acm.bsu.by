//package pkg36;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

class BinarySearchTree {

    public TreeNode root;
    public int nodeCount, nodeCountCurrent;

    public class TreeNode {

        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;

        public int data;

        TreeNode(int data) {
            this.data = data;
            left = right = parent = null;
        }
    }

    public void SerializeLPK(TreeNode treeNode, PrintWriter output) {
        if (treeNode == null) {
            return;
        }
        SerializeLPK(treeNode.left, output);
        SerializeLPK(treeNode.right, output);
        output.print(treeNode.data);
        nodeCountCurrent++;
        if (nodeCountCurrent != nodeCount) {
            output.append(' ');
        }
    }

    public void SerializeLKP(TreeNode treeNode, PrintWriter output) {
        if (treeNode == null) {
            return;
        }
        SerializeLKP(treeNode.left, output);
        output.print(treeNode.data);
        nodeCountCurrent++;
        if (nodeCountCurrent != nodeCount) {
            output.append(' ');
        }

        SerializeLKP(treeNode.right, output);
    }

    BinarySearchTree(String fileName) throws Exception {
        Scanner input = new Scanner(new FileReader(fileName));
        nodeCount = input.nextInt();
        Stack<TreeNode> st = new Stack<>();
        TreeNode star = root = new TreeNode(input.nextInt());
        st.push(root);
        for (int i = 1; i < nodeCount; i++) {
            TreeNode current = new TreeNode(input.nextInt());
            while ((!st.empty()) && (st.peek().data <= current.data)) {
                star = st.pop();
            }
            if (current.data >= star.data) {
                star.right = current;
            } else {
                star.left = current;
            }
            st.push(current);
            star = current;
        }
        input.close();
    }

}

public class Main implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            BinarySearchTree tree = new BinarySearchTree("input.txt");
            PrintWriter output = new PrintWriter("output.txt");
            tree.nodeCountCurrent = 0;
            tree.SerializeLPK(tree.root, output);
            output.println("");
            tree.nodeCountCurrent = 0;
            tree.SerializeLKP(tree.root, output);
            output.close();
        } catch (Exception e) {
        }
    }

}

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class Tree {

    private NodeBinTree root;

    private static final Vector<Integer> values = new Vector<>();

    public void push(int value) {

        NodeBinTree newNode = new NodeBinTree(value);

        if (root == null) {

            root = newNode;

        } else {

            NodeBinTree focusNode = root;

            NodeBinTree parent;

            while (true) {

                parent = focusNode;

                if (value < focusNode.value) {

                    focusNode = focusNode.leftChild;

                    if (focusNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                }
                else {
                    focusNode = focusNode.rightChild;

                    if (focusNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void traverse(NodeBinTree current) {

        if (current != null) {

            values.add(current.value);
            traverse(current.leftChild);
            traverse(current.rightChild);
        }
    }

    public boolean delete(int value) {
        if (root == null)
            return false;
        else {
            if (root.getValue() == value) {
                NodeBinTree auxRoot = new NodeBinTree(0);
                auxRoot.setLeftChild(root);
                boolean result = root.delete(value, auxRoot);
                root = auxRoot.getLeftChild();
                return result;
            } else {
                return root.delete(value, null);
            }
        }
    }
//
//    public NodeBinTree findNode(int value) {
//
//        NodeBinTree focusNode = root;
//
//        while (focusNode.value != value) {
//
//            if (value < focusNode.value) {
//                focusNode = focusNode.leftChild;
//            }
//            else {
//
//                focusNode = focusNode.rightChild;
//            }
//            if (focusNode == null)
//                return null;
//        }
//        return focusNode;
//    }

    class NodeBinTree {

        private int value;

        private NodeBinTree leftChild;
        private NodeBinTree rightChild;

        NodeBinTree(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public NodeBinTree getLeftChild() {
            return this.leftChild;
        }

        public boolean delete(int value, NodeBinTree parent) {
            if (value < this.value) {
                if (leftChild != null)
                    return leftChild.delete(value, this);
                else
                    return false;
            } else if (value > this.value) {
                if (rightChild != null)
                    return rightChild.delete(value, this);
                else
                    return false;
            } else {
                if (leftChild != null && rightChild != null) {
                    this.value = rightChild.minimum();
                    rightChild.delete(this.value, this);
                } else if (parent.leftChild == this) {
                    parent.leftChild = (leftChild != null) ? leftChild : rightChild;
                } else if (parent.rightChild == this) {
                    parent.rightChild = (leftChild != null) ? leftChild : rightChild;
                }
                return true;
            }
        }

        public int minimum() {
            if (leftChild == null)
                return value;
            else
                return leftChild.minimum();
        }

        public void setLeftChild(NodeBinTree leftChild) {
            this.leftChild = leftChild;
        }
    }

    public static void main(String[] args) throws IOException {

        Tree binTree = new Tree();
        Scanner sc = new Scanner(new File("input.txt"));
        int key = sc.nextInt();
        while (sc.hasNext()) {
            int x = sc.nextInt();
            binTree.push(x);
        }
        binTree.delete(key);
        binTree.traverse(binTree.root);
        File f = new File("output.txt");
        PrintWriter pw = new PrintWriter(f);
        for (int value: values) {
            pw.println(value);
        }
        pw.close();
    }
}


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class Tree {

    private Node root;

    private static Vector<Node> valuesAdvanced;
    private static Vector<Long> values;

    private void push(long value) {

        Node newNode = new Node(value);

        if (root == null) {

            root = newNode;

        } else {

            Node focusNode = root;

            Node parent;

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

    private void setMaxLenghtSemipath(Node current)
    {
        if (current != null) {
            setMaxLenghtSemipath(current.leftChild);
            setMaxLenghtSemipath(current.rightChild);
            if(current.leftChild == null && current.rightChild == null ) {
                current.height = 0;
                current.lenghOfSemipath = 0;
                return;
            }
            current.height = 0;
            long h1 = 0,h2 = 0;
            current.lenghOfSemipath = 0;
            if(current.leftChild!=null)
            {
                current.lenghOfSemipath += current.leftChild.height + 1;
                h1 = current.leftChild.height + 1;
            }
            if(current.rightChild!=null) {
                current.lenghOfSemipath += current.rightChild.height +1;
                h2 = current.rightChild.height +1;
            }
            current.height = Math.max(h1, h2);
        }

    }

    private long findRootsWithMaxSemipathWithMinLeaves()
    {
        Node maxLengh = new Node();
        boolean flag = false;
        for(Node node: valuesAdvanced)
        {
            if(!flag) {
                maxLengh = node;
                flag = true;
            }
            else
            {
                if((node.lenghOfSemipath > maxLengh.lenghOfSemipath)||
                        (node.lenghOfSemipath == maxLengh.lenghOfSemipath && node.value < maxLengh.value))
                    maxLengh = node;
            }
        }
        return maxLengh.value;
    }

    private void preOrderTraverse(Node current) {

        if (current != null) {

            values.add(current.value);
            preOrderTraverse(current.leftChild);
            preOrderTraverse(current.rightChild);
        }
    }

    private void traverseWithHeight(Node current) {

        if (current != null) {

            valuesAdvanced.add(new Node(current.value, current.height, current.lenghOfSemipath));
            traverseWithHeight(current.leftChild);
            traverseWithHeight(current.rightChild);
        }
    }

    private boolean delete(long value) {
        if (root == null)
            return false;
        else {
            if (root.getValue() == value) {
                Node auxRoot = new Node(0);
                auxRoot.setLeftChild(root);
                boolean result = root.delete(value, auxRoot);
                root = auxRoot.getLeftChild();
                return result;
            } else {
                return root.delete(value, null);
            }
        }
    }

    class Node {

        private long value;
        private long height;
        private long lenghOfSemipath;

        private Node leftChild;
        private Node rightChild;

        Node(long value) {
            this.value = value;
        }

        Node(long value, long height, long lenghOfSemipath) {
            this.value = value;
            this.height = height;
            this.lenghOfSemipath = lenghOfSemipath;
        }

        Node(){}

        private long getValue() {
            return this.value;
        }

        private Node getLeftChild() {
            return this.leftChild;
        }

        private boolean delete(long value, Node parent) {
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

        private long minimum() {
            if (leftChild == null)
                return value;
            else
                return leftChild.minimum();
        }

        private void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public String toString()
        {
            return (this.value+" "+this.height+" "+this.lenghOfSemipath);
        }
    }

    public static void main(String[] args) throws IOException {

        Tree binTree = new Tree();
        Scanner sc = new Scanner(new File("in.txt"));
        while (sc.hasNext()) {
            long x = sc.nextLong();
            binTree.push(x);
        }
        Tree.values = new Vector<>();
        binTree.setMaxLenghtSemipath(binTree.root);
        Tree.valuesAdvanced = new Vector<>();
        binTree.traverseWithHeight(binTree.root);
        long key = binTree.findRootsWithMaxSemipathWithMinLeaves();
        binTree.delete(key);
        binTree.preOrderTraverse(binTree.root);
        File f = new File("out.txt");
        PrintWriter pw = new PrintWriter(f);
        for(long  value: values)
        {
            pw.println(value);
        }
        pw.close();
    }
}
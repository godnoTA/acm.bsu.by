import javafx.util.Pair;

import java.io.*;
import java.util.Vector;

import static java.util.Collections.sort;

public class BinaryTree <T extends Comparable<T>> {

    private Node<T> root;
    Vector<Pair<Node<T>, Vector<Integer>>> nodesInfo;

    public class Node<T> {

        public T value;
        public Node parent;
        public Node left;
        public Node right;
        public Integer curDeep;

        Node(T value, Node left, Node parent, Node right) {
            this.value = value;
            this.left = left;
            this.parent = parent;
            this.right = right;
            this.curDeep = -1;
        }
    }

    public BinaryTree(T rootValue) {
        root = new Node<>(rootValue, null, null, null);
        root.curDeep = 0;
    }

    private Node<T> addKey(T value, Node<T> node, Node<T> parent) {
        if (node == null) {
            node = new Node<>(value, null, parent, null);
            node.curDeep = node.parent.curDeep + 1;
            return node;
        } else if (node.value.compareTo(value) > 0)
            node.left = addKey(value, node.left, node);
        else if (node.value.compareTo(value) < 0)
            node.right = addKey(value, node.right, node);

        return node;
    }

    void addKey(T var) {
        addKey(var, root, root);
    }

    private StringBuilder dRound(Node<T> node, StringBuilder stringBuilder) {
        stringBuilder.append(node.value + "\n");
        if (node.left != null)
            dRound(node.left, stringBuilder);
        if (node.right != null)
            dRound(node.right, stringBuilder);
        return stringBuilder;
    }

    public StringBuilder directRound() {
        StringBuilder stringBuilder = new StringBuilder();
        dRound(root, stringBuilder);
        return stringBuilder;
    }

    private Node foundKey(T value, Node<T> parent) {
        Node<T> tempAnswer = null;
        if (parent == null) {
            return tempAnswer;
        }
        if (parent.value.compareTo(value) > 0) {
            tempAnswer = foundKey(value, parent.left);
        }
        if (parent.value.compareTo(value) < 0) {
            tempAnswer = foundKey(value, parent.right);
        }
        if (parent.value.compareTo(value) == 0) {
            tempAnswer = parent;
        }
        return tempAnswer;
    }

    public Node foundKey(T value) {
        return foundKey(value, root);
    }

    public boolean removeKey(T value) {
        return removeKey(this.foundKey(value));
    }

    private boolean removeKey(Node node) {

        if (node == null)
            return false;

        if (node.left == null && node.right == null) {
            if (node == root) {
                root = null;
                return true;
            }
            if (node.parent.left == node)
                node.parent.left = null;
            if (node.parent.right == node)
                node.parent.right = null;

            return true;
        }

        if (node.left == null) {
            if (node == root) {
                root = node.right;
                return true;
            }
            if (node.parent.right == node)
                node.parent.right = node.right;
            if (node.parent.left == node)
                node.parent.left = node.right;
            return true;
        }
        if (node.right == null) {
            if (node == root) {
                root = node.left;
                return true;
            }
            if (node.parent.left == node)
                node.parent.left = node.left;
            if (node.parent.right == node)
                node.parent.right = node.left;
            return true;
        }

        if (node.right != null && node.left != null) {
            Node temp = node;
            temp = temp.right;
            if (temp.left == null) {
                node.value = temp.value;
                node.right = temp.right;
            } else {
                while (temp.left != null)
                    temp = temp.left;
                node.value = temp.value;
                removeKey(temp);
            }
            return true;
        }
        return false;
    }

    public void investigateTheTree() {
        nodesInfo = new Vector<>();
        investigateTheNode(nodesInfo, root);
        Vector<T> temp = new Vector<>();
        for (int i = 0; i < nodesInfo.size(); ++i) {
            if (nodesInfo.get(i).getValue().get(1) == nodesInfo.get(i).getValue().get(3)
                    && nodesInfo.get(i).getValue().get(0) != nodesInfo.get(i).getValue().get(2))
                temp.add(nodesInfo.get(i).getKey().value);
        }
        if (temp.size() % 2 == 1) {
            sort(temp);
            this.removeKey(temp.get((temp.size() - 1) / 2));
        }
    }

    private void investigateTheNode(Vector<Pair<Node<T>, Vector<Integer>>> nodesInfo, Node<T> node) {
        nodesInfo.add(investigateTheNode(node));
        if (node.left != null)
            investigateTheNode(nodesInfo, node.left);
        if (node.right != null)
            investigateTheNode(nodesInfo, node.right);
    }

    private Pair<Node<T>, Vector<Integer>> investigateTheNode(Node<T> node) {
        Vector<Integer> data = new Vector<>();
        if (node.left != null) {
            data.add(numberOfChildren(node.left, 0) + 1);
            data.add(subtreeDepth(node.left, node.left.curDeep) - node.curDeep);
        } else {
            data.add(0);
            data.add(-1);
        }
        if (node.right != null) {
            data.add(numberOfChildren(node.right, 0) + 1);
            data.add(subtreeDepth(node.right, node.right.curDeep) - node.curDeep);
        } else {
            data.add(0);
            data.add(-1);
        }
        return new Pair<>(node, data);
    }

    private Integer numberOfChildren(Node<T> node, Integer numberOfChildren) {
        if (node.left != null) {
            ++numberOfChildren;
            numberOfChildren = numberOfChildren(node.left, numberOfChildren);
        }
        if (node.right != null) {
            ++numberOfChildren;
            numberOfChildren = numberOfChildren(node.right, numberOfChildren);
        }
        return numberOfChildren;
    }

    private Integer subtreeDepth(Node<T> node, Integer maxDeep) {
        if (node.left != null) {
            if (node.left.curDeep > maxDeep)
                maxDeep = subtreeDepth(node.left, node.left.curDeep);
            else
                maxDeep = subtreeDepth(node.left, maxDeep);
        }
        if (node.right != null) {
            if (node.right.curDeep > maxDeep)
                maxDeep = subtreeDepth(node.right, node.right.curDeep);
            else
                maxDeep = subtreeDepth(node.right, maxDeep);
        }
        return maxDeep;
    }

    public static void main(String[] args) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));

        /*Integer key = Integer.parseInt(reader.readLine());
        reader.readLine();*/
        BinaryTree<Integer> tree = new BinaryTree<>(Integer.parseInt(reader.readLine()));

        while (true) {
            String temp1 = reader.readLine();
            if (temp1 == null) break;
            tree.addKey(Integer.parseInt(temp1));
        }

        tree.investigateTheTree();

        StringBuilder stringBuilder = tree.directRound();

        writer.write(stringBuilder.toString());

        writer.close();
    }
}
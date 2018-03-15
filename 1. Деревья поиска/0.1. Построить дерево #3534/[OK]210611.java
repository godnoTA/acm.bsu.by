import java.io.*;

public class BinaryTree <T extends Comparable<T>>{

    private Node<T> root;

    public class Node<T> {

        public T value;
        public Node parent;
        public Node left;
        public Node right;

        Node(T value, Node left, Node parent, Node right){
            this.value = value;
            this.left = left;
            this.parent = parent;
            this.right = right;
        }
    }

    public BinaryTree(T rootValue) {
        root = new Node<T>(rootValue, null, null, null);
    }

    private Node<T> addKey(T value, Node<T> node, Node<T> parent) {
        if (node == null) {
            node = new Node<>(value, null, parent, null);
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

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        BinaryTree<Integer> tree = new BinaryTree<>(Integer.parseInt(reader.readLine()));
        while (true) {
            String temp = reader.readLine();
            if (temp == null) break;
            tree.addKey(Integer.parseInt(temp));
        }

        StringBuilder stringBuilder = tree.directRound();
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write(stringBuilder.toString());
        writer.close();
    }
}

/*
/*


    private void bRound(Node<T> node) {
        if (node.left != null)
            bRound(node.left);
        if (node.right != null)
            bRound(node.right);
        System.out.print(node.value + " ");
    }

    private void sRound(Node<T> node) {
        if (node.left != null)
            sRound(node.left);
        System.out.print(node.value + " ");
        if (node.right != null)
            sRound(node.right);
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

    private boolean removeKey(T value) {
        Node node = this.foundKey(value);

        if (node == null || node.parent == null)
            return false;

        if (node.left == null && node.right == null) {
            if (node.parent.left == node)
                node.parent.left = null;
            if (node.parent.right == node)
                node.parent.right = null;
        }

        if (node.left == null) {
            if (node.parent.right == node)
                node.parent.right = node.right;
            if (node.parent.left == node)
                node.parent.left = node.right;
        }
        if (node.right == null) {
            if (node.parent.left == node)
                node.parent.left = node.left;
            if (node.parent.right == node)
                node.parent.right = node.left;
        }

        if (node.right != null && node.left != null) {
            Node temp = node;
            temp = temp.right;
            while (temp.left != null)
                temp = temp.left;
            node.value = temp.value;
            temp.parent.left = null;
        }
        return true;
    }







    public void backRound() {
        bRound(root);
    }

    public void symmetryRound() {
        sRound(root);
    }

    public Node foundKey(T number) {
        return foundKey(number, root);
    }

    public Node getParent(Node node) {
        if (node != null)
            return node.parent;
        return null;
    }

    public boolean isKeyRemove(T value) {
        Boolean isKeyRemoved = removeKey(value);
        if (isKeyRemoved) foundTreeDeep();
        return isKeyRemoved;
    }


    public Node<T> getRoot() {
        return root;
    }*/



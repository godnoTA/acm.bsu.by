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
        root = new Node<>(rootValue, null, null, null);
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
            if(node == root) {
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
            if(node == root) {
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
            if(node == root) {
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
            if(temp.left == null){
                node.value = temp.value;
                node.right = temp.right;
            }
            else {
                while (temp.left != null)
                    temp = temp.left;
                node.value = temp.value;
                removeKey(temp);
            }
            return true;
        }
return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        Integer key = Integer.parseInt(reader.readLine());
        reader.readLine();
        BinaryTree<Integer> tree = new BinaryTree<>(Integer.parseInt(reader.readLine()));

        while (true) {
            String temp1 = reader.readLine();
            if (temp1 == null) break;
            tree.addKey(Integer.parseInt(temp1));
        }

        tree.removeKey(key);

        StringBuilder stringBuilder = tree.directRound();

        writer.write(stringBuilder.toString());

        writer.close();
 
    }
}
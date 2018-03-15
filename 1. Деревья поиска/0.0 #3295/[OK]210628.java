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

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String temp = reader.readLine();
        if(temp != null) {
            BinaryTree<Integer> tree = new BinaryTree<>(Integer.parseInt(temp));

            while (true) {
                String temp1 = reader.readLine();
                if (temp1 == null) break;
                tree.addKey(Integer.parseInt(temp1));
            }

            StringBuilder stringBuilder = tree.directRound();

            String[] keys = stringBuilder.toString().split("\n");

            long sum = 0;
            for (int i = 0; i < keys.length; ++i) {
                sum += Integer.parseInt(keys[i]);
            }


            writer.write(Long.toString(sum));

        }
        writer.close();
    }
}
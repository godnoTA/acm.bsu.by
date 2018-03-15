import java.io.*;
import java.util.HashSet;

public class Main {

    static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;
    }

    static class Tree{
        private Node root;

        private void pushNode(Node pos, int value)
        {
            if (pos != null)
            {
                if(value < pos.value)
                {
                    if(pos.left == null)
                    {
                        pos.left = new Node();
                        pos.left.value = value;
                        pos.left.parent = pos;
                    }
                    else
                    {
                        pushNode(pos.left, value);
                    }
                }
                else
                {
                    if(pos.right == null)
                    {
                        pos.right = new Node();
                        pos.right.value = value;
                        pos.right.parent = pos;
                    }
                    else
                    {
                        pushNode(pos.right, value);
                    }
                }
            }
        }

        public void addNode(int value)
        {
            if(root == null)
            {
                root = new Node();
                root.value = value;
            }
            else
            {
                pushNode(root, value);
            }
        }

        public void tourTree() {
            PrintWriter bw;
            try {
                bw = new PrintWriter("output.txt");
                searchNodeByValue(root, bw);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void searchNodeByValue(Node pos, PrintWriter bw)
        {
            if (pos != null) {
                A.write(bw, pos.value);

                searchNodeByValue(pos.left, bw);
                searchNodeByValue(pos.right, bw);
            }
        }
    }

    static class A {
        public static void write(PrintWriter bw, int value) {
            bw.println(value);
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            HashSet<Integer> set = new HashSet<Integer>();

            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                int value = Integer.valueOf(line);
                if (!set.contains(value)) {
                    set.add(value);
                    tree.addNode(value);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tree.tourTree();
    }
}


import java.io.*;
import java.util.*;

public class Main {

    static class Node{
        public int value, leftNum = 0, rightNum = 0;
        public Node left;
        public Node right;
        public Node parent;
    }

    static class Tree{
        private Node root;
        private ArrayList<Integer> midValues = new ArrayList<>();

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
                        refreshFor(pos.left);
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
                        refreshFor(pos.right);
                    }
                    else
                    {
                        pushNode(pos.right, value);

                    }

                }
            }
        }

        private void refreshFor(Node currentNode) {
            Node node = currentNode, parent;
            while (node != root) {
                parent = node.parent;
                if (parent.left == node)
                    parent.leftNum++;
                if (parent.right == node)
                    parent.rightNum++;
                node = parent;
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

        public void rightRemoveNode(int value)
        {
            Node x = root, prevNode = null;
            while (x != null)
            {
                if (x.value == value) break;
                prevNode = x;
                if (x.value > value) x = x.left;
                else x = x.right;
            }
            if (x == null) return;
            if (x.right == null)
            {
                if (prevNode == null)
                {
                    root = x.left;
                }
                else if (x == prevNode.right) prevNode.right = x.left;
                else prevNode.left = x.left;
            }
            else
            {
                Node temp = x.right;
                Node ourLeaf = null;
                while (temp.left != null)
                {
                    ourLeaf = temp;
                    temp = temp.left;
                }
                if (ourLeaf != null)
                {
                    ourLeaf.left = temp.right;
                }
                else
                {
                    x.right = temp.right;
                }
                x.value = temp.value;
            }
        }

        public void tourTree() {
            PrintWriter bw;
            try {
                bw = new PrintWriter(outputName);
                search(root, bw);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void search(Node pos, PrintWriter bw)
        {
            if (pos != null) {
                A.write(bw, pos.value);
                if (Math.abs(pos.leftNum - pos.rightNum) == 1)
                    midValues.add(pos.value);
                search(pos.left, bw);
                search(pos.right, bw);
            }
        }

        public ArrayList<Integer> findMidValues() {
            search(root, null);
            return midValues;
        }

    }

    static class A {
        public static void write(PrintWriter bw, int value) {
            if (bw != null)
                bw.println(value);
        }
    }

    private static final String inputName = "in.txt", outputName = "out.txt";

    public static void main(String[] args) {
        Tree tree = new Tree();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputName));
            TreeSet<Integer> set = new TreeSet<>();

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

            ArrayList<Integer> midValues = tree.findMidValues();
            if (midValues.size() % 2 == 1) {
                Collections.sort(midValues);
                Integer valueToDel = midValues.get(midValues.size() / 2);
                //System.out.println(valueToDel);
                if (valueToDel != null)
                    tree.rightRemoveNode(valueToDel);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tree.tourTree();
    }
}


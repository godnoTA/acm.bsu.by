import java.io.*;

import java.io.BufferedWriter;
import java.io.IOException;

class BinaryTree {
    private Node root;
    private BufferedWriter bw;

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    public static class Node {
        long key;

        Node left, right, parent;

        Node(long key) {
            this.key = key;
        }
    }

    public void addToTree(long key) {
        Node nodeToAdd = new Node(key);
        addToTree(nodeToAdd);
    }

    public void addToTree(Node nodeToAdd) {
        Node y = null;
        Node x = this.root;

        while (x != null) {
            y = x;

            long compareResult = nodeToAdd.key - x.key;

            if (compareResult < 0) {
                x = x.left;
            } else if (compareResult > 0){
                x = x.right;
            } else {
                break;
            }
        }

        nodeToAdd.parent = y;

        // in case if tree was empty;

        if (y == null) {
            this.root = nodeToAdd;
        } else {
            long compareResult = nodeToAdd.key - y.key;

            if (compareResult < 0) {
                y.left = nodeToAdd;
            } else if (compareResult > 0){
                y.right = nodeToAdd;
            }
        }
    }

    public void preOrderTreeWalk(Node root) throws IOException {
        if (root != null) {
            bw.append(String.valueOf(root.key));
            bw.append('\n');
            preOrderTreeWalk(root.left);
            preOrderTreeWalk(root.right);
        }
    }

    public Node getRoot() {
        return root;
    }
}

public class Main {
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        try {
            FileWriter fwr = new FileWriter("output.txt", true);
            BufferedWriter writer = new BufferedWriter(fwr);

            tree.setBw(writer);

            FileReader fr = new FileReader("input.txt");
            BufferedReader br =
                    new BufferedReader(fr);

            String s = null;
            while((s = br.readLine()) != null) {
                long number = Long.valueOf(s);
                tree.addToTree(number);
            }
            br.close();

            tree.preOrderTreeWalk(tree.getRoot());

            writer.close();

        } catch(FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
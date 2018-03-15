import java.io.*;

import java.io.BufferedWriter;
import java.io.IOException;

class BinaryTree {
    private Node root;
    private BufferedWriter bw;

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    public void setRoot(Node root){
        this.root = root;
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
    public void deleteFromTree(Node nodeToDelete) {
        if (nodeToDelete == null) return;

        if (nodeToDelete == getRoot()) {
            root = null;
            return;
        }

        Node parent = nodeToDelete.parent;

        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (nodeToDelete.parent.left == nodeToDelete) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (nodeToDelete.left == null) {
            replaceNode(nodeToDelete, nodeToDelete.right);
        } else if (nodeToDelete.right == null) {
            replaceNode(nodeToDelete, nodeToDelete.left);
        } else {
            Node minimumNode = findMinimum(nodeToDelete.right);
            if (minimumNode.parent != nodeToDelete) {
                replaceNode(minimumNode, minimumNode.right);
                minimumNode.right = nodeToDelete.right;
                minimumNode.right.parent = minimumNode;
            }
            replaceNode(nodeToDelete, minimumNode);
            minimumNode.left = nodeToDelete.left;
            minimumNode.left.parent = minimumNode;
        }
    }

    public Node deleteRecursion(Node root, long key) {
        if (root == null) return null;
        if (key < root.key) {
            root.left = deleteRecursion(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRecursion(root.right, key);
        } else if (root.left != null && root.right != null) {
            Node minimumNode = findMinimum(root.right);
            root.key = minimumNode.key;
            root.right = deleteRecursion(root.right, root.key);
        } else if (root.right != null) {
            root = root.right;
        } else {
            root = root.left;
        }
        return root;
    }

    public void replaceNode(Node a, Node b) {
        if (root == a) {
            root = b;
            return;
        }
        if (a == a.parent.left) {
            a.parent.left = b;
        } else {
            a.parent.right = b;
        }
        if (b != null) {
            b.parent = a.parent;
        }
    }

    public Node findMinimum(Node node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node findNode(Node x, long key) {
        if (x == null || x.key - key == 0) {
            return x;
        }
        long compareResult = key - x.key;
        if (compareResult < 0) {
            return findNode(x.left, key);
        } else {
            return findNode(x.right, key);
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

            String s;

            long keyToDelete = Long.valueOf(br.readLine());
            br.readLine();
            while((s = br.readLine()) != null) {
                long number = Long.valueOf(s);
                tree.addToTree(number);
            }
            br.close();

            //tree.deleteFromTree(tree.findNode(tree.getRoot(), keyToDelete));

            tree.setRoot(tree.deleteRecursion(tree.getRoot(), keyToDelete));
            tree.preOrderTreeWalk(tree.getRoot());

            writer.close();

        } catch(FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


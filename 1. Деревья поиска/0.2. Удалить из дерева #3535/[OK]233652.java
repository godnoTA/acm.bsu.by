import java.io.*;
import java.util.*;
class Node {
    long key;
    Node leftChild;
    Node rightChild;

    Node (long key) {
        this.key=key;
    }

}
public class Main implements Runnable {
    public static void main(String[] args)  {
        new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
    }
    
    Node root;

    public void addNode(long key) {
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
        } else {
            Node centerNode = root;
            Node parent;
            while (true) {
                parent = centerNode;
                if (key == centerNode.key) {
                    break;
                }
                if (key < centerNode.key) {
                    centerNode = centerNode.leftChild;
                    if (centerNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    centerNode = centerNode.rightChild;
                    if (centerNode == null) {
                        parent.rightChild = newNode;
                        return;

                    }
                }
            }
        }
    }

    public void OrderTree(Node centerNode, FileWriter writer) throws IOException {
        if (centerNode != null) {
            writer.write(Long.toString(centerNode.key) + "\n");

            OrderTree(centerNode.leftChild, writer);

            OrderTree(centerNode.rightChild, writer);

        }
    }





    public boolean delete(long key) {


        Node focusNode = root;
        Node parent = root;
        boolean isLeft = true;
        while (focusNode.key != key) {
            parent = focusNode;
            if (key < focusNode.key) {
                isLeft = true;
                focusNode = focusNode.leftChild;
            } else {
                isLeft = false;
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null)
                return false;
        }


        if (focusNode.leftChild == null && focusNode.rightChild == null) {
            if (focusNode == root)
                root = null;
            else if (isLeft)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }

        else if (focusNode.rightChild == null) {
            if (focusNode == root)
                root = focusNode.leftChild;
            else if (isLeft)
                parent.leftChild = focusNode.leftChild;
            else
                parent.rightChild = focusNode.leftChild;
        }

        else if (focusNode.leftChild == null) {
            if (focusNode == root)
                root = focusNode.rightChild;
            else if (isLeft)
                parent.leftChild = focusNode.rightChild;
            else
                parent.rightChild = focusNode.rightChild;
        }

        else {
            Node replacement = getReplacementNode(focusNode);
            if (focusNode == root)
                root = replacement;
            else if (isLeft)
                parent.leftChild = replacement;
            else
                parent.rightChild = replacement;
            replacement.leftChild = focusNode.leftChild;
        }
        return true;
    }

    public Node getReplacementNode(Node replacedNode) {
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;
        Node focusNode = replacedNode.rightChild;

        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;
        }


        if (replacement != replacedNode.rightChild) {
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;
        }
        return replacement;
    }

    public void run() {
        {
            long key;
            Main theTree = new Main();
            Scanner in = null;
            try {
                in = new Scanner(new File("input.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileWriter writer = null;
            try {
                writer = new FileWriter("output.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            key = Integer.parseInt(in.nextLine());
            String line = in.nextLine();
            while (in.hasNextLine()) {
                theTree.addNode(Integer.parseInt(in.nextLine()));
            }

            theTree.delete(key);
            try {
                theTree.OrderTree(theTree.root, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // theTree.delete(theTree.root, key);
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            in.close();
        }
    }
}
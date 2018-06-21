import java.io.*;
import java.util.*;
class Node {
    long key;
    Node leftChild;
    Node rightChild;

    Node (int key) {
        this.key=key;
    }

}
public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
    }

    Node root;

    public void addNode(int key) {
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

    public void run() {
        {
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

            while (in.hasNextLine()) {
                theTree.addNode(Integer.parseInt(in.nextLine()));
            }
            try {
                theTree.OrderTree(theTree.root, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            in.close();
        }

    }
}
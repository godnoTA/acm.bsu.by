import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class BinaryNode {
    Integer value;
    int Height, LeftLength, RightLength;
    BinaryNode left, right, parent;

    public BinaryNode(Integer temp,BinaryNode parent) {
        value = temp;
        LeftLength = RightLength = Height = 0;
        left = right = null;
        this.parent = parent;
    }

}

class BinaryTree {
    BinaryNode root;
    FileWriter writer;

    public BinaryTree() {
        root = null;
        try {
            writer = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rootLeftRight() {
        rootLeftRight(root);
    }

    private void rootLeftRight(BinaryNode bN) {
        if (bN != null) {
            try {
                writer.write(bN.value + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            rootLeftRight(bN.left);
            rootLeftRight(bN.right);
        }
    }

    public void insert(int data) {
        root = insert(root, data, null);
    }

    private BinaryNode insert(BinaryNode current, int data, BinaryNode parent) {
        if (current == null) {
            current = new BinaryNode(data, parent);
        }
        if (data < current.value) {
            current.left = insert(current.left, data, current);
        } else if (data > current.value) {
            current.right = insert(current.right, data, current);
        }
        return current;
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        BinaryTree tree = new BinaryTree();

        Scanner scan = new Scanner(new File("input.txt"));

        while (scan.hasNextLong())
            tree.insert(scan.nextInt());

        tree.rootLeftRight();
        scan.close();
    }
}
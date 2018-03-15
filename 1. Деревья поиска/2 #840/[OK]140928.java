import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.io.File;

public class Tree {
    public static void main(String[] args) throws IOException {
        BinaryTree myTree = new BinaryTree();
        BinaryTree tree = new BinaryTree();
        Scanner sc = new Scanner(new File("in.txt"));
        while (sc.hasNextInt()) {
            int element=sc.nextInt();
            if(myTree.find(element)==null) {
                myTree.insert(element);
            }
        }
        sc.close();
        Writer out = new FileWriter("out.txt");
        if ((myTree.getRoot()!=null)&&(myTree.root.right == null) && (myTree.root.left == null)) {
            {
                myTree.delete(myTree.root.data);
            }

            myTree.nodeLeftRight(myTree.getRoot(), out);
        } else {
            myTree.inOrder(myTree.root, tree);
            long count = myTree.counter / 2;
            for (int i = 0; i < count; i++) {
                Node min = tree.minimum();
                Node max = tree.maximum();
                tree.delete(min.data);
                tree.delete(max.data);
            }
            if (tree.root != null)//существует средняя по значению вершина
            {
                myTree.delete(tree.root.data);
            }
            myTree.nodeLeftRight(myTree.getRoot(), out);
        }

        out.close();
    }


}

class Node//узел дерева
{
    long data;//информационная часть
    Node left;//левый потомок
    Node right;//правый потомок

    Node(long data) {
        this.data = data;
        left = null;
        right = null;
    }

    public long Height(Node root)//высота вершины
    {
        long h1 = 0;
        long h2 = 0;
        if (root == null) {
            return 0;
        }
        if (root.left != null) {
            h1 = Height(root.left);
        }
        if (root.right != null) {
            h2 = Height(root.right);
        }
        return Math.max(h1, h2) + 1;

    }

}

class BinaryTree {
    public Node root;//храним только корень, доступ к остальным элементам через него
    public long counter;

    Node getRoot() {
        return root;
    }

    public BinaryTree() {
        root = null;
        counter = 0;
    }

    public void insert(long id) {
        Node newNode = new Node(id);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (id < current.data) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }
    public Node find(int key)
    {
        Node current = root;
        if(root==null)
        {
            return null;
        }
        while(current.data != key)
        {
            if(key < current.data)
                current = current.left;
            else
                current = current.right;
            if(current == null)
                return null;
        }
        return current;
    }

    public void nodeLeftRight(Node root, Writer out) throws IOException {
        if (root != null) {
            out.write(root.data+System.lineSeparator());
            nodeLeftRight(root.left, out);
            nodeLeftRight(root.right, out);
        }
    }

    public void inOrder(Node localRoot, BinaryTree tree) {
        if (localRoot != null) {
            inOrder(localRoot.left, tree);
            if (localRoot.Height(localRoot.left) != localRoot.Height(localRoot.right)) {
                tree.insert(localRoot.data);
                counter++;
            }
            inOrder(localRoot.right, tree);
        }

    }

    public Node getSuccessor(Node delNode) {
        Node successor = delNode;
        if (delNode == null) {
            return null;
        }
        Node current = delNode.right;
        while (current != null) {
            successor = current;
            current = current.left;
        }
        return successor;
    }

    public void delete(long key) {
        Node current = root;
        Node parent = root;
        if (root == null) {
            return;
        }
        boolean isLeftChild = true;
        while (current.data != key) {
            parent = current;

            if (key < current.data) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return;
            }
        }

        if (current.left == null &&
                current.right == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        } else if (current.right == null)
            if (current == root)
                root = current.left;
            else if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        else if (current.left == null)
            if (current == root)
                root = current.right;
            else if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        else {
            Node successor = getSuccessor(current);
            long dat = successor.data;
            delete(successor.data);
            current.data = dat;

        }

    }

    public Node minimum() {
        Node current;
        Node last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.left;
        }
        return last;
    }

    public Node maximum() {
        Node current;
        Node last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.right;
        }
        return last;
    }


}


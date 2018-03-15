import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Node<T extends Comparable> {
    T iData;
    Node<T> leftChild;
    Node<T> rightChild;

    Node(T id) {
        this.iData = id;
        this.leftChild = null;
        this.rightChild = null;
    }
}

class Tree<T extends Comparable> implements Cloneable {
    private Node<T> root;
    String str = "";

    Tree() {
        root = null;
    }


    void insert(T id) {
        Node newNode = new Node( id );
        if (root != null)
            insertTo( newNode, root );
        else
            root = newNode;
    }


    private void insertTo(Node<T> newNode, Node current) {
        if (newNode.iData.compareTo( current.iData ) < 0)
            if (current.leftChild == null) {
                current.leftChild = newNode;
            } else {
                insertTo( newNode, current.leftChild );
            }
        if (0 < newNode.iData.compareTo( current.iData )) {
            if (current.rightChild == null) {
                current.rightChild = newNode;
            } else {
                insertTo( newNode, current.rightChild );
            }
        }
    }

    void traverse() {
        preOrder( root );
    }

    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            str += localRoot.iData + "\n";
            preOrder( localRoot.leftChild );
            preOrder( localRoot.rightChild );
        }
    }

    private Node minimum(Node x) {
        if (x.leftChild == null)
            return x;
        return minimum( x.leftChild );
    }

    void delete(T z) {
        root = delete( root, z );
    }

    private Node delete(Node node, T z) {
        if (node == null)
            return node;
        if (z.compareTo( node.iData ) < 0)
            node.leftChild = delete( node.leftChild, z );

        else if (z.compareTo( node.iData ) > 0)
            node.rightChild = delete( node.rightChild, z );
        else {
            if (node.leftChild != null && node.rightChild != null) {
                node.iData = (T) minimum( node.rightChild ).iData;
                node.rightChild = delete( node.rightChild, (T) node.iData );
            } else if (node.leftChild != null)
                node = node.leftChild;
            else
                node = node.rightChild;
        }
        return node;
    }

}

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        try {
            try (FileWriter writer = new FileWriter( "output.txt" )) {
                Scanner sc = new Scanner( new FileReader( "input.txt" ) );
                int number =Integer.valueOf( sc.nextLine());
                sc.nextLine();
                while (sc.hasNext()) {
                    tree.insert( Integer.valueOf( sc.nextLine() ) );
                }

                tree.delete( number );
                tree.traverse();
                writer.write( tree.str );
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
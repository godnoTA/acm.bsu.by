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
    StringBuilder builder = new StringBuilder("");

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
           builder.append(localRoot.iData + "\n" );
            preOrder( localRoot.leftChild );
            preOrder( localRoot.rightChild );
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        try {
            try (FileWriter writer = new FileWriter( "output.txt" )){
                Scanner sc = new Scanner( new FileReader( "input.txt" ) );
                while (sc.hasNext()) {
                    tree.insert( Integer.valueOf( sc.nextLine() ) );
                }
                tree.traverse();
                writer.write( tree.builder.toString() );
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}

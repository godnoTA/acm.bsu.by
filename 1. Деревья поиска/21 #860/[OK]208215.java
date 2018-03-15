import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    ArrayList<Node> arr1 = new ArrayList<>();
    ArrayList<Integer> arr2 = new ArrayList<>();

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

    private int heightOfBinaryTree(Node node) {
        if (node == null) {
            return 0;
        } else {

            if (node.leftChild == null || node.rightChild == null) {
                return -1;
            } else if (heightOfBinarySubtree( node.leftChild ) == heightOfBinarySubtree( node.rightChild ))
                return 1;
            else
                return 2;
        }
    }

    private int heightOfBinarySubtree(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max( heightOfBinarySubtree( node.leftChild ), heightOfBinarySubtree( node.rightChild ) );
        }

    }

    private void equalHeight(Node localRoot) {
        if (localRoot != null) {
            if (heightOfBinaryTree( localRoot ) == 1) {
                arr1.add( localRoot );
            }
            equalHeight( localRoot.leftChild );
            equalHeight( localRoot.rightChild );
        }
    }

    void heightOfBinarySubtree() {
        equalHeight( root );
    }

    int amount() {
        for (int i = 0; i < arr1.size(); i++) {
            if (amount( arr1.get( i ).rightChild ) == amount( arr1.get( i ).leftChild )) {
                arr1.remove( i );
                i--;
            } else
                arr2.add( (Integer) arr1.get( i ).iData );
        }
        Collections.sort( arr2 );
        if ((arr2.size() - 1) % 2 == 0)
            return arr2.get( arr2.size() / 2 );
        return Integer.MAX_VALUE;
    }

    private int amount(Node node) {
        if (node == null)
            return 0;
        else {
            return 1 + amount( node.leftChild ) + amount( node.rightChild );
        }

    }
}

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        try {
            try (FileWriter writer = new FileWriter( "out.txt" )) {
                Scanner sc = new Scanner( new FileReader( "in.txt" ) );
                while (sc.hasNext()) {
                    tree.insert( Integer.valueOf( sc.nextLine() ) );
                }
                tree.heightOfBinarySubtree();
                tree.delete( tree.amount() );
                tree.traverse();
                writer.write( tree.builder.toString() );
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
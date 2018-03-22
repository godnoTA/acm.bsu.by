import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Long;


class Tree{

    private class Node {
        long iData;
        Node leftChild;
        Node rightChild;

        Node(long id) {
            this.iData = id;
            this.leftChild = null;
            this.rightChild = null;
        }
    }
    private Node root;
    StringBuilder builder = new StringBuilder("");

    void insert(long id) {
        Node newNode = new Node( id );
        if (root != null)
            insertTo( newNode, root );
        else
            root = newNode;
    }

    private void insertTo(Node newNode, Node current) {
        switch (Long.compare(newNode.iData, current.iData)) {
            case -1: {
                if (current.leftChild == null)
                    current.leftChild = newNode;
                 else
                    insertTo( newNode, current.leftChild );

                break;
            }
            case 1: {
                if (current.rightChild == null)
                    current.rightChild = newNode;
                else
                    insertTo( newNode, current.rightChild );

                break;
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

    void delete(long z) {
        root = delete( root, z );
    }

    private Node delete(Node node, long z) {
        if (node == null)
            return node;
        switch (Long.compare(z, node.iData)) {
            case -1:
                node.leftChild = delete( node.leftChild, z );
                return node;
            case 1:
                node.rightChild = delete( node.rightChild, z );
                return node;
            case 0:
                if (node.leftChild != null && node.rightChild != null) {
                    node.iData =  minimum( node.rightChild ).iData;
                    node.rightChild = delete( node.rightChild,  node.iData );
                    return node;
                }
                else if (node.leftChild != null) {
                    node = node.leftChild;
                    return node;
                }
                else {
                    node = node.rightChild;
                    return node;
                }
        }
        return null;
    }


}

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run()  {
        Tree tree = new Tree();
        try {
            try (FileWriter writer = new FileWriter( "output.txt" )) {
                Scanner sc = new Scanner( new FileReader( "input.txt" ) );
                Long number =Long.valueOf( sc.nextLine());
                sc.nextLine();
                while (sc.hasNext()) {
                    tree.insert( Long.valueOf( sc.nextLine() ) );
                }

                tree.delete( number );
                tree.traverse();
                writer.write( tree.builder.toString() );
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
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
public class Main {
    Node root;

    public void addNode (int key) {
        Node newNode = new Node(key);
        if(root==null) {
            root=newNode;
        }
        else {
            Node centerNode=root;
            Node parent;
            while (true) {
                parent=centerNode;
                if (key==centerNode.key){
                    break;
                }
                if(key<centerNode.key) {
                    centerNode=centerNode.leftChild;
                    if (centerNode==null)
                    {
                        parent.leftChild=newNode;
                        return;
                    }
                } else {
                    centerNode=centerNode.rightChild;
                    if(centerNode==null) {
                        parent.rightChild=newNode;
                        return;

                    }
                }
            }
        }
    }
    public void OrderTree (Node centerNode, FileWriter writer) throws IOException {
        if(centerNode!=null) {
            writer.write (Long.toString(centerNode.key)+"\n");

            OrderTree(centerNode.leftChild, writer);

            OrderTree(centerNode.rightChild, writer);

        }
    }
    public static void main (String[] args) throws IOException{
        Main theTree = new Main();
        Scanner in = new Scanner(new File("input.txt"));
        FileWriter writer = new FileWriter("output.txt");

        while (in.hasNextLine()) {
            theTree.addNode(Integer.parseInt(in.nextLine()));
        }
        theTree.OrderTree(theTree.root, writer);

        writer.close();
        in.close();
    }

}
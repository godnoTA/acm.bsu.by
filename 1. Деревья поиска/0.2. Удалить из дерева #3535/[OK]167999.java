import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {


        Scanner sc = new Scanner(new File("input.txt"));
        PrintStream wr = new PrintStream("output.txt");
        Integer node;
        node = sc.nextInt();
        sc.nextLine();
        Tree tree = new Tree(sc.nextInt());
        while (sc.hasNext()) {
            tree.add (sc.nextInt());
        }
        tree.rightDelete(node);
        tree.print (tree.root, wr);

        sc.close();
        wr.close();
    }
}


class Tree {
    private class Node {
        private Integer key;
        private Node left;
        private Node right;

        public Node(Integer key) {
            this.key = key;
            left = null;
            right = null;
        }

    }

    Node root;

    public Tree(Integer n) {
        root = new Node(n);
    }

    public boolean add(Integer newEl)  {
        if (root == null) {
            root = new Node(newEl);
        } else {
            Node work = root;
            Node nodeFin = null;
            while (work != null) {
                nodeFin = work;
                if (work.key < newEl) {
                    work = work.right;
                } else if (work.key > newEl) {
                    work = work.left;
                } else
                    if (work.key == newEl){
return false;
}
            }
            Node newNode = new Node(newEl);
            if (nodeFin.key < newNode.key) {
                nodeFin.right = newNode;
            } else
                nodeFin.left = newNode;
        }
return true;

    }

    public void print(Node node, PrintStream wr) throws Exception {
        if (node != null) {
            wr.println(node.key);
            print(node.left, wr);
            print(node.right, wr);
        }
    }
public boolean rightDelete(Integer value) {
        Node iter = root;
        Node parent = null;
        while (iter != null && iter.key != value) {
            parent = iter;
            if (value > iter.key) {
                iter = iter.right;
            } else if (value < iter.key) {
                iter = iter.left;
            } else if (value == iter.key) {
                break;
            }
        }
        if (iter == null) {
            return false;
        } else if (iter.right == null && iter.left == null) {
            deleteLeaf(iter, parent);
        } else if (!(iter.right != null && iter.left != null)) {
            deleteOneChildVertex(iter, parent);
        } else {
            deleteTwoChildVertex(iter, parent);
        }
        return true;
    }

    private void deleteLeaf(Node leaf, Node parent) {
        if(parent != null) {
            if (parent.key < leaf.key) {
                parent.right = null;
            } else {
                parent.left = null;
            }
        } else {
            root = null;
        }
    }

    private void deleteOneChildVertex(Node node, Node parent) {
        Node next;
        if (node.right != null) {
            next = node.right;
        } else {
            next = node.left;
        }
        if(parent != null) {
            if (parent.key < node.key) {
                parent.right = next;
            } else {
                parent.left = next;
            }
        } else {
            if(node.right != null) {
                root = node.right;
            } else {
                root = node.left;
            }
        }
    }

    private void deleteTwoChildVertex(Node node, Node parent) {
        Node next;
        Node nextParent;
        next = node.right;
        nextParent = node;
        while (next.left != null) {
            nextParent = next;
            next = next.left;
        }
        if (next.right != null) {
            deleteOneChildVertex(next, nextParent);
        } else {
            deleteLeaf(next, nextParent);
        }
        next.left = node.left;
        if(node.right != next) {
            next.right = node.right;
        } else {
            next.right = null;
        }
        if(parent != null) {
            if (parent.key < next.key) {
                parent.right = next;
            } else {
                parent.left = next;
            }
        } else {
            root = next;
        }
    }
}


 

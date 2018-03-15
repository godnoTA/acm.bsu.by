import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Tree {
    public class Node {
        private Long  value;
        private Node left;
        private Node right;

        public Node(Long value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;

    public Tree(Long value) {
        this.root = new Node(value);
    }

    public Tree(Node root) {
        this.root = root;
    }

    public boolean find(Long value) {
        Node current = this.root;
        while (current != null) {
            if (current.value.compareTo(value) == 0) {
                return true;
            } else if (value.compareTo(current.value) < 0) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        return false;

    }

    public void insert(Long value) {
        Node toBeInserted = new Node(value);
        if(root == null) {
            root = toBeInserted;
        }
        else {
            Node parent = null;
            Node current = root;
            while(current != null) {
                if(current.value.compareTo(value) == 0) {
                    current = toBeInserted;
                    return;
                }
                else if(value.compareTo(current.value) < 0){
                    parent = current;
                    current = current.left;
                }
                else {
                    parent = current;
                    current = current.right;
                }

            }
            if(value.compareTo(parent.value) < 0) parent.left = toBeInserted;
            else if (value.compareTo(parent.value) > 0)  parent.right = toBeInserted;
        }

    }

    private void preOrderTraverse(Node node) {
        if(node != null) {
            System.out.print(node.value.toString() + " ");
            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
        }
    }

    public void preOrderTraverse() { // Straight ( Root Left Right )
        if(root != null) {
            System.out.print(root.value.toString() + " ");
            preOrderTraverse(root.left);
            preOrderTraverse(root.right);
        }
    }

    private void inOrderTraverse(Node node) {
        if(node != null) {
            inOrderTraverse(node.left);
            System.out.print(node.value.toString() + " ");
            inOrderTraverse(node.right);
        }
    }

    public void inOrderTraverse() { // Symmetric ( Left Root Right )
        if(root != null) {
            inOrderTraverse(root.left);
            System.out.print(root.value.toString() + " ");
            inOrderTraverse(root.right);
        }
    }

    private void postOrderTraverse(Node node) {
        if(node != null) {
            postOrderTraverse(node.left);
            postOrderTraverse(node.right);
            System.out.print(node.value.toString() + " ");
        }
    }

    public void postOrderTraverse() { // Reversed ( Left Right Root )
        if(root != null) {
            postOrderTraverse(root.left);
            postOrderTraverse(root.right);
            System.out.print(root.value.toString() + " ");
        }
    }

    public void delete(Long value) {
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            if (value.compareTo(current.value) == 0) {
                break;
            } else if (value.compareTo(current.value) > 0) {
                parent = current;
                current = current.right;
            } else {
                parent = current;
                current = current.left;
            }
        }
        if(current != null) {
            if(current.left == null && current.right == null) { // No Children
                if(parent == null) {
                    root = null;
                    return;
                }
                if(parent.left == current) {
                    parent.left = null;
                }
                else {
                    parent.right = null;
                }
            }
            else if( (current.left == null) != (current.right == null) ) { // One Child
                if(current.left != null) {
                    if(parent == null) {
                        root = current.left;
                        return;
                    }
                    if(parent.left == current) {
                        parent.left = current.left;
                    }
                    else {
                        parent.right = current.left;
                    }
                }
                else {
                    if(parent == null) {
                        root = current.right;
                        return;
                    }
                    if(parent.left == current) {
                        parent.left = current.right;
                    }
                    else {
                        parent.right = current.right;
                    }
                }
            }
            else { // Two Children
                Node nextParent = current;
                Node nextCurrent = current.right;
                while(nextCurrent.left != null) {
                    nextParent = nextCurrent;
                    nextCurrent = nextCurrent.left;
                }
                Long buf = nextCurrent.value;
                delete(buf);
                current.value = buf;

            }


        }
    }

    private void preOrderTraverseToFile(Node node,FileWriter writer) throws IOException {
        if(node != null) {
            writer.write(node.value.toString() + "\n");
            preOrderTraverseToFile(node.left,writer);
            preOrderTraverseToFile(node.right,writer);
        }
    }

    public void preOrderTraverseToFile(FileWriter writer) throws IOException {
        if(root != null) {
            writer.write(root.value.toString() + "\n");
            preOrderTraverseToFile(root.left,writer);
            preOrderTraverseToFile(root.right,writer);
        }
    }


    public static void main(String[] args) {
        boolean isFirstTime = true;
        Tree tree = null;
        File file = new File("input.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                if(isFirstTime) {
                    isFirstTime = false;
                    long temp = scanner.nextLong();
                    tree = new Tree(temp);
                }
                else {
                    long temp = scanner.nextLong();
                    tree.insert(temp);
                }

            }


        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        try {
            FileWriter writer = new FileWriter("output.txt");
            tree.preOrderTraverseToFile(writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        } catch (NullPointerException e) {
            System.out.println("Somehing Went Wrong");
        }


    }
}

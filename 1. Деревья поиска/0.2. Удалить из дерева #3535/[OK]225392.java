import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Runnable {
    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "", 10 * 1024 * 1024).start();


    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            FileWriter fw = new FileWriter("output.txt");
            String str;
            if ((str = br.readLine()) != null) {
                int keyForRemove = Integer.parseInt(str);
                br.readLine();
                MyTree tree = new MyTree(new Node(Integer.parseInt(br.readLine())));
                while ((str = br.readLine()) != null)
                    tree.insert(Integer.parseInt(str), tree.root);
                tree.rightRemove(keyForRemove);
                fw.append(tree.getStringFromDirectLeft());
            }

            fw.close();
        } catch (Exception ignore) {
            System.out.println("Oh, fuck");
        }

    }

}

class MyTree {
    Node root;

    MyTree(Node root) {
        this.root = root;
    }
    

    boolean insert(int key, Node node) {
        if (node.key == key)
            return false;
        if (key < node.key) {
            if (node.left != null) insert(key, node.left);
            else node.left = new Node(key, node);
        } else {
            if (node.right != null) insert(key, node.right);
            else node.right = new Node(key, node);
        }
        return true;
    }

    void rightRemove(int key) {
        try {
            Node rem = findNode(key, root);
            if (rem.parent == null) {
                if (rem.right != null) {
                    if (rem.left == null)
                        root = rem.right;
                    else root.key = findAndRemoveMinRight(rem.right).key;
                    return;
                } else {
                    root = rem.left;
                    return;
                }

            }
            if (rem.left == null && rem.right == null) {
                if (rem.parent.left != null) {
                    if (rem.parent.left.equals(rem)) {
                        rem.parent.left = null;
                        return;
                    } else if (rem.parent.right != null) {
                        if (rem.parent.right.equals(rem)) {
                            rem.parent.right = null;
                            return;
                        }
                    }
                }
                if (rem.parent.right != null) {
                    if (rem.parent.right.equals(rem)) {
                        rem.parent.right = null;
                        return;
                    }
                }
                return;
            }
            if (rem.left == null) {
                if (rem.parent.left != null)
                    if (rem.parent.left.equals(rem))
                        rem.parent.left = rem.right;
                    else rem.parent.right = rem.right;
                else rem.parent.right = rem.right;
                return;
            }
            if (rem.right == null) {
                if (rem.parent.left != null)
                    if (rem.parent.left.equals(rem))
                        rem.parent.left = rem.left;
                    else rem.parent.right = rem.left;
                else rem.parent.right = rem.left;
                return;
            }
            rem.key = findAndRemoveMinRight(rem.right).key;

        } catch (NoNodeWithKeyException e) {
            e.printStackTrace();
        }

    }


    Node findNode(int key, Node currentNode) throws NoNodeWithKeyException {
        try {
            if (key == currentNode.key)
                return currentNode;
            if (key < currentNode.key)
                return findNode(key, currentNode.left);
            return findNode(key, currentNode.right);
        } catch (NullPointerException e) {
            throw new NoNodeWithKeyException(key);
        }
    }


    Node findAndRemoveMinRight(Node node) {
        
        if (node.left != null) return findAndRemoveMinRight(node.left);
        if (node.parent.left != null)
            if (node.parent.left.equals(node)) {
                node.parent.left = node.right;
                return node;
            }
        node.parent.right = node.right;
        return node;
    }


    ArrayList<Node> directLeft(Node node) {
        try {
            ArrayList<Node> res = new ArrayList<>();
            if (root.equals(node))
                res.add(node);
            if (node.left != null) {
                res.add(node.left);
                res.addAll(directLeft(node.left));
            }
            if (node.right != null) {
                res.add(node.right);
                res.addAll(directLeft(node.right));
            }
            return res;
        } catch (NullPointerException e) {
            return new ArrayList<Node>();
        }
    }

    String getStringFromDirectLeft() {
        ArrayList<Node> nodes = directLeft(root);
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes)
            sb.append(n.key).append("\n");
        return sb.toString();
    }
}

class Node {
    int key;
    Node left;
    Node right;
    Node parent;
    int height;

    Node(int key) {
        this.key = key;
    }

    public Node(int key, Node parent) {
        this.key = key;
        this.parent = parent;
    }

    public Node(int key, Node left, Node right, Node parent, int height) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return key == node.key;
    }

    @Override
    public int hashCode() {
        return key;
    }
}

class NoNodeWithKeyException extends Exception {
    int key;

    public NoNodeWithKeyException(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "NoNodeWithKeyException: " +
                "key = " + key;
    }
}
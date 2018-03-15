import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.max;

public class Main implements Runnable  {

    public static void main(String[] args)  {

        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {

        Scanner sc = null;
        try {
            sc = new Scanner(new File("tst.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream wr = null;
        try {
            wr = new PrintStream("tst.out");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Integer> list = new ArrayList<>();


        Tree tree = new Tree(sc.nextInt());
        while (sc.hasNext()) {
            tree.add (sc.nextInt());
        }

        try {
            tree.LRC(tree.root,wr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            tree.LRC1(tree.root,wr,list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(list.size()%2 != 0){

            tree.rightDelete(list.get(list.size()/2));
        }

        try {
            tree.print (tree.root, wr);
        } catch (Exception e) {
            e.printStackTrace();
        }


        sc.close();
        wr.close();
       System.exit(0);


    }



}

class Tree {

    private class Node {
        private int key, h;
        private Node left;
        private Node right;


        public Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }

    }

    Node root;

    public Tree(int n) {
        root = new Node(n);
    }

    public boolean add(Integer newEl) {
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
                } else if (work.key == newEl) {
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
        if (parent != null) {
            if (parent.key < leaf.key) {
                parent.right = null;
            } else {
                parent.left = null;
            }
        } else {
            root = null;
        }
    }

    public int LRC(Node node, PrintStream wr) throws Exception {
        if (node == null) {
            return -1;
        } else
            return node.h = max(LRC(node.left, wr), LRC(node.right, wr)) + 1;

    }

    public void LRC1(Node node, PrintStream wr, List<Integer> list) throws Exception {
        if (node != null) {

            LRC1(node.left, wr, list);
            if (node.left != null && node.right != null && node.left.h == node.right.h || node.left == null && node.right == null) {
                list.add(node.key);


            }
            LRC1(node.right, wr, list);
        }

    }

    private void deleteOneChildVertex(Node node, Node parent) {
        Node next;
        if (node.right != null) {
            next = node.right;
        } else {
            next = node.left;
        }
        if (parent != null) {
            if (parent.key < node.key) {
                parent.right = next;
            } else {
                parent.left = next;
            }
        } else {
            if (node.right != null) {
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
        if (node.right != next) {
            next.right = node.right;
        } else {
            next.right = null;
        }
        if (parent != null) {
            if (parent.key < next.key) {
                parent.right = next;
            } else {
                parent.left = next;
            }
        } else {
            root = next;
        }
    }


    public void print(Node node, PrintStream wr) throws Exception {
        if (node != null) {
            wr.println(node.key);
            print(node.left, wr);
            print(node.right, wr);
        }
    }



}


import java.io.*;
import java.util.*;

public class Test {

    static class Node {
        private int key;
        private Node left;
        private Node right;

        public Node(int key) {this.key = key;}

        public int getKey() {return this.key;}
        public Node getLeft() {return this.left;}
        public Node getRight() {return this.right;}

        public void setKey(int key) {this.key = key;}
        public void setLeft(Node left) {this.left = left;}
        public void setRight(Node right) {this.right = right;}
    }

    static class Tree {
        Node root;

        public Tree() {this.root = null;}

        public boolean isEmpty() {return root == null;}

        public void add(int key) {root = add(root, key);}

        private static Node add(Node current, int key) {
            if (current == null) {
                return new Node(key);
            }
            if (key < current.key) {
                current.left = add(current.left, key);
            } else if (key > current.key) {
                current.right = add(current.right, key);
            }
            return current;
        }

        private void visit(Node current,PrintWriter out ) {

            if (current == null)
                return;
            out.println(current.getKey());
            visit(current.getLeft(), out);
            visit(current.getRight(), out);
        }

        public boolean search(int key) {return search(root, key);}

        private boolean search(Node current, int key){
            boolean found = false;
            while ((current != null) && !found)
            {
                int currentKey = current.getKey();
                if (key < currentKey)
                    current = current.getLeft();
                else if (key > currentKey)
                    current = current.getRight();
                else
                {
                    found = true;
                    break;
                }
                found = search(current, key);
            }
            return found;
        }

        public void delete(int key) {
            if (isEmpty()) return;
            if(search(key) == false) return;
            root = delete(root, key);
        }


        private Node delete(Node current, int key) {
            Node parent;
            if (current == null)
                return null;
            if (key < current.getKey())
            {
                current.setLeft(delete(current.getLeft(), key));
                return current;
            }
            if (key > current.getKey()) {
                current.setRight(delete(current.getRight(), key));
                return current;
            }
            if (current.getKey() == key)
            {
                Node leftSun, rightSun;
                leftSun = current.getLeft();
                rightSun = current.getRight();
                if (leftSun == null && rightSun == null)
                    return null;
                else if (leftSun == null)
                {
                    parent = rightSun;
                    return parent;
                }
                else if (rightSun == null)
                {
                    parent = leftSun;
                    return parent;
                }
                else
                {
                    int minKey = searchMin(current.right).getKey();
                    current.setKey(minKey);
                    current.setRight(delete(current.getRight(), minKey));
                    return current;
                }
            }
            return root;
        }

        public Node searchMin(Node current){

            if (current.getLeft()!=null)
                return searchMin(current.getLeft());
            else
                return current;
        }
    }

    public void run(){
        try{
            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            int deleteKey=in.nextInt();
            Tree tree=new Tree();
            while (in.hasNext()) {
                tree.add(in.nextInt());
            }
            tree.delete(deleteKey);
            if(!tree.isEmpty()){
                tree.visit(tree.root, out);}
            out.flush();

        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}
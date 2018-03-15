import java.io.*;
import java.util.*;

class Tree {
    ArrayList<Integer> order;
    public int maxSemipath = 0;
    private boolean gotMaxNode = false;
    static class Node {
        int key;
        int mark = 0;
        int path;
        Node left;
        Node right;
        public Node(int x){
            key = x;
        }
        public Node(){}
    }
    public Node root;
    public Node maxNode = new Node();

    public void insert(int x) {
        root = doInsert(root, x);
    }

    private static Node doInsert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = doInsert(node.left, x);
        } else if (x > node.key) {
            node.right = doInsert(node.right, x);
        }
        return node;
    }

    private void doLeftPreOrder(Node node){
        if (node != null) {
            order.add(node.key);
            if (node.left != null)
                doLeftPreOrder(node.left);
            if (node.right != null)
                doLeftPreOrder(node.right);
        }
        else return;
    }

    public void leftPreOrder(){
        order = new ArrayList<>();
        doLeftPreOrder(root);
    }

    public Node findMin(Node node){
        if (node.left != null)
            return findMin(node.left);
        else return node;
    }

    public Node delete(Node node, int key){
        if (node == null)
            return null;
        if (key < node.key){
            node.left = delete(node.left, key);
            return node;
        }
        else if (key > node.key){
            node.right = delete(node.right, key);
            return node;
        }
        if (node.left == null) {
            return node.right;
        }
        else if (node.right == null) {
            return node.left;
        }
        else {
            int minKey = findMin(node.right).key;
            node.key = minKey;
            node.right = delete(node.right, minKey);
            return node;
        }
    }

    public void maxPath(Node node){
        node.path = 0;
        if (node != null) {
            if (node.left != null)
                node.path += node.left.mark;
            if (node.right != null)
                node.path += node.right.mark;
            if (node.left != null)
                maxPath(node.left);
            if (node.right != null)
                maxPath(node.right);
            if (node.left != null)
                node.path++;
            if (node.right != null)
                node.path++;
        }
        if (node.path > maxSemipath)
            maxSemipath = node.path;
    }

    public void getHeight(Node node) {
        int leftMark = -1;
        int rightMark = -1;
        if (node.left != null) {
            getHeight(node.left);
            leftMark = node.left.mark;
        }
        if (node.right != null) {
            getHeight(node.right);
            rightMark = node.right.mark;
        }
        node.mark = Math.max(leftMark, rightMark) + 1;
    }
    private void doInOrder(Node node){
        if (node != null) {
            if (node.left != null)
                doInOrder(node.left);
            if (node.path == maxSemipath && gotMaxNode == false){
                maxNode = node;
                gotMaxNode = true;
            }
            if (node.right != null)
                doInOrder(node.right);
        }
        else return;
    }
    public void inOrder(){
        gotMaxNode = false;
        doInOrder(root);
    }

}

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        ArrayList<Tree.Node> excNodes = new ArrayList<>();
        Scanner scanner;
        int number;
        try{
            scanner = new Scanner(new File("input.txt"));

            while (scanner.hasNext()){
                number = scanner.nextInt();
                tree.insert(number);
            }
            tree.getHeight(tree.root);
            tree.maxPath(tree.root);
            tree.inOrder();
            if (tree.maxSemipath % 2 == 0){
                Tree.Node temp = tree.maxNode;
                int l;
                if (temp.left == null)
                    l = 0;
                else l = temp.left.mark;
                int r;
                if (temp.right == null)
                    r = 0;
                else r = temp.right.mark;
                int diff;
                int diff1 = 0;
                if (r == 0 && temp.right == null)
                    diff = l / 2 + 1;
                else if (l == 0 && temp.left == null)
                    diff = r / 2 + 1;
                else diff = Math.abs(r - l) / 2;
                int dir = 0;
                if (r < l)
                    dir = 1;
                boolean cont = false;
                while (diff != 0){
                    cont = true;
                    if (temp.left == null && temp.right != null){
                        if (dir == 0)
                            diff--;
                        else
                            excNodes.add(temp);
                        temp = temp.right;
                    }
                    else if (temp.right == null && temp.left != null){
                        if (dir == 1)
                            diff--;
                        else
                            excNodes.add(temp);
                        temp = temp.left;
                    }
                    else if (temp.right == null && temp.left == null){
                        diff1 = diff;
                        diff = 0;
                        cont = false;
                        temp  = excNodes.get(excNodes.size() - diff1);
                    }
                    else {
                        if (temp.left.mark >= temp.right.mark){
                            if (dir == 1)
                                diff--;
                            else
                                excNodes.add(temp);
                            temp = temp.left;

                        }
                        else {
                            if (dir == 0)
                                diff--;
                            else
                                excNodes.add(temp);
                            temp = temp.right;
                        }
                    }
                }
                if (cont) {
                    int lm = 0;
                    int rm = 0;
                    if (temp.left != null)
                        lm = temp.left.mark;
                    if (temp.right != null)
                        rm = temp.right.mark;
                    if (dir == 0) {
                        while ((temp.left != null) && (lm >= rm)){
                            temp = temp.left;
                            lm = 0;
                            rm = 0;
                            if (temp.left != null)
                                lm = temp.left.mark;
                            if (temp.right != null)
                                rm = temp.right.mark;
                        }
                    }
                    else {
                        while (temp.right != null && lm < rm){
                            temp = temp.right;
                            lm = 0;
                            rm = 0;
                            if (temp.left != null)
                                lm = temp.left.mark;
                            if (temp.right != null)
                                rm = temp.right.mark;
                        }
                    }
                }
                tree.delete(tree.root, temp.key);
            }
            tree.leftPreOrder();
            try{
                FileWriter fileWriter = new FileWriter("output.txt");
                for (Integer i:tree.order){
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(i.toString());
                    stringBuilder.append("\r\n");
                    fileWriter.write(stringBuilder.toString());
                }
                fileWriter.flush();
            }
            catch(Exception e){}
        }
        catch(Exception e){}
    }
}
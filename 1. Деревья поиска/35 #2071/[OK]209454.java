import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

class HalfPath{
    private List<Integer> nodes = new ArrayList<>();

    public HalfPath(){}

    public boolean add(int key) {
        return nodes.add(key);
    }
    public int size(){
        return nodes.size() - 1;
    }
    public boolean contains(int key){
        return nodes.contains(key);
    }

}


class BinaryTree {
    public Node root;
    private List<HalfPath> halfPaths = new ArrayList<>();
    private List<Node> leaves = new ArrayList<>(12);
    public static class Node {
        public static boolean isLeftChildOf(Node child, Node parent) {
            return parent.left == child;
        }

        public static boolean isRightChildOf(Node child, Node parent) {
            return parent.right == child;
        }

        public Node left;
        public Node right;
        public int depth;
        public int key;
        public Node parent;
        public boolean marked;
        public Node(int key, int depth){
            this(key, depth, null, null, null);
        }

        public Node(int key, int depth, Node parent) {
            this(key, depth, parent, null, null);
        }

        public Node(int key, int depth, Node parent, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.depth = depth;
            this.parent = parent;
        }

        public void add(int key) {
            if (key < this.key) {
                if (this.left == null) {
                    left = new Node(key, depth + 1, this);
                } else left.add(key);
            }
            if (key > this.key) {
                if (this.right == null) {
                    right = new Node(key, depth + 1, this);
                } else right.add(key);
            }
        }

        public int getKey() {
            return key;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getDepth() {
            return depth;
        }

        public Node getParent() {
            return parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public void removeRightChild() {
            right = null;
        }

        public void removeLeftChild() {
            left = null;
        }

        public void nullify() {
            right = null;
            left = null;
        }

        public boolean hasOnlyChild() {
            return left == null && right != null || right == null && left != null;
        }

        public Node find(int key) {
            if (this.key == key) return this;
            if (left == null && key < this.key) {
                return left.find(key);
            } else if (right == null && key > this.key) {
                return right.find(key);
            }
            return null; // TODO()
        }


        @Override
        public int hashCode() {
            return key;
        }

        @Override
        public boolean equals(Object obj) {
            return key == ((Node) obj).key;
        }
    }

    public void add(int key) {
        if (root == null) {
            root = new Node(key, 0, null);
        } else {

            root.add(key);




            /*Node parent;
            Node temp = root;

            while (true){
                parent = temp;
                if (key < temp.key){
                    temp = temp.left;
                    if (temp == null){
                        parent.left = new Node(key, parent.depth + 1, parent);
                        leaves.remove(parent);
                        leaves.add(parent.left);
                        return;
                    }
                }
                else if (key > temp.key){
                    temp = temp.right;
                    if (temp == null){
                        parent.right = new Node(key, parent.depth + 1, parent);
                        leaves.remove(parent);
                        leaves.add(parent.right);
                        return;
                    }
                }
            }*/

        }

    }

    public Node getRoot() {
        return root;
    }


    private Node find(int key) {
        Node temp = root;
        while (true) {
            int tempKey = temp.getKey();
            if (tempKey == key) return temp;
            if (tempKey < key) temp = temp.right;
            if (tempKey > key) temp = temp.left;
            if (temp == null) return null;
        }
    }

    public void traverse(Node node, Consumer<? super Node> consumer) {
        if (node != null) {
            consumer.accept(node);
            if (node.left != null) traverse(node.left, consumer);
            if (node.right != null) traverse(node.right, consumer);
        }
    }

    private void removeNodeWithOneChild(Node rm) {
        Node child = rm.getLeft() == null ? rm.getRight() : rm.getLeft();
        if (rm == root) root = child;
        else {
            Node parent = rm.getParent();
            if (rm == parent.right) parent.right = child;
            else parent.left = child;
        }
    }

    private void removeLeaf(Node leaf) {
        if (leaf == root) {
            root = null;
            return;
        }
        Node parent = leaf.getParent();
        if (leaf == parent.right) parent.right = null;
        else  parent.left = null;
    }

    public void remove(int key) {
        Node rm = find(key);
        if (rm == null) return;

        if (rm.isLeaf()) {
            removeLeaf(rm);
        } else if (rm.hasOnlyChild()) { // TODO()
            removeNodeWithOneChild(rm);
            rm.nullify();
        } else {
            Node rmParent = rm.getParent();
            Node smallest = rm.right;
            while (smallest.left != null) {
                smallest = smallest.left;
            }
            Node smallestParent = smallest.getParent();
            if (smallest == smallestParent.left) smallestParent.left = smallest.right;
            if (rm == root) {
                if (smallest == root.right) {
                    smallest.left = root.left;
                    root = smallest;
                } else {
                    smallest.left = root.left;
                    smallest.right = root.right;
                    root = smallest;
                }
            } else {
                if (smallest == smallestParent.right) {
                    if (rm == rmParent.left) rmParent.left = smallest;
                    else rmParent.right = smallest;
                    smallest.left = rm.left;
                } else {
                    smallestParent.left = smallest.right;
                    if (rm == rmParent.right) {
                        rmParent.right = smallest;
                    } else {
                        rmParent.left = smallest;
                    }
                    smallest.left = rm.left;
                    smallest.right = rm.right;
                }
                rm.nullify();
            }
        }


    }


    public int height(Node node){
        if (node == null || node.isLeaf()) return 0;
        int heightLeft = height(node.left);
        int heightRight = height(node.right);
        return 1 + Math.max(heightLeft, heightRight);
    }


    private boolean validateDistance(Node node, int k){

        if (node == root) return height(node) < k;

        for (Node leaf : leaves){

            //1
            if (Integer.compare(node.getKey(), root.getKey()) * Integer.compare(leaf.getKey(), root.getKey()) < 0){
                if (leaf.getDepth() + node.getDepth() >= k) return false;
            }

            //2
            if (isReachableFrom(node, leaf)){
                if (leaf.getDepth() - node.getDepth() >= k) return false;
            }
            //3
            {
                Node temp = node;
                while (! isReachableFrom(temp, leaf)) temp = temp.getParent();
                if ((node.getDepth() - temp.getDepth()) + (leaf.getDepth() - temp.getDepth()) >= k) return false;
            }

        }
        return true;
    }

    private Node commonParent(Node node1, Node node2){
        Node temp = node1;
        while (! isReachableFrom(temp, node2)) temp = temp.parent;
        return temp;
    }

    private int distanceBetweenLeaves(Node leaf1, Node leaf2){
        if (leaf1 == root) return leaf2.depth;
        if (leaf2 == root) return leaf1.depth;

        if ((leaf1.depth - root.depth) * (leaf2.depth - root.depth) < 0 ){
            return leaf1.depth + leaf2.depth;
        }

        //2
        else {
            Node commonParent = commonParent(leaf1, leaf2);
            return leaf1.depth - commonParent.depth + leaf2.depth - commonParent.depth;
        }
    }

    public List<Node> method(int k) {

        List<Node> res = new ArrayList<>(); // TODO();
        traverse(root, node ->{
            if (!node.marked && validateDistance(node, k)) res.add(node);
        });
        return res;
    }

    private boolean isReachableFrom(Node from, Node to){
        Node temp = from;
        while (true){
            if (temp == null) return false;
            if (temp == to) return true;
            if (to.getKey() < temp.getKey()) temp = temp.left;
            else temp = temp.right;
        }
    }


    private HalfPath create(Node node1, Node node2){

        HalfPath path = new HalfPath();

        while (! isReachableFrom(node1, node2)){
            node1.marked = true;
            path.add(node1.key);
            node1 = node1.parent;
        }
        path.add(node1.key);
        node1.marked = true;
        while (node2 != node1){
            node2.marked = true;
            path.add(node2.key);
            node2 = node2.parent;
        }

        return path;

    }

    private boolean isSuitable(Node node, int k){
        for (HalfPath halfPath : halfPaths){
            if (halfPath.contains(node.key)) return false;
        }
        return true;
    }

    private void initHalfPath(int k){
        for (int i = 0; i < leaves.size() - 1; i++) {
            for (int j = i + 1; j < leaves.size(); j++) {
                Node leaf1 = leaves.get(i);
                Node leaf2 = leaves.get(j);
                if (distanceBetweenLeaves(leaf1, leaf2) == k) halfPaths.add(create(leaf1, leaf2));
            }
        }
    }

    private void initLeaves(){
        traverse(root, node1 -> {
            if (node1.isLeaf()) leaves.add(node1);
        });
        if (root.hasOnlyChild()) leaves.add(root);
    }

    public Node problem35(int k){
        initLeaves();
        initHalfPath(k);

        List<Node> result = method(k);
        if (result.size() == 0) return null;
        else return Collections.max(result, Comparator.comparingInt(Node::getKey));
    }
}



public class Main {

    private static Path getOutput() throws Exception{
        return Files.createFile(Paths.get("tst.out"));
    }


    public static void main(String[] args) throws Exception {

        BinaryTree tree = new BinaryTree();
        List<String> lines = Files.readAllLines(Paths.get("tst.in"));

        PrintWriter writer = new PrintWriter(getOutput().toFile());
        int k = 0;
        k = Integer.parseInt(lines.get(0));

        for (int i = 1; i < lines.size(); i++){
            tree.add(Integer.parseInt(lines.get(i)));
        }

        if (tree.root == null) writer.println("Empty");
        else {
            BinaryTree.Node node = tree.problem35(k);
            if (node != null) tree.remove(node.key);

            if (tree.root == null) writer.println("Empty");
            else {
                tree.traverse(tree.root, node1 -> {
                    writer.println(node1.key);
                });
            }
        }
        writer.flush();


    }


}